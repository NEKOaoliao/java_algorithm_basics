package class11;

public class Code07_PaperFoldingLearn {
    
    public static void printPaperFolding(int n) {
        if (n <= 0) {
            return;
        }
        printInOrder(1, n, true);
    }

    public static void printInOrder(int layer, int depth, Boolean pos) {
        if(layer > depth) {
            return;
        }
        printInOrder(layer + 1, depth, true);
        // if (pos) {
        //     System.out.println("凹");
        // } else {
        //     System.out.println("凸");
        // }
        System.out.println(pos ? "凹": "凸");
        printInOrder(layer + 1, depth, false);
    }

    public static void main(String[] args) {
        printPaperFolding(3);
    }
}
