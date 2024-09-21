package class03;

public class Code011_ReverseList {

    static class Node{
        public int value;
        public Node next;

        public Node(){

        }

        public Node(int value){
            this.value = value;
        }
    }

    public Node reverse(Node head){
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    static class DoubleNode{
        public int value;
        public DoubleNode lastNode;
        public DoubleNode nextNode;

        public DoubleNode(){

        }

        public DoubleNode(int value){
            this.value = value;
        }
    }

    static DoubleNode reverse(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null){
            next = head.nextNode;
            head.nextNode = pre;
            head.lastNode = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {

    }
}
