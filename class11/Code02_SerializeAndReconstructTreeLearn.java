package class11;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Code02_SerializeAndReconstructTreeLearn {
    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *       
     * */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }
    
    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }
    
    public static Node buildByPreQueue(Queue<String> prelist) {
        if(prelist == null || prelist.isEmpty()) {
            return null;
        }

        return preb(prelist);
    }

    public static Node preb(Queue<String> prelist) {
        String val = prelist.poll();
        if (val == null) {
            return null;
        }
        Node cur = new Node(Integer.parseInt(val));
        cur.left = preb(prelist);
        cur.right = preb(prelist);
        return cur;
    }

    public static Queue<String> inSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		ins(head, ans);
		return ans;
	}

	public static void ins(Node head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ins(head.left, ans);
			ans.add(String.valueOf(head.value));
			ins(head.right, ans);
		}
	}

    public static Queue<String> posSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        poss(head, ans);
        return ans;
    }

    public static void poss(Node head, Queue<String> ans) {
        if(head == null) {
            ans.add(null);
        } else {
            poss(head.left, ans);
            poss(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    public static Node buildByPosQueue(Queue<String> posList) {
        if(posList == null || posList.isEmpty()) {
            return null;
        }
        reverseQueue(posList);
        return posb(posList);
    }

    public static <T> void reverseQueue(Queue<T> queue) {
        LinkedList<T> list = new LinkedList<>(queue);
        Collections.reverse(list);
        queue.clear();
        queue.addAll(list);
    }

    public static Node posb(Queue<String> reversedPosList) {
        String val = reversedPosList.poll();
        if(val == null) {
            return null;
        }
        Node cur = new Node(Integer.parseInt(val));
        cur.right = posb(reversedPosList);
        cur.left = posb(reversedPosList);
        return cur;
    }

	public static Queue<String> levelSerial(Node head) {
		if(head == null) {
			return null;
		}
		Queue<String> ans = new LinkedList<>();
		Queue<Node> que = new LinkedList<>();
		que.add(head);
		while(!que.isEmpty()) {
			Node cur = que.poll();
			if(cur == null) {
				ans.add(null);
			} else {
				ans.add(String.valueOf(cur.value));
				que.add(cur.left);
				que.add(cur.right);
			}
		}
		return ans;
	}

	public static Node buildByLevel(Queue<String> levelList) {
		if (levelList == null || levelList.isEmpty()) {
			return null;
		}
		Queue<Node> que = new LinkedList<>();
		Node head = new Node(Integer.parseInt(levelList.poll()));
		que.add(head);
		while(!levelList.isEmpty()) {
			Node parent = que.poll();
			String left = levelList.poll();
			String right = levelList.poll();
			if (left != null) {
				parent.left = new Node(Integer.parseInt(left));
				que.add(parent.left);
			}
			if (right != null) {
				parent.right = new Node(Integer.parseInt(right));
				que.add(parent.right);
			}
		}

		return head;
	}

    public static Node generateNode(String val) {
		if (val == null) {
			return null;
		}
		return new Node(Integer.valueOf(val));
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
	public static boolean isSameValueStructure(Node head1, Node head2) {
		if (head1 == null && head2 != null) {
			return false;
		}
		if (head1 != null && head2 == null) {
			return false;
		}
		if (head1 == null && head2 == null) {
			return true;
		}
		if (head1.value != head2.value) {
			return false;
		}
		return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
	}

	// for test
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Queue<String> pre = preSerial(head);
			Queue<String> pos = posSerial(head);
			Queue<String> level = levelSerial(head);
			Node preBuild = buildByPreQueue(pre);
			Node posBuild = buildByPosQueue(pos);
			Node levelBuild = buildByLevel(level);
			if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
				System.out.println("Oops!");
			}
            // if (!isSameValueStructure(preBuild, posBuild)) {
			// 	System.out.println("Oops!");
			// }
		}
		System.out.println("test finish!");
		
	}
}
