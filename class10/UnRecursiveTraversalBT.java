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
		if(head != null) {
			Stack<Node> stk = new Stack<>();
			stk.push(head);
			while(!stk.isEmpty()) {
				Node cur = stk.pop();
				System.out.print(cur.value + " ");
				if(cur.right != null) {
					stk.push(cur.right);
				}
				if(cur.left != null) {
					stk.push(cur.left);
				}
			}
		}
		System.out.println();
	}

	public static void in(Node cur) {
		System.out.print("in-order: ");
		if(cur != null) {
			Stack<Node> stk = new Stack<>();
			while(!stk.isEmpty() || cur != null) {
				if(cur != null) {
					stk.push(cur);
					cur = cur.left;
				} else {
					cur = stk.pop();
					System.out.print(cur.value + " ");
					cur = cur.right;
				}
			}
		}
		System.out.println();
	}

	public static void pos1(Node head) {
		System.out.print("pos-order: ");
		if(head != null) {
			Node cur = null;
			Stack<Node> stk1 = new Stack<>();
			Stack<Node> stk2 = new Stack<>();
			stk1.push(head);
			while(!stk1.isEmpty()) {
				cur = stk1.pop();
				stk2.push(cur);
				if(cur.left != null) {
					stk1.push(cur.left);
				}
				if(cur.right != null) {
					stk1.push(cur.right);
				}
			}
			while(!stk2.isEmpty()) {
				cur = stk2.pop();
				System.out.print(cur.value + " ");
			}
		}
		System.out.println();
	}

	public static void pos2(Node head) {
		System.out.print("pos-order: ");
		if(head != null) {
			Stack<Node> stk = new Stack<>();
			Node cur = null;
			stk.push(head);
			while(!stk.isEmpty()) {
				cur = stk.peek();
				if(cur.left != null && cur.left != head && cur.right != head) {
					stk.push(cur.left);
				} else if(cur.right != null && cur.right != head) {
					stk.push(cur.right);
				} else {
					cur = stk.pop();
					System.out.print(cur.value + " ");
					head = cur;
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
