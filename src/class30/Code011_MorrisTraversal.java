package class30;

public class Code011_MorrisTraversal {

    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void process(Node root) {
        if (root == null) {
            return;
        }
        // 1
        process(root.left);
        // 2
        process(root.right);
        // 3
    }


    /**
     * 如果存在左孩子，则找出左孩子的最右节点，
     * 如果该节点为空，则将其右指针指向当前节点（这是第一次遍历到该节点），将当前指针移到左孩子上
     * 如果该节点指向了当前节点，则将其右指针置为空，恢复树的结构（这是第二次遍历到该节点），将当前指针移到右孩子上
     * 如果不存在左孩子，则将当前指针移到右孩子上，进行下一轮遍历
     *
     * @param head
     */
    public static void mirris2(Node head) {
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;

            if (mostRight != null) {
                while (mostRight != null && mostRight != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }

            cur = cur.right;
        }
    }


    public static void mirrisPre(Node head) {
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {

            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    //这里是第一个次遍历到
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    //这里是第二次遍历到
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }

            cur = cur.right;
        }

        System.out.println();
    }

    public static void mirrisIn(Node head) {
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    //这里是第一个次遍历到
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    //这里是第二次遍历到
                    System.out.print(cur.value + " ");
                    mostRight.right = null;
                }
            } else{
                System.out.print(cur.value + " ");
            }

            cur = cur.right;
        }

        System.out.println();
    }

    public static void mirrisPost(Node head) {
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    //这里是第一个次遍历到
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    //这里是第二次遍历到
//                    System.out.print(cur.value + " ");
                    mostRight.right = null;
                }
//            } else{
//                System.out.print(cur.value + " ");
//            }
            }

            System.out.print(cur.value + " ");

            cur = cur.right;
        }

        System.out.println();
    }


    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        mirrisPre(head);
        mirrisIn(head);
    }


}
