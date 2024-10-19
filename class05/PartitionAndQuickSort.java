package class05;

public class PartitionAndQuickSort {
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    public static void NetherlandsFlag(int[] arr, int num) {
        int less = 0;
        int more = arr.length - 1;
        for (int i = 0; i <= more; i++) {
            if (arr[i] < num) {
                swap(arr, i, less);
                less++;
            } else if (arr[i] > num) {
                swap(arr, i, more);
                more--;
                i--;// 大于换过来的还需要再次判断，小于区域不需要做再次判断的原因是因为小于区域与大于区域之间的区域只有等于区和未检测的区域
            }
        }
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        // main logic
        int index = L;
        int less = L - 1;
        int more = R;// 可以认为此时R位置的数不属于算法执行的范围
        while (index < more) {
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else if (arr[index] == arr[R]) {
                index++;
            } else { // >
                swap(arr, index, --more);
            }
        }
        swap(arr, R, more);
        return new int[] { less + 1, more };
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // main logic
        int less = L;
        int more = R;
        while (less < more) {
            while (arr[more] > arr[L] && less < more) {
                more--;
            }
            while (arr[less] <= arr[L] && less < more) {
                less++;
            }
            swap(arr, less, more);
        }
        swap(arr, L, more);
        process3(arr, L, more - 1);
        process3(arr, more + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
