package class23;

/**
 * 给定一个正数数组arr
 * 请把ar中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class Code011_SplitSumClosed {

    /**
     * 按照提议拆解，寻找有利条件。做适当转化
     *
     * @param arr
     * @return
     */
    public static int split(int[] arr) {

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return process(arr, 0, sum / 2);
    }

    public static int process(int[] arr, int index, int rest) {
        if (arr.length == index) {
            return 0;
        }

        int p1 = process(arr, index + 1, rest);

        int p2 = 0;
        if (arr[index] < rest) {
            p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
        }

        return Math.max(p1, p2);
    }

    public static int split2(int[] arr) {
        int N = arr.length;

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }

        int[][] dp = new int[N + 1][sum + 1];

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum / 2; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (arr[index] < rest) {
                    p2 = arr[index] + dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][sum / 2];
    }


}
