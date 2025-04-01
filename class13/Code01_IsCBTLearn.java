package class13;

import java.util.LinkedList;
import java.util.Queue;

public class Code01_IsCBTLearn {
    
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static boolean isCBT1(Node head) {
        if(head == null) {
            return true;
        }
        Queue<Node> que = new LinkedList<>();
        boolean isCBT = true;
        boolean mark = false; // marked as true if current node has a null right child or has no child
        que.add(head);
        
        while(!que.isEmpty()) {
            Node cur = que.poll();
            Node left = cur.left;
            Node right = cur.right;
            if ((left == null && right != null) || (mark && (left != null || right != null))) {
                return false;
            }
            if(left == null || right == null) {
                mark = true;
            }
            if(left != null) {
                que.add(left);
            }
            if(right != null) {
                que.add(right);
            }
        }

        return isCBT;
    }

    public static class Info {
        boolean isCBT;
        int minHeight;
        int maxHeight;

        public Info(boolean isCBT, int min, int max) {
            this.isCBT = isCBT;
            this.minHeight = min;
            this.maxHeight = max;
        }
    }

    public static boolean isCBT2(Node head) {
        return process(head).isCBT;
    }

    public static Info process(Node head) {
        if(head == null) {
            return new Info(true,  0, 0);
        }

        boolean isCBT = false;
        int minHeight = 1;
        int maxHeitght = 1;

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        
        isCBT = leftInfo.isCBT && rightInfo.isCBT;
        isCBT = isCBT && 
                (rightInfo.maxHeight <= leftInfo.minHeight) && 
                (leftInfo.maxHeight - rightInfo.minHeight <= 1);

        minHeight += Math.min(leftInfo.minHeight, rightInfo.minHeight);
        maxHeitght += Math.max(leftInfo.maxHeight, rightInfo.maxHeight);

        return new Info(isCBT, minHeight, maxHeitght);
    }

    // for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
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
		int maxLevel = 7;
		int maxValue = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isCBT1(head) != isCBT2(head)) {
                System.out.println(isCBT1(head));
                System.out.println(isCBT2(head));
                printTree(head);
				System.out.println("Oops!");
                break;
			}
		}
		System.out.println("finish!");
	}
}
