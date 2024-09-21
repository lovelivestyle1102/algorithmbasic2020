package class12;

public class Code08_isSame {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isSame(Node node1,Node node2){
        if(node1 == null && node2 == null){
            return true;
        }

        if((node1 == null && node2 != null) || (node1 != null && node2 == null)){
            return false;
        }

        if(node1 != node2){
            return false;
        }

        return isSame(node1.left,node2.left) && isSame(node1.right,node2.right);
    }
}
