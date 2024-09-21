package class11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 返回二叉树最大宽度
 *
 */
public class Code051_TreeMaxWidth {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxWidthUseMap(Node head){
		if(head == null){
			return 0;
		}

		Queue<Node> queue = new LinkedList<>();

		queue.add(head);

		int max = 0;

		int currentLevelNodes = 0;

		Node curEnd = head;

		Node nextEnd = null;

		while(!queue.isEmpty()){
			Node node = queue.poll();

			if(node.left != null){
				queue.add(node.left);
				nextEnd = node.left;
			}

			if(node.right != null){
				queue.add(node.right);
				nextEnd = node.left;
			}

			currentLevelNodes++;

			if(node == curEnd){
				max = Math.max(max,currentLevelNodes);
				currentLevelNodes = 0;
				curEnd = nextEnd;
			}
		}

		return max;
	}


	public static int maxWidthNoMap(Node head){

		return 0;
	}

}
