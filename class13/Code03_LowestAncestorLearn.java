package class13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Code03_LowestAncestorLearn {
    
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static Node findLowestAncestor(Node head, Node a, Node b) {
        
        if (head == null) {
            return null;
        }
        
        Node lowestAncestorNode = null;

        Queue<Node> que = new LinkedList<>();
        Map<Node, Node> map = new HashMap<>();
        que.add(head);
        map.put(head, null);
        while(!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.left != null) {
                que.add(cur.left);
                map.put(cur.left, cur);
            }
            if(cur.right != null) {
                que.add(cur.right);
                map.put(cur.right, cur);
            }
        }

        // search
        Set<Node> parentSet = new HashSet<>();
        Node pre = a;
        while(pre != null) {
            parentSet.add(pre);
            pre = map.get(pre);
        }
        pre = b;
        while(pre != null) {
            if (parentSet.contains(pre)) {
                lowestAncestorNode = pre;
                break;
            }
            pre = map.get(pre);
        }

        return lowestAncestorNode;
    }

    public static class Info {
        boolean isABranch;
        boolean isBBranch;
        Node lowsestAncestor;

        public Info(boolean isAB, boolean isBB, Node ans) {
            this.isABranch = isAB;
            this.isBBranch = isBB;
            this.lowsestAncestor = ans;
        }
    }

    public static Node findLowestAncestor1(Node head, Node a, Node b) {
        return process1(head, a, b).lowsestAncestor;
    }

    public static Info process1(Node head, Node a, Node b) {
        if(head == null) {
            return new Info(false, false, null);
        }

        boolean isABranch;
        boolean isBBranch;
        Node lowsestAncestor = null;

        Info leftInfo = process1(head.left, a, b);
        Info rightInfo = process1(head.right, a, b);

        if (leftInfo.lowsestAncestor != null) {
            return new Info(true, true, leftInfo.lowsestAncestor);
        }

        if (rightInfo.lowsestAncestor != null) {
            return new Info(true, true, rightInfo.lowsestAncestor);
        }

        isABranch = (head == a) || leftInfo.isABranch || rightInfo.isABranch;
        isBBranch = (head == b) || leftInfo.isBBranch || rightInfo.isBBranch;

        if (isABranch && isBBranch) {
            lowsestAncestor = head;
        }

        return new Info(isABranch, isBBranch, lowsestAncestor);
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

	// for test
	public static Node pickRandomOne(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		int randomIndex = (int) (Math.random() * arr.size());
		return arr.get(randomIndex);
	}

	// for test
	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static void main(String[] args) {
		int maxLevel = 6;
		int maxValue = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Node o1 = pickRandomOne(head);
			Node o2 = pickRandomOne(head);
			if (findLowestAncestor(head, o1, o2) != findLowestAncestor1(head, o1, o2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
