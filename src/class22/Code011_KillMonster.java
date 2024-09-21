package class22;

/**
 * 给定3个参数，N, M,K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失{0~M}的血量.到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求k次打击之后，英雄把怪兽砍死的概率
 */
public class Code011_KillMonster {


    public static int kill(int N, int M, int K) {
        double all = (int) Math.pow(M + 1, K);

        int kill = process(N, M, K);

        return (int) (kill / all);
    }

    public static int process(int N, int M, int rest) {
        if (rest == 0) {
            return N <= 0 ? 1 : 0;
        }
        if (N <= 0) {
            return (int) Math.pow(M + 1, rest);
        }
        int ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(N - i, M, rest - 1);
        }
        return ways;
    }

    /**
     * 对于枚举依赖，可以通过查看附近值依赖关系减少复杂度。但是要注意边界值
     *
     * @param N
     * @param M
     * @param K
     * @return
     */
    public static int kill2(int N, int M, int K) {
        double all = (int) Math.pow(M + 1, K);

        int[][] dp = new int[N + 1][K + 1];

        dp[0][0] = 1;

        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                int ways = 0;
                for (int z = 0; z <= M; z++) {
                    if (N - z >= 0) {
                        ways += dp[N - z][j - 1];
                    } else {
                        ways += (long) Math.pow(M + 1, j - 1);
                    }
                }
                dp[i][j] = ways;
            }
        }
        int kill = dp[N][K];

        return (int) (kill / all);
    }


    public static int kill3(int N, int M, int K) {
        double all = (int) Math.pow(M + 1, K);

        int[][] dp = new int[N + 1][K + 1];

        dp[0][0] = 1;

        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
//                int ways = 0;
//                for (int z = 0; z <= M; z++) {
//                    if(N - z >= 0){
//                        ways += dp[N-z][j-1];
//                    }else{
//                        ways += (long) Math.pow(M + 1, j - 1);
//                    }
//                }
                dp[i][j] = dp[i][j - 1];
                if (i - 1 > 0) {
                    dp[i][j] += dp[i - 1][j];
                } else {
                    dp[i][j] += (long) Math.pow(M + 1, j - 1);
                }
            }
        }
        int kill = dp[N][K];

        return (int) (kill / all);
    }

}
