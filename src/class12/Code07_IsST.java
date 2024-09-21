package class12;

public class Code07_IsST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isST(Node head){
        return process(head).isST;
    }

    public static Info process(Node node){
        if(node == null){
            return new Info(true,0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int hight = Math.max(leftInfo.hight,rightInfo.hight) + 1;
        boolean isST = false;
        if(leftInfo.isST && rightInfo.isST && Math.asin(leftInfo.hight - rightInfo.hight) <=1 ){
            isST = true;
        }
        return new Info(isST,hight);
    }

    public static class Info{
        boolean isST;
        int hight;

        public Info(boolean isST, int hight){
            this.isST = isST;
            this.hight = hight;
        }
    }
}
