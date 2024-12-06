package class11;

import java.util.HashMap;
import java.util.LinkedList;

public class Code01_LevelTraversalBT {
    public static class Node {
        public Node left = null;
        public Node right = null;
        public int value;

        Node (int value) {
            this.value = value;
        }
    }

    public static int levelWithMaxWidth(Node head) {
        if (head == null) {
            return 0;
        }

        LinkedList<Node> que = new LinkedList<>();
        HashMap<Node, Integer> map = new HashMap<>();

        que.add(head);
        map.put(head, 1);
        int max = 0;
        int curLevel = 1;
        int curLevelNodes = 0;
        while(!que.isEmpty()) {
            head = que.poll();
            
            if(head.left != null) {
                que.add(head.left);
                map.put(head.left, curLevel + 1);
            }
            
            if(head.right != null) {
                que.add(head.right);
                map.put(head.right, curLevel + 1);
            }


            if(curLevel == map.get(head)) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 1;
                curLevel = map.get(head);
            }
        }
        return max;
    }

    public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
        head.left.right.left = new Node(8);
		head.right.left = new Node(6);
		head.right.left.left = new Node(9);
		head.right.right = new Node(7);

		int res = levelWithMaxWidth(head);
        System.out.print("Result: " + res);
		System.out.println("\n========");
	}
}
