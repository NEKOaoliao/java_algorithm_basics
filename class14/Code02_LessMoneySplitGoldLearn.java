package class14;

import java.util.PriorityQueue;

public class Code02_LessMoneySplitGoldLearn {
    
    public static int lessMoney0(int[] arr) {
        return process(0, arr);
    }

    public static int process(int curMoney, int[] arr) {
        if (arr.length <= 1) {
            return curMoney;
        }

        int res = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                res = Math.min(process(curMoney + arr[i] + arr[j], mergeTwoAndCopyArr(i, j, arr)), res);
            }
        }

        return res;
    }

    public static int[] mergeTwoAndCopyArr(int i, int j, int[] arr) {
        int[] newArr = new int[arr.length - 1];
        int ptrA = 0;
        int ptrB = 0;
        while (ptrA < arr.length) {
            if (ptrA != i && ptrA != j) {
                newArr[ptrB] = arr[ptrA];
                ptrB++;
            }
            ptrA++;
        }
        newArr[newArr.length - 1] = arr[i] + arr[j];

        return newArr;
    }

    public static int lessMoney1(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while(pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }

    	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 6;
		int maxValue = 1000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			if (lessMoney1(arr) != lessMoney0(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
