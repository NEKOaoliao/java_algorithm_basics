package class04;

public class MergeSort {

	public static void mergeSort1(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	public static void process(int[] arr, int L, int R) {
		if(L < R) {
			int mid = L + ((R - L) >> 1);
			process(arr, L, mid);
			process(arr, mid + 1, R);
			merge(arr, L, mid, R);
		}
	}

	public static void merge(int[] arr, int L, int M, int R) {
		int[] helper = new int[R - L + 1];
		int i = 0;
		int p1 = L;
		int p2 = M + 1;
		while(p1 <= M && p2 <= R) {
			helper[i++] = arr[p1] <= arr[p2]? arr[p1++]: arr[p2++];
		}
		while(p1 <= M) {
			helper[i++] = arr[p1++];
		}
		while(p2 <= R) {
			helper[i++] = arr[p2++];
		}
		i = 0;
		while(L <= R) {
			arr[L++] = helper[i++];
		}
	}

	// 非递归方法实现
	public static void mergeSort2(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		int N = arr.length;
		int mergeSize = 1;
		while(mergeSize < N) {
			int L = 0;
			while(L < N - mergeSize) {
				int M = L + mergeSize - 1;
				int R = Math.min(N - 1, M + mergeSize);
				merge(arr, L, M, R);
				L = R + 1;
			}
			if(mergeSize > N/2) {
				break;
			}
			mergeSize <<= 1;
		}
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
		int testTime = 50000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			mergeSort1(arr1);
			mergeSort2(arr2);
			if (!isEqual(arr1, arr2)) {
				System.out.println("出错了！");
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
