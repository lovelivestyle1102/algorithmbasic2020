package class25;

import java.util.Stack;

/**
 * 给定一小个非负数组arr，代表直方图
 * 返回直方图的最大长方形面积
 *
 */
// 测试链接：https://leetcode.com/problems/largest-rectangle-in-histogram
public class Code031_LargestRectangleInHistogram {

	public static int largestRectangleArea1(int[] height) {
		int N = height.length;

		Stack<Integer> stack = new Stack<>();

		int maxValue = 0;

		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
				Integer pos = stack.pop();
				maxValue = Math.max(maxValue, (stack.isEmpty() ? -1 : (i - 1 - stack.peek())) * height[pos]);
			}
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			Integer pos = stack.pop();
			maxValue = Math.max(maxValue, (stack.isEmpty() ? N-1 : (N - 1 - stack.peek())) * height[pos]);
		}

		return maxValue;
	}

}
