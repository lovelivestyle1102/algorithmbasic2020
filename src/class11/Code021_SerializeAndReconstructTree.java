package class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化和反序列化
 *
 */
public class Code021_SerializeAndReconstructTree {
    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *       
     * */
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Queue<String> preSerial(Node head){
		if(head == null){
			return null;
		}

		Queue<String> queue = new LinkedList<>();

		preEn(head,queue);

		return queue;
	}

	private static void preEn(Node head, Queue<String> queue) {
		if(head == null){
			queue.add(null);
		}else {
			queue.add(head.value + "");
			preEn(head.left, queue);
			preEn(head.right, queue);
		}
	}

	public static Node preDeserial(Queue<String> queue){
		if(queue.isEmpty()){
			return null;
		}

		return preDe(queue);
	}

	private static Node preDe(Queue<String> queue) {
		String poll = queue.poll();
		Node root = new Node(Integer.valueOf(poll));
		root.left = preDe(queue);
		root.right = preDe(queue);
		return root;
	}

//	public static Queue<String> levelSerial(Node head) {
//
//	}
//
//	public static Node buildByLevelQueue(Queue<String> levelList) {
//
//	}

	public static void main(String[] args) {
//		int maxLevel = 5;
//		int maxValue = 100;
//		int testTimes = 1000000;
//		System.out.println("test begin");
//		for (int i = 0; i < testTimes; i++) {
//			Node head = generateRandomBST(maxLevel, maxValue);
//			Queue<String> pre = preSerial(head);
//			Queue<String> pos = posSerial(head);
//			Queue<String> level = levelSerial(head);
//			Node preBuild = buildByPreQueue(pre);
//			Node posBuild = buildByPosQueue(pos);
//			Node levelBuild = buildByLevelQueue(level);
//			if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
//				System.out.println("Oops!");
//			}
//		}
//		System.out.println("test finish!");
		
	}
}
