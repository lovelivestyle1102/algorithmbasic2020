package class17;

import java.util.HashSet;
import java.util.Stack;

public class Code021_Hanoi {
	public static void main(String[] args) {
		hanoi1(3);
		System.out.println("=======================");
		hanoi2(3);
	}

	public static void hanoi2(int n){
		if(n > 0){
			func(n,"left","mid","right");
		}
	}

	/**
	 * 参数
	 *
	 * @param n
	 * @param from
	 * @param to
	 * @param other
	 */
	public static void func(int n,String from,String to,String other){
		if(n == 1){
			System.out.println("move 1 from "+ from + " to " + to);
			return;
		}else{
			func(n -1 ,from,other,to);
			System.out.println("Move " + n + " from " + from + " to " + to);
			func(n-1,other,to,from);
		}
	}




//	public static void hanoi2(int n) {
//		if (n > 0) {
//			func(n, "left", "right", "mid");
//		}
//	}
//
//	public static void func(int N, String from, String to, String other) {
//		if (N == 1) { // base
//			System.out.println("Move 1 from " + from + " to " + to);
//		} else {
//			func(N - 1, from, other, to);
//			System.out.println("Move " + N + " from " + from + " to " + to);
//			func(N - 1, other, to, from);
//		}
//	}

	public static void hanoi1(int n) {
		moveToRightFromLeft(n);
	}


	public static void moveToRightFromLeft(int n){
		if(n == 1){
			System.out.println("move 1 from left to right");
			return ;
		}

		moveToMiddleFromLeft(n - 1);

		System.out.println("move "+n+" from left to right");

		moveToRightFromMiddle(n - 1);
	}

	public static void moveToMiddleFromLeft(int n){
		if(n == 1){
			System.out.println("move 1 from left to middle");
			return;
		}

		moveToRightFromLeft(n-1);

		System.out.println("move "+n+" from left to middle");

		moveToMiddleFromRight(n-1);
	}

	public static void moveToMiddleFromRight(int n){
		if(n == 1){
			System.out.println("move 1 from right to middle");
			return;
		}

		moveToLeftFromRight(n-1);

		System.out.println("move "+n+" from right to middle");

		moveToMiddleFromLeft(n-1);
	}

	public static void moveToLeftFromRight(int n){
		if(n == 1){
			System.out.println("move 1 from right to left");
			return;
		}

		moveToMiddleFromRight(n-1);

		System.out.println("move "+n+" from right to left");

		moveToLeftFromMiddle(n-1);
	}


	public static void moveToRightFromMiddle(int n){
		if(n == 1){
			System.out.println("move 1 from middle to right");
			return;
		}

		moveToLeftFromMiddle(n-1);

		System.out.println("move "+n+" from middle to right");

		moveToRightFromLeft(n-1);
	}

	public static void moveToLeftFromMiddle(int n){
		if(n == 1){
			System.out.println("move 1 from middle to left");
            return;
		}

		moveToRightFromMiddle(n-1);

		System.out.println("move "+n+" from middle to left");

		moveToLeftFromRight(n-1);
	}
}
