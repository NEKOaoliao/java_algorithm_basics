package class10;

import java.util.Stack;

public class UnRecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static void pre(Node head) {
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stk = new Stack<>();
			stk.push(head);
			while (!stk.isEmpty()) {
				head = stk.pop();
				System.out.print(head.value + " ");
				if (head.right != null) {
					stk.push(head.right);
				}
				if (head.left != null) {
					stk.push(head.left);
				}
			}
		}
		System.out.println();
	}

	public static void in(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
			Stack<Node> stk = new Stack<>();
			while(!stk.empty() || head != null) {
				if(head != null) {
					stk.push(head);
					head = head.left;
				} else {
					head = stk.pop();
					System.out.print(head.value + " ");
					head = head.right;
				}
			}
		}
		System.out.println();
	}

	public static void pos1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> stk1 = new Stack<>();
			Stack<Node> stk2 = new Stack<>();
			stk1.push(head);
			while(!stk1.empty()) {
				head = stk1.pop();
				stk2.push(head);
				if (head.left != null) {
					stk1.push(head.left);
				}
				if (head.right != null) {
					stk1.push(head.right);
				}
			}
			while(!stk2.empty()) {
				Node n = stk2.pop();
				System.out.print(n.value + " ");
			}
		}
		System.out.println();
	}

	public static void pos2(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> stk = new Stack<>();
			Node cur = null;
			stk.push(head);
			while(!stk.empty()) {
				cur = stk.peek();
				if(cur.left != null && head != cur.left && head != cur.right) {
					stk.push(cur.left);
				} else if (cur.right != null && head != cur.right) {
					stk.push(cur.right);
				} else {
					head = stk.pop();
					System.out.print(head.value + " ");
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos1(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
	}

}
