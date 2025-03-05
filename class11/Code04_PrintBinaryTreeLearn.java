package class11;

public class Code04_PrintBinaryTreeLearn {

    public static class Node {
        int value;
        Node left;
        Node right;
        Node(int val) {
            this.value = val;
        }
    }
    
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 20);
        System.out.println();
    }

    public static void printInOrder(Node cur, int height, String to, int len) {
        if (cur == null) {
            return;
        }

        printInOrder(cur.right, height + 1, "v", len);

        String valStr = String.valueOf(cur.value);
        valStr = to + valStr + to;
        int lenM = valStr.length();
        int lenL = (len - lenM) >> 1;
        int lenR = len - lenL - lenM;
        System.out.println(getSpace(height * len + lenL) + valStr + getSpace(lenR));

        printInOrder(cur.left, height + 1, "^", len);
    }

	public static String getSpace(int len) {
        String space = " ";
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            builder.append(space);
        }
        return builder.toString();
	}

    public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(-222222222);
		head.right = new Node(3);
		head.left.left = new Node(Integer.MIN_VALUE);
		head.right.left = new Node(55555555);
		head.right.right = new Node(66);
		head.left.left.right = new Node(777);
		printTree(head);

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.left.left.right = new Node(7);
		printTree(head);

		head = new Node(1);
		head.left = new Node(1);
		head.right = new Node(1);
		head.left.left = new Node(1);
		head.right.left = new Node(1);
		head.right.right = new Node(1);
		head.left.left.right = new Node(1);
		printTree(head);

	}
    
    
}
