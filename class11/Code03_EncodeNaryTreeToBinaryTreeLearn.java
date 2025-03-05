package class11;

import java.util.ArrayList;
import java.util.List;

public class Code03_EncodeNaryTreeToBinaryTreeLearn {
    // 提交时不要提交这个类
	public static class Node {
		public int val;
		public List<Node> children;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	};

	// 提交时不要提交这个类
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

    class Codec {
        public TreeNode encode(Node head) {
            if (head == null) {
                return null;
            }
            TreeNode newHead = new TreeNode(head.val);
            newHead.left = en(head.children);
            return newHead;
        }

        public TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child: children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null) {
                    head = tNode;
                } else {
                    cur.right = tNode;
                }
                cur = tNode;
                cur.left = en(child.children);
            }
            return head;
        }

        public Node decode(TreeNode head) {
            if (head == null) {
                return null;
            }
            Node newHead = new Node(head.val, de(head.left));
            return newHead;
        }

        public List<Node> de(TreeNode head) {
            List<Node> children = new ArrayList<>();
            while(head != null) {
                Node tNode = new Node(head.val, de(head.left));
                children.add(tNode);
                head = head.right;
            }
            return children;
        }
    }
}
