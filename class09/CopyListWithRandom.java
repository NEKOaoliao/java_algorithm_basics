package class09;

import java.util.HashMap;

// 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
public class CopyListWithRandom {

	public static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
	//Hash 做法 笔试用
	public static Node copyRandomList1(Node head) {
		HashMap<Node, Node> map = new HashMap<>();
		Node cur = head;
		while(cur != null) {
			map.put(cur, new Node(cur.val));
			cur = cur.next;
		}
		cur = head;
		while(cur != null) {
			map.get(cur).next = map.get(cur.next);
			map.get(cur).random = map.get(cur.random);
			cur = cur.next;
		}
		return map.get(head);
	}

	public static Node copyRandomList2(Node head) {
		if(head == null) {
			return head;
		}

		Node cur = head;
		Node next = null;
		while(cur != null) {
			next = cur.next;
			cur.next = new Node(cur.val);
			cur.next.next = next;
			cur = next;
		}

		Node copy = null;
		cur = head;
		while(cur != null) {
			next = cur.next.next;
			copy = cur.next;
			copy.random = cur.random != null ? cur.random.next : null;
			cur = next;
		}

		cur = head;
		Node res = cur.next;
		while(cur != null) {
			next = cur.next.next;
			copy = cur.next;
			cur.next = next;
			copy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}


	//for test
	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print("[cur: " + node.val + " ");
			if(node.random != null) {
				System.out.print("rand: " + node.random.val + "] -> ");
			} else {
				System.out.print("rand: null] -> ");
			}
			node = node.next;
		}
		System.out.println();
	}

	//for test
	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(6);
		head1.random = head1.next.next.next; //7 -> 8
		head1.next.random = null; // 9 -> null
		head1.next.next.random = head1; // 1 -> 7 
		head1.next.next.next.random = null; // 8 -> null
		head1.next.next.next.next.random = head1.next.next.next.next.next.next; // 5 -> 6
		head1.next.next.next.next.next.random = head1.next.next.next.next.next; // 2 -> 2
		head1.next.next.next.next.next.next.random = head1.next.next.next.next; // 6 -> 5
		System.out.println("===========================================");
		printLinkedList(head1);
		System.out.println("===========================================");
		Node cHead1 = copyRandomList1(head1);
		printLinkedList(cHead1);
		System.out.println("===========================================");
		Node cHead2 = copyRandomList2(head1);
		printLinkedList(cHead2);

	}
}
