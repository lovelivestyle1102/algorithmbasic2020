package class19;

/**
 * 背包问题
 */
public class Code011_Knapsack {

    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int index, int rest) {
        if (index == w.length) {
            return 0;
        }

        if (rest < 0) {
            return -1;
        }

        int p1 = process(w, v, index + 1, rest);

        int next = process(w, v, index + 1, rest - w[index]);

        int p2 = 0;

        if (next != -1) {
            p2 = v[index] + next;
        }

        return Math.max(p1, p2);
    }
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static int maxValue3(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }

        int N = w.length;

        int[][] dp = new int[N+1][bag + 1];

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
//                dp[index][rest] = Math.max(dp[index + 1][rest], dp[index + 1][rest - w[index]] == -1 ? 0 : v[index] + dp[index + 1][rest - w[index]]);
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][bag];
    }


    public static int maxValue2(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }

        int N = w.length;

        int[][] dp = new int[N + 1][bag + 1];

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < bag + 1; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println("=========================");

        // 尝试函数！
        return process2(w, v, 0, bag, dp);
    }

    public static int process2(int[] w, int[] v, int index, int rest, int[][] dp) {
        if (index == w.length) {
            return 0;
        }

        System.out.println("index is " + index + " rest is " + rest + " the dp is " + dp[index][rest]);

        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }

        int ans = 0;

        if (rest < 0) {
            ans = Integer.MIN_VALUE;
        } else {
            int p1 = process2(w, v, index + 1, rest, dp);

            int next = process2(w, v, index + 1, rest - w[index], dp);

            int p2 = 0;

            if (next != Integer.MIN_VALUE) {
                p2 = v[index] + next;
            }

            ans = Math.max(p1, p2);
        }

        dp[index][rest] = ans;

        System.out.println("index is " + index + " rest is " + rest + " the result is " + ans);

        return ans;
    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
//        System.out.println(maxValue(weights, values, bag));
//        System.out.println(maxValue2(weights, values, bag));
        System.out.println(maxValue3(weights, values, bag));
//		System.out.println(dp(weights, values, bag));
    }

}
