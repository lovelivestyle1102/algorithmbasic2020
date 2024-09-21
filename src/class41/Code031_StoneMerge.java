package class41;

/**
 * 摆放着n堆石子。现要将石子有次序地合并成一堆
 * 规定每次只能选相邻的2堆石子合并成新的一堆，并将新的一堆石子数记为该次合并的得分
 * 求出將n堆石子合并成一堆的最小得分（或最大得分） 合并方案
 */
public class Code031_StoneMerge {


    public static int subSum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    public static int min1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;

        int[] sum = new int[N + 1];

        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        return process(sum, 0, N - 1);
    }

    public static int process(int[] sum, int L, int R) {
        if (L == R) {
            return 0;
        }

        int next = Integer.MAX_VALUE;

        for (int leftEnd = L; leftEnd <= R; leftEnd++) {
            next = Math.min(next, process(sum, L, leftEnd) + process(sum, leftEnd + 1, R));
        }

        return next + subSum(sum, L, R);
    }


    public static int min2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;

        int[] sum = new int[N + 1];

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        return process2(sum, 0, N - 1, dp);
    }

    public static int process2(int[] sum, int L, int R, int[][] dp) {
        if (dp[L][R] != -1) {
            return dp[L][R];
        }

        int ans = 0;

        if (L == R) {
            ans = 0;
        } else {
            int next = Integer.MAX_VALUE;

            for (int leftEnd = L; leftEnd <= R; leftEnd++) {
                next = Math.min(next, process2(sum, L, leftEnd, dp) + process2(sum, leftEnd + 1, R, dp));
            }

            ans = next + subSum(sum, L, R);
        }

        dp[L][R] = ans;

        return ans;
    }

    public static int min3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;

        int[] sum = new int[N + 1];

        int[][] dp = new int[N][N];

        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        for (int L = N - 2; L >= 0; L--) {
            for (int R = L + 1; R < N; R++) {
                int next = Integer.MAX_VALUE;
                for (int leftEnd = L; leftEnd <= R; leftEnd++) {
                    next = Math.min(next, dp[L][leftEnd] + dp[leftEnd + 1][R]);
                }
                dp[L][R] = next + subSum(sum, L, R);
            }
        }

        return dp[0][N - 1];
    }

    public static int min4(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int N = arr.length;

        int[] sum = new int[N + 1];

        int[][] dp = new int[N][N];

        int[][] bestPos = new int[N][N];

        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        for (int i = 0; i < N - 1; i++) {
            bestPos[i][i + 1] = i;
            dp[i][i + 1] = subSum(sum, i, i + 1);
        }

        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {

                int next = Integer.MAX_VALUE;

                int pos = 0;

                for (int leftEnd = bestPos[L][R - 1]; leftEnd <= bestPos[L + 1][R]; leftEnd++) {

                    if (next < dp[L][leftEnd] + dp[leftEnd + 1][R]) {
                        next = dp[L][leftEnd] + dp[leftEnd + 1][R];
                        pos = leftEnd;
                    }
                }

                dp[L][R] = next + subSum(sum, L, R);
                bestPos[L][R] = pos;
            }
        }

        return dp[0][N - 1];
    }


}
