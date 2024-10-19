package class01;

import java.util.Arrays;

public class insertionSort {
    
    public static void Sort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        for(int i = 1; i < arr.length; i++) {
            int j = i;
            while(j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        //Math.random() [0, 1)
        //Math.random() * N [0, N)
        //(int)(Math.random() * N) [0, N - 1]
        int[] arr = new int[(int)(Math.random() * (maxSize + 1))];
        for(int i = 0; i < arr.length; i++) {
            //[0, maxValue + 1) + (-maxValue, 0] = (-maxValue, maxValue + 1) 
            //after rounding [-maxValue, maxValue]
            arr[i] = (int)((Math.random() * (maxValue + 1))) - ((int)(Math.random() * maxValue));            
        }
        return arr;
    } 

    // for test
    public static int[] copyArray(int[] arr) {
        if(arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    // for test
    // compare two arrays
    public static boolean isEqual(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    // for test
    // print array
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
        int testTime = 500;
        int maxSize = 1000;
        int maxValue = 100;
        boolean succeed = true;
        for(int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            Sort(arr1);
            comparator(arr2);
            if(!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.print("Not Equal!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking Fucked!");
    }

}
