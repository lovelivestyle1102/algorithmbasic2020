package class18;

/**
 * 假设有排成一行的N个位置，记为1-N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是 1-N中的一个）
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数N、M、K、P，返回方法数。
 */
public class Code011_RobotWalk {

    public static void main(String[] args) {
        int num = walk1(2, 6, 4, 17);
        System.out.println(num);
        int num2 = walk2(2, 6, 4, 17);
        System.out.println(num2);
        int num3 = walk3(2, 6, 4, 17);
        System.out.println(num3);
    }

    /**
     * @param start
     * @param K
     * @param aim
     * @param N
     * @return
     */
    public static int walk1(int start, int K, int aim, int N) {
        return process1(start, K, aim, N);
    }

    //暴力尝试，自然智慧
    //其中aim,N是固定的，cur，rest是变化的
    //可以利用二维数组来记录cur，rest所有变化结果
    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }

        if (cur == 1) {
            return process1(2, rest - 1, aim, N);
        }

        if (cur == N) {
            return process1(N - 1, rest - 1, aim, N);
        }

        return process1(cur + 1, rest - 1, aim, N) + process1(cur - 1, rest - 1, aim, N);
    }

    public static int walk2(int start, int K, int aim, int N) {
        int[][] dp = new int[N + 1][K + 1];

        return process2(start, K, aim, N, dp);
    }

    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != 0) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process1(2, rest - 1, aim, N);
        } else if (cur == N) {
            ans = process1(N - 1, rest - 1, aim, N);
        } else {
            ans = process1(cur + 1, rest - 1, aim, N) + process1(cur - 1, rest - 1, aim, N);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }


    public static int walk3(int start, int K, int aim, int N) {
        int[][] dp = new int[N + 1][K + 1];

        dp[aim][0] = 1;

        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest-1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest -1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest-1];
        }

        return dp[start][K];
    }
}
