package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code05_TreeMaxWidthLearn {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // map based method
    public static int getTreeMaxWidthWMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> que = new LinkedList<>();
        que.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 0);
        int curLevel = 0;
        int curLevelNodes = 0;
        int maxWidth = 0;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (levelMap.get(cur) == curLevel) {
                curLevelNodes += 1;
            } else { // only updated when new layer is encountered
                maxWidth = Math.max(maxWidth, curLevelNodes);
                curLevel += 1;
                curLevelNodes = 1;
            }
            if (cur.left != null) {
                que.add(cur.left);
                levelMap.put(cur.left, curLevel + 1);
            }
            if (cur.right != null) {
                que.add(cur.right);
                levelMap.put(cur.right, curLevel + 1);
            }
        }
        maxWidth = Math.max(maxWidth, curLevelNodes); // need extra update
        return maxWidth;
    }

    public static int getTreeMaxWoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> que = new LinkedList<>();
        que.add(head);
        Node curEnd = head;
        Node NextEnd = null;
        int maxWidth = 0;
        int curLevelNodes = 0;
        while(!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.left != null) {
                que.add(cur.left);
                NextEnd = cur.left;
            }
            if(cur.right != null) {
                que.add(cur.right);
                NextEnd = cur.right;
            }
            if (cur == curEnd) {
                maxWidth = Math.max(maxWidth, curLevelNodes + 1);
                // update curEnd to nextEnd
                curEnd = NextEnd;
                // update NextEnd to null
                NextEnd = null;
                // update curLevelNodes to zero
                curLevelNodes = 0;
            } else {
                curLevelNodes++;
            }
        }
        return maxWidth;
    }


	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.3) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static void printTree(Node head) {
        if (head == null) {
            return;
        }
        int NodeNum = 1;
        Node nullNode = new Node(-100);
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                Node current = queue.poll();
                if (current != nullNode) {
                    System.out.print("|" + current.value + "| ");
                    NodeNum--;
                } else if (current == nullNode && NodeNum != 0) {
                    System.out.print("O ");
                }
                if (current == nullNode && NodeNum == 0) {
                    break;
                }
                if (current.left != null) {
                    queue.add(current.left);
                    NodeNum++;
                } else {
                    queue.add(nullNode);
                }
                if (current.right != null) {
                    queue.add(current.right);
                    NodeNum++;
                } else {
                    queue.add(nullNode);
                }
                levelSize--;
            }
            if (NodeNum != 0) {
                System.out.println();
            } else {
                break;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
		int maxLevel = 6;
		int maxValue = 40;
		int testTimes = 1000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
            if(i == 0) {
                printTree(head);
                System.out.println("maxWidthWMap: " + getTreeMaxWidthWMap(head));
                System.out.println("maxWidthWoMap: " + getTreeMaxWoMap(head));
            }
			if (getTreeMaxWidthWMap(head) != getTreeMaxWoMap(head)) {
				System.out.println("Oops!");
                System.out.println(getTreeMaxWidthWMap(head));
                System.out.println(getTreeMaxWoMap(head));
                return;
			}
		}
		System.out.println("finish!");

	}
    
}
