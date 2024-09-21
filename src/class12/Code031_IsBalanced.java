package class12;

public class Code031_IsBalanced {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalanced1(Node head) {
        return process(head).isBal;
	}

	public static Info process(Node head){
		if(head == null){
			return new Info(true,0);
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		int hight = Math.max(leftInfo.hight,rightInfo.hight)+1;
		boolean isBal = true;
		if(!leftInfo.isBal){
			isBal = false;
		}
		if(!rightInfo.isBal){
			isBal = false;
		}
		if(Math.abs(leftInfo.hight - rightInfo.hight) > 1){
			isBal = false;
		}
		return new Info(isBal,hight);
	}

	public static class Info{
		boolean isBal;
		int hight;

		public Info(boolean isBal , int hight){
			this.isBal = isBal;
			this.hight = hight;
		}
	}

}
