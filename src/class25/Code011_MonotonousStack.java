package class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code011_MonotonousStack {

	// arr = [ 3, 1, 2, 3]
	//         0  1  2  3
	//  [
	//     0 : [-1,  1]
	//     1 : [-1, -1]
	//     2 : [ 1, -1]
	//     3 : [ 2, -1]
	//  ]
	public static int[][] getNearLessNoRepeat(int[] arr) {
		int[][] res = new int[arr.length][2];

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < arr.length; i++) {
			while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
				Integer pop = stack.pop();
				int leftLessPos = stack.isEmpty() ? -1 : stack.peek();
				res[pop][0] = leftLessPos;
				res[pop][1] = i;
			}
			stack.push(i);
		}

		while(!stack.isEmpty()){
			Integer pop = stack.pop();
			int leftLessPos = stack.isEmpty() ? -1 : stack.peek();
			res[pop][0] = leftLessPos;
			res[pop][1] = -1;
		}

		return res;
	}


}
