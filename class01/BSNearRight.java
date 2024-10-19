package class01;

import java.util.Arrays;

public class BSNearRight {
    public static int nearestIndex(int[] arr, int value) {
        if(arr == null) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while(L <= R) {
            int mid = L + ((R - L) >> 1);
            if(arr[mid] <= value) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    // for test
    public static int test(int[] arr, int value) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > value) {
                return i - 1;
            }
        }
        return arr.length - 1;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)(Math.random() * (maxSize + 1))];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * maxValue);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if(arr == null) {
            return;
        }
        for(int i = 0;i < arr.length;i++) {
            System.out.print(arr[i] + " ");
        } 
        System.out.println();
    }


    /// for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 10;
        boolean succeed = true;
        for(int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * maxValue);
            if(test(arr, value) != nearestIndex(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearestIndex(arr, value));
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "Nice!" : "Fucking Fucked");
    }


}