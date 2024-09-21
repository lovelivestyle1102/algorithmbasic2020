package class30;

public class Code012_MorrisTraversal {

    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;

        while (cur != null) {
            Node mostLeft = cur.left;
            if (mostLeft != null) {
                while (mostLeft.right != null && mostLeft.right != cur) {
                    mostLeft = mostLeft.right;
                }

                if (mostLeft.right == null) {
                    mostLeft.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostLeft.right = null;
                }
            }
            cur = cur.right;
        }
    }


}
