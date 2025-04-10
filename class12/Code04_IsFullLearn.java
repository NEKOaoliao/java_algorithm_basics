package class12;

public class Code04_IsFullLearn {

    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int val) {
            this.value = val;
        }
    }

    public static boolean isFull(Node head) {
        Info resInfo = process(head);
        return resInfo.numOfNodes == (int)Math.pow(2.0, (double)resInfo.height) - 1;
    }

    public static class Info {
        int height;
        int numOfNodes;

        Info(int height, int numOfNodes) {
            this.height = height;
            this.numOfNodes = numOfNodes;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int numOfNodes = leftInfo.numOfNodes + rightInfo.numOfNodes + 1;
        
        return new Info(height, numOfNodes);
    }

	// 第二种方法
	// 收集子树是否是满二叉树
	// 收集子树的高度
	// 左树满 && 右树满 && 左右树高度一样 -> 整棵树是满的
	public static boolean isFull2(Node head) {
		if (head == null) {
			return true;
		}
		return process2(head).isFull;
	}

	public static class Info2 {
		public boolean isFull;
		public int height;

		public Info2(boolean f, int h) {
			isFull = f;
			height = h;
		}
	}

	public static Info2 process2(Node h) {
		if (h == null) {
			return new Info2(true, 0);
		}
		Info2 leftInfo = process2(h.left);
		Info2 rightInfo = process2(h.right);
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		return new Info2(isFull, height);
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
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isFull(head) != isFull2(head)) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}
    
}
