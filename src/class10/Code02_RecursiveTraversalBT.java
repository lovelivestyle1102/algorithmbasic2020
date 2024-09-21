package class10;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Code02_RecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	//递归序
	public static void f(Node head) {
		if (head == null) {
			return;
		}

		System.out.println(head.value);
		// 1
		f(head.left);
		System.out.println(head.value);
		// 2
		f(head.right);
		System.out.println(head.value);
		// 3
	}

	// 先序打印所有节点
	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value);
		pre(head.left);
		pre(head.right);
	}

	public static void in(Node head) {
		if (head == null) {
			return;
		}
		in(head.left);
		System.out.print(head.value);
		in(head.right);
	}

	public static void pos(Node head) {
		if (head == null) {
			return;
		}
		pos(head.left);
		pos(head.right);
		System.out.println(head.value);
	}

	public static void preWithoutCursion(Node head){
		Stack<Node> nodeStack = new Stack<>();

		nodeStack.push(head);

		while(!nodeStack.isEmpty()){
			Node node = nodeStack.pop();
			System.out.print(node.value);

			if(node.right != null){
				nodeStack.push(node.right);
			}

			if(node.left != null){
				nodeStack.push(node.left);
			}
		}
	}

	public static void inWithoutCursion(Node head){
		Stack<Node> nodeStack = new Stack<>();

		//需要辅助列表记录已经访问过的记录
		Set<Node> nodes = new HashSet<>();

		nodeStack.push(head);

		while(!nodeStack.isEmpty()){
			Node node = nodeStack.peek();

			if(!nodes.contains(node.left) && node.left != null){
				nodeStack.push(node.left);
				nodes.add(node.left);
			}else{
				System.out.print(node.value);

				nodeStack.pop();

				if(node.right != null) {
					nodeStack.push(node.right);
				}
			}
		}
	}

	public static void posWithoutCursion(Node head){
//		Stack<Node> nodeStack = new Stack<>();
//
//		//需要辅助列表记录已经访问过的记录
//		Set<Node> nodes = new HashSet<>();
//
//		nodeStack.push(head);
//
//		while(!nodeStack.isEmpty()){
//			Node node = nodeStack.peek();
//
//			if(!nodes.contains(node.left) && node.left != null){
//				nodeStack.push(node.left);
//				nodes.add(node.left);
//			}else{
//				System.out.print(node.value);
//
//				nodeStack.pop();
//
//				if()
//
//				if(node.right != null) {
//					nodeStack.push(node.right);
//				}
//			}
//		}
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

//		f(head);
//		preWithoutCursion(head);
//		inWithoutCursion(head);

		pre(head);
		System.out.println("========");
		preWithoutCursion(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		inWithoutCursion(head);
		System.out.println("========");
//		pos(head);
//		System.out.println("========");

	}

}
