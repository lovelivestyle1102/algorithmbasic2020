package class41;

/**
 * 给定一个非负数组arr，长度为N，那么有N-1种方案可以把arr切成左右两部分
 * 每一种方案都有，min(左部分累加和，右部分累加和}
 * 求这么多方素中，min(左部分累加和，右部分累加和)的最大值是多少？
 * 整个过程要求时问复杂度O(N)
 */
public class Code011_BestSplitForAll {

    public static int subSum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    public static int bestSplit1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int N = arr.length;

        int[] sum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        int max = Integer.MIN_VALUE;

        for (int i = 1; i < N; i++) {
            int ans = Math.min(subSum(sum, 0, i - 1), subSum(sum, i, N - 1));

            max = Math.max(ans, max);
        }

        return max;
    }

    public static int bestSplit2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int sumAll = 0;
        for (int num : arr) {
            sumAll += num;
        }
        int ans = 0;
        int sumL = 0;
        // [0...s]  [s+1...N-1]
        for (int s = 0; s < N - 1; s++) {
            sumL += arr[s];
            int sumR = sumAll - sumL;
            ans = Math.max(ans, Math.min(sumL, sumR));
        }
        return ans;
    }

    public static int[] randomArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int ans1 = bestSplit1(arr);
            int ans2 = bestSplit2(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }


}