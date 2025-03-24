package class12;

import java.util.ArrayList;

public class Code05_MaxSubBSTSizeLearn {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        int max;
        int min;
        boolean isAllBST;
        int maxSubBSTSize;

        Info(int max, int min, boolean isAllBST, int maxSubBSTSize) {
            this.max = max;
            this.min = min;
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
        }
    }

    public static int getMaxSubBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int result = process0(head).maxSubBSTSize;
        return result;
    }

    public static Info process0(TreeNode n) {
        if (n == null) {
            return null;
        }

        int max;
        int min;
        boolean isAllBST;
        int maxSubBSTSize;

        Info leftInfo = process0(n.left);
        Info rightInfo = process0(n.right);

        boolean leftComp = leftInfo == null ? true : leftInfo.max < n.val;
        boolean rightComp = rightInfo == null ? true : rightInfo.min > n.val;
        boolean leftIsAllBST = leftInfo == null ? true : leftInfo.isAllBST; 
        boolean rightIsAllBST = rightInfo == null ? true : rightInfo.isAllBST; 

        if (leftComp && rightComp && leftIsAllBST && rightIsAllBST) {
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) 
                            + 
                            (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) 
                            +
                            1;
            isAllBST = true;
        } else {
            maxSubBSTSize = Math.max((leftInfo == null ? 0 : leftInfo.maxSubBSTSize), (rightInfo == null ? 0 : rightInfo.maxSubBSTSize));
            isAllBST = false;
        }
        
        if (leftInfo == null && rightInfo == null) {
            max = n.val;
            min = n.val;
        } else if (rightInfo != null && leftInfo != null) {
            max = Math.max(leftInfo.max, rightInfo.max);
            max = Math.max(max, n.val);
            min = Math.min(leftInfo.min, rightInfo.min);
            min = Math.min(min, n.val);
        } else {
            max = (leftInfo == null ? rightInfo.max : leftInfo.max);
            max = Math.max(max, n.val);
            min = (leftInfo == null ? rightInfo.min : leftInfo.min);
            min = Math.min(min, n.val);
        }
        
        return new Info(max, min, isAllBST, maxSubBSTSize);
    }

    	// 为了验证
	// 对数器方法
	public static int right(TreeNode head) {
		if (head == null) {
			return 0;
		}
		int h = getBSTSize(head);
		if (h != 0) {
			return h;
		}
		return Math.max(right(head.left), right(head.right));
	}

	// 为了验证
	// 对数器方法
	public static int getBSTSize(TreeNode head) {
		if (head == null) {
			return 0;
		}
		ArrayList<TreeNode> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).val <= arr.get(i - 1).val) {
				return 0;
			}
		}
		return arr.size();
	}

	// 为了验证
	// 对数器方法
	public static void in(TreeNode head, ArrayList<TreeNode> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	// 为了验证
	// 对数器方法
	public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// 为了验证
	// 对数器方法
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		TreeNode head = new TreeNode((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	// 为了验证
	// 对数器方法
	public static void main(String[] args) {
		int maxLevel = 6;
		int maxValue = 1000;
		int testTimes = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomBST(maxLevel, maxValue);
			if (getMaxSubBSTSize(head) != right(head)) {
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");
	}
}
