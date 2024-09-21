package class21;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}, aim =4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code031_CoinsWayNoLimit {

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (arr.length == index) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int times = 0; times * arr[index] <= rest; times++) {
            ways += process(arr, index + 1, rest - times * arr[index]);
        }
        return ways;
    }


    public static int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int N = arr.length;

        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 1;

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int ways = 0;
                for (int times = 0; times * arr[i] <= j; times++) {
                    ways += dp[i + 1][j - times * arr[i]];
                }
                dp[i][j] = ways;
            }
        }


        return dp[0][aim];
    }

    /**
     * 对三级依然需要通过枚举来推测出来结果的，可以通过位置依赖关系简化压缩
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsWay3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int N = arr.length;

        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 1;

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {

                dp[i][j] = dp[i + 1][j];

                if (j - arr[i] >= 0) {
                    dp[i][j] += dp[i][j - arr[i]];
                }
            }
        }

        return dp[0][aim];
    }

}
