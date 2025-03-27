package class13;

import java.util.ArrayList;

public class Code02_MaxSubBSTHeadLearn {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static class Info {
        boolean isBST;
        int maxSize;
        Node maxBSTSizeHead;
        int max;
        int min;

        public Info(boolean isBST, int maxSize, Node maxBSTSizeHead, int max, int min) {
            this.maxSize = maxSize;
            this.maxBSTSizeHead = maxBSTSizeHead;
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public static Node getMaxSubBSTHead(Node head) {
        if (head == null) {
            return null;
        }

        return process(head).maxBSTSizeHead;
    }

    public static Info process(Node head) {
        if(head == null) {
            return null;
        }

        boolean isBST;
        int maxSize;
        Node maxBSTSizeHead;
        int max;
        int min;

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        if (leftInfo != null && rightInfo != null) {
            isBST = leftInfo.isBST && rightInfo.isBST && (leftInfo.max < head.value && rightInfo.min > head.value);
            if (isBST) {
                maxSize = leftInfo.maxSize + rightInfo.maxSize + 1;
                maxBSTSizeHead = head;
            } else {
                maxSize = Math.max(leftInfo.maxSize, rightInfo.maxSize);
                maxBSTSizeHead = leftInfo.maxSize >= rightInfo.maxSize ? leftInfo.maxBSTSizeHead : rightInfo.maxBSTSizeHead;
            }
            max = Math.max(head.value, Math.max(leftInfo.max, rightInfo.max));
            min = Math.min(head.value, Math.min(leftInfo.min, rightInfo.min));
        } else if (leftInfo != null && rightInfo == null) {
            isBST = leftInfo.isBST && leftInfo.max < head.value;
            if (isBST) {
                maxSize = leftInfo.maxSize + 1;
                maxBSTSizeHead = head;
            } else {
                maxSize = leftInfo.maxSize;
                maxBSTSizeHead = leftInfo.maxBSTSizeHead;
            }
            max = Math.max(leftInfo.max, head.value);
            min = Math.min(leftInfo.min, head.value);
        } else if (leftInfo == null && rightInfo != null) {
            isBST = rightInfo.isBST && rightInfo.min > head.value;
            if (isBST) {
                maxSize = rightInfo.maxSize + 1;
                maxBSTSizeHead = head;
            } else {
                maxSize = rightInfo.maxSize;
                maxBSTSizeHead = rightInfo.maxBSTSizeHead;
            }
            max = Math.max(rightInfo.max, head.value);
            min = Math.min(rightInfo.min, head.value);
        } else {
            isBST = true;
            maxSize = 1;
            maxBSTSizeHead = head;
            max = head.value;
            min = head.value;
        }

        return new Info(isBST, maxSize, maxBSTSizeHead, max, min);
    }


    	public static int getBSTSize(Node head) {
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return 0;
			}
		}
		return arr.size();
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	public static Node maxSubBSTHead1(Node head) {
		if (head == null) {
			return null;
		}
		if (getBSTSize(head) != 0) {
			return head;
		}
		Node leftAns = maxSubBSTHead1(head.left);
		Node rightAns = maxSubBSTHead1(head.right);
		return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
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

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxSubBSTHead1(head) != getMaxSubBSTHead(head)) {
				System.out.println("Oops!");
                printTree(head);
                System.out.println(maxSubBSTHead1(head).value);
                System.out.println(getMaxSubBSTHead(head).value);
                return;
			}
		}
		System.out.println("finish!");
	}
    
}
