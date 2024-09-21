package class25;

import java.util.Stack;


/**
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形，内部有多少个1
 */
// 测试链接：https://leetcode.com/problems/maximal-rectangle/
public class Code041_MaximalRectangle {

    public static int maximalRectangle(char[][] map) {
        int N = map.length;
        int M = map[0].length;

        int maxValue = 0;

        int[] dp = new int[M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[j] = map[i][j] == '0' ? 0 : dp[j] + 1;
                maxValue = Math.max(maxValue, maxRecFromBottom(dp));
            }
        }

        return maxValue;
    }

    private static int maxRecFromBottom(int[] dp) {
        int N = dp.length;

        Stack<Integer> stack = new Stack<>();

        int maxValue = 0;

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && dp[stack.peek()] >= dp[i]) {
                Integer pos = stack.pop();
                maxValue = Math.max(maxValue, (stack.isEmpty() ? -1 : (i - 1 - stack.peek())) * dp[pos]);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer pos = stack.pop();
            maxValue = Math.max(maxValue, (stack.isEmpty() ? N - 1 : (N - 1 - stack.peek())) * dp[pos]);
        }

        return maxValue;
    }


}
