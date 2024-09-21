package class42;

import java.util.Arrays;

/**
 * 一条直线上有居民点，邮局只能建在居民点上。给定一个有序正数数组ar，每个值表示 居民点的一维坐标，再给定一个正教 num，表示邮局数量。选择num个居民点
 * 建立num个 邮局，使所有的居民点到最近邮局的总距离最短，返回最短的总距离
 * 【举例】
 * arr=[1,2,3,4,5,1000]， num=2
 * 第一个邮局建立在 3 位置，第二个邮局建立在 1000 位置。那么1位置到邮局的距离
 * 为 2，3位置到邮局距离为1，3位置到耶局的距离为口，
 * 4 位置到邮局的距离为 1,
 * 5位置到邮局的距 离为 21000 位置到邮局的距离为 0。这种方案下的总距离为6.
 * 其他任何方案的忘距离都不会 比该方索的总距商更短，所以返回6
 */
public class Code011_PostOfficeProblem {

    public static int min(int[] arr, int num) {
        int N = arr.length;

        int[][] w = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(j + i) >> 1];
            }
        }

        return process(w, 0, N - 1, num);
    }

    /**
     * @param w
     * @param L
     * @param R
     * @param rest
     * @return
     */
    public static int process(int[][] w, int L, int R, int rest) {
        if (rest == 0) {
            return 0;
        }

        if (L == R) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        for (int left = L; left <= R; left++) {
            ans = Math.min(ans, process(w, left, R, rest - 1) + w[L][left]);
        }

        return ans;
    }

    public static int min2(int[] arr, int num) {
        int N = arr.length;

        int[][] w = new int[N + 1][N + 1];

        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(j + i) >> 1];
            }
        }

        return process2(w, 0, N - 1, num, dp);
    }

    /**
     * @param w
     * @param L
     * @param R
     * @param rest
     * @return
     */
    public static int process2(int[][] w, int L, int R, int rest, int[][] dp) {
        if (dp[L][R] != -1) {
            return dp[L][R];
        }

        int ans = 0;

        if (rest == 0) {
            ans = 0;
        } else if (L == R) {
            ans = 0;
        } else {
            int cur = Integer.MAX_VALUE;

            for (int left = L; left <= R; left++) {
                int p1 =  process2(w, left, R, rest - 1, dp);


                cur = Math.min(cur, process2(w, left, R, rest - 1, dp) + w[L][left]);
            }

            ans = cur;
        }

        dp[L][R] = ans;

        return ans;
    }


    public static int min1(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        int[][] dp = new int[N][num + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 2; j <= Math.min(i, num); j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = 0; k <= i; k++) {
                    ans = Math.min(ans, dp[k][j - 1] + w[k + 1][i]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][num];
    }


    // for test
    public static int[] randomSortedArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i != len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int N = 30;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans1 = min1(arr, num);
            int ans2 = min2(arr, num);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }
}
