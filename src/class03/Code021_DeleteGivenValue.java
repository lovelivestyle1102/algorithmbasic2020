package class03;

public class Code021_DeleteGivenValue {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// head = removeValue(head, 2);
	public static Node removeValue(Node head, int num) {
		while(head != null){
			if(head.value != num){
				break;
			}
			head = head.next;
		}

		Node pre = head;
		Node cur = head;
		while(cur != null){
			if(cur.value == num){
				pre.next = cur.next;
			}else{
				pre = cur;
			}
			cur = cur.next;
		}
		return pre;
	}

}
