package class41;

public class Code021_BestSplitForEveryPosition {

    public static int subSum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    public static int[] bestSplit1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int N = arr.length;

        int[] ans = new int[N];

        int[] sum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        for (int i = 1; i < N; i++) {
            int cur = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                int res = Math.min(subSum(sum, 0, j - 1), subSum(sum, j, N - 1));

                cur = Math.max(res, cur);
            }
            ans[i] = cur;
        }
        return ans;
    }


    public static int[] bestSplit3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int N = arr.length;

        int[] ans = new int[N];

        int[] sum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }

        //在0-best -1 best -range -1 上获取最好结果
        int best = 0;
        for (int range = 1; range < N; range++) {
            while (best + 1 < range) {
                int before = Math.min(subSum(sum, 0, best), subSum(sum, best + 1, range));

                //最好分隔向后移动一位看看
                int after = Math.min(subSum(sum, 0, best + 1), subSum(sum, best + 2, range));

                //如果后以一位的结果好于前一个则说明可以后以最好分隔（这里包含等于，以便0情况下可以继续后移）
                if (after >= before) {
                    best++;
                } else {
                    break;
                }
            }
            ans[range] = Math.min(subSum(sum, 0, best - 1), subSum(sum, best, range));
        }
        return ans;
    }

    public static int[] bestSplit2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        for (int range = 1; range < N; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = 0;
                for (int L = 0; L <= s; L++) {
                    sumL += arr[L];
                }
                int sumR = 0;
                for (int R = s + 1; R <= range; R++) {
                    sumR += arr[R];
                }
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
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

    public static boolean isSameArray(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        int N = arr1.length;
        for (int i = 0; i < N; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int[] ans1 = bestSplit1(arr);
            int[] ans2 = bestSplit2(arr);
//            int[] ans3 = bestSplit3(arr);
            if (!isSameArray(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
