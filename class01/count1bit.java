package class01;

public class count1bit {
    public static void main(String[] args) {
        int input = 127;
        int count = 0;
        int r1 = 0;
        while(input != 0) {
            r1 = input & (~input + 1);
            count++;
            input = input ^ r1;
        }
        System.out.println(count);
    }
}
