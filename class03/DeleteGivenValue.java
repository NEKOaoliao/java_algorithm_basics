package class03;

public class DeleteGivenValue {
    
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node removeByValue(Node head, int num) {
        Node dummyHead = new Node(-1);
        dummyHead.next = head;
        Node cur = dummyHead;
        while(cur.next != null) {
            if(cur.next.value == num) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }

        return dummyHead.next;
    }
}
