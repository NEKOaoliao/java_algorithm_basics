package misc;

public class binary_swap {
    // 交换同一地址的值时会出错
    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a]^arr[b];
        arr[b] = arr[a]^arr[b];
        arr[a] = arr[a]^arr[b];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        swap(arr, 0, 1);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }
}
