package class12;

public class Code06_MaxDistanceLearn {
    
    public static class Node {
        Node left;
        Node right;
        int value;
        Node(int val) {
            this.value = val;
        }
    }

    public static int getMaxDistance(Node head) {
        if(head == null) {
            return 0;
        }
        MaxDistanceInfo maxInfo = process1(head);
        return Math.max(maxInfo.maxLefttRightTreeDistance, maxInfo.maxSubTreeDistance);
    }

    public static class MaxDistanceInfo {
        int maxSubTreeDistance;
        int maxLefttRightTreeDistance;
        MaxDistanceInfo(int std, int lrtd) {
            this.maxSubTreeDistance = std;
            this.maxLefttRightTreeDistance = lrtd;
        }
    }

    public static MaxDistanceInfo process1(Node head) {
        if (head == null) {
            return new MaxDistanceInfo(0, 0);
        }
        MaxDistanceInfo leftMaxDistance = process1(head.left);
        MaxDistanceInfo rightMaxDistance = process1(head.right);
        int curLeftRightTreeDistance = Math.max(leftMaxDistance.maxLefttRightTreeDistance, rightMaxDistance.maxLefttRightTreeDistance) + 1;
        int curmaxSubTreeDistance = leftMaxDistance.maxLefttRightTreeDistance + rightMaxDistance.maxLefttRightTreeDistance;
        curmaxSubTreeDistance = Math.max(leftMaxDistance.maxSubTreeDistance, curmaxSubTreeDistance);
        curmaxSubTreeDistance = Math.max(rightMaxDistance.maxSubTreeDistance, curmaxSubTreeDistance);
        
        return new MaxDistanceInfo(curmaxSubTreeDistance, curLeftRightTreeDistance);
    }

    // test code
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.right.right = new Node(4);
        System.out.println(getMaxDistance(head));

        // test case with answer 6
        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.left.left = new Node(8);
        head.right.right.right = new Node(9);
        System.out.println(getMaxDistance(head));

        // 6
        head = new Node(0);
        head.left = new Node(1);
        head.left.left = new Node(2);
        head.left.right = new Node(3);
        head.left.left.left = new Node(4);
        head.left.right.right = new Node(5);
        head.left.right.right.right = new Node(6);
        head.left.right.right.right.right = new Node(7);
        System.out.println(getMaxDistance(head));
    }
    
}
