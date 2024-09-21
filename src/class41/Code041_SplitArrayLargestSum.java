package class41;

/**
 * 给定一个整数数组arr，数组中的每个值都为正数，表示完成一副画作需要的时间，
 * 再给定一个正数num表示画匠的数量，每个画匠只能画连在一起的画作。所有的画家并行工作，
 * 请返回完成所有的画作需要的最少时间
 */
// leetcode原题
// 测试链接：https://leetcode.com/problems/split-array-largest-sum/
public class Code041_SplitArrayLargestSum {
    public static int subSum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    // 不优化枚举的动态规划方法，O(N^2 * K)
    public static int splitArray1(int[] nums, int K) {
        int N = nums.length;

        int[] sum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int result = process(sum, 0, N - 1, K);

        return result == -1 ? 0 : result;
    }

    public static int process(int[] sum, int L, int R, int K) {
        if (K <= 0) {
            return -1;
        }

        if (L > R) {
            return -1;
        }

        if (K == 1) {
            return subSum(sum, L, R);
        }

        //只剩一副画
        if (L == R) {
            return subSum(sum, L, L);
        }

        int res = Integer.MAX_VALUE;

        //以最后一个画家负责画的范围来讨论
        //在全部画作范围上负责 1，2，。。。。。
        for (int i = L; i <= R; i++) {

            int p1 = 0;

            int tryP1 = process(sum, i, R, K - 1);

            if (tryP1 != -1) {
                p1 = tryP1;
            }

            res = Math.min(res, Math.max(p1, subSum(sum, L, i - 1)));

        }

        return res;
    }


    // 求原数组arr[L...R]的累加和
    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    // 不优化枚举的动态规划方法，O(N^2 * K)
    public static int splitArray2(int[] nums, int K) {
        int N = nums.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[N][K + 1];
        for (int j = 1; j <= K; j++) {
            dp[0][j] = nums[0];
        }
        for (int i = 1; i < N; i++) {
            dp[i][1] = sum(sum, 0, i);
        }
        // 每一行从上往下
        // 每一列从左往右
        // 根本不去凑优化位置对儿！
        for (int i = 1; i < N; i++) {
            for (int j = 2; j <= K; j++) {
                int ans = Integer.MAX_VALUE;
                // 枚举是完全不优化的！
                for (int leftEnd = 0; leftEnd <= i; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    if (cur < ans) {
                        ans = cur;
                    }
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][K];
    }


    // 不优化枚举的动态规划方法，O(N^2 * K)
    public static int splitArray3(int[] nums, int K) {
        int N = nums.length;

        int[] sum = new int[N + 1];

        int[][][] dp = new int[N][N][K + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k <= K; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int result = process3(sum, 0, N - 1, K, dp);

        return result == -1 ? 0 : result;
    }

    public static int process3(int[] sum, int L, int R, int K, int[][][] dp) {
        if (dp[L][R][K] != -1) {
            return dp[L][R][K];
        }
        int ans = 0;
        if (K <= 0) {
            ans = -1;
        } else if (L > R) {
            ans = -1;
        } else if (K == 1) {
            ans = subSum(sum, L, R);
        } else if (L == R) {
            ans = subSum(sum, L, L);
        } else {
            int res = Integer.MAX_VALUE;

            //以最后一个画家负责画的范围来讨论
            //在全部画作范围上负责 1，2，。。。。。
            for (int i = L; i <= R; i++) {

                int p1 = 0;

                int tryP1 = process3(sum, i, R, K - 1, dp);

                if (tryP1 != -1) {
                    p1 = tryP1;
                }

                res = Math.min(res, Math.max(p1, subSum(sum, L, i - 1)));
            }

            ans = res;
        }

        dp[L][R][K] = ans;

        return ans;
    }


    public static int splitArray4(int[] nums, int K) {
        int N = nums.length;

        int[] sum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int[][] dp = new int[N][K + 1];
//        int[][] bestPos = new int[N][K+1];

        for (int i = 1; i <= K; i++) {
            dp[0][i] = nums[0];
        }

        for (int i = 1; i < N; i++) {
            dp[i][1] = subSum(sum, 0, i);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 2; j <= K; j++) {
                int ans = Integer.MAX_VALUE;
                for (int leftEnd = 0; leftEnd < i; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    if (cur < ans) {
                        ans = cur;
                    }
                }
                dp[i][j] = ans;
            }
        }

        return dp[N - 1][K];
    }


    public static int splitArray5(int[] nums, int K) {
        int N = nums.length;

        int[] sum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int[][] dp = new int[N][K + 1];
        int[][] bestPos = new int[N][K + 1];

        for (int i = 1; i <= K; i++) {
            dp[0][i] = nums[0];
            bestPos[0][i] = -1;
        }

        for (int i = 1; i < N; i++) {
            dp[i][1] = subSum(sum, 0, i);
            bestPos[i][1] = -1;
        }

        for (int j = 2; j <= K; j++) {
            for (int i = N - 1; i >= 1; i--) {
                int down = bestPos[i][j - 1];
                // 如果i==N-1，则不优化上限
                int up = i == N - 1 ? N - 1 : bestPos[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    // 注意下面的if一定是 < 课上的错误就是此处！当时写的 <= ！
                    // 也就是说，只有取得明显的好处才移动！
                    // 举个例子来说明，比如[2,6,4,4]，3个画匠时候，如下两种方案都是最优:
                    // (2,6) (4) 两个画匠负责 | (4) 最后一个画匠负责
                    // (2,6) (4,4)两个画匠负责 | 最后一个画匠什么也不负责
                    // 第一种方案划分为，[0~2] [3~3]
                    // 第二种方案划分为，[0~3] [无]
                    // 两种方案的答案都是8，但是划分点位置一定不要移动!
                    // 只有明显取得好处时(<)，划分点位置才移动!
                    // 也就是说后面的方案如果==前面的最优，不要移动！只有优于前面的最优，才移动
                    // 比如上面的两个方案，如果你移动到了方案二，你会得到:
                    // [2,6,4,4] 三个画匠时，最优为[0~3](前两个画家) [无](最后一个画家)，
                    // 最优划分点为3位置(best[3][3])
                    // 那么当4个画匠时，也就是求解dp[3][4]时
                    // 因为best[3][3] = 3，这个值提供了dp[3][4]的下限
                    // 而事实上dp[3][4]的最优划分为:
                    // [0~2]（三个画家处理） [3~3] (一个画家处理)，此时最优解为6
                    // 所以，你就得不到dp[3][4]的最优解了，因为划分点已经越过2了
                    // 提供了对数器验证，你可以改成<=，对数器和leetcode都过不了
                    // 这里是<，对数器和leetcode都能通过
                    // 这里面会让同学们感到困惑的点：
                    // 为啥==的时候，不移动，只有<的时候，才移动呢？例子懂了，但是道理何在？
                    // 哈哈哈哈哈，看了邮局选址问题，你更懵，请看42节！
                    if (cur < ans) {
                        ans = cur;
                        bestChoose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                bestPos[i][j] = bestChoose;
            }
        }
        return dp[N - 1][K];
    }


    public static int getNeedParts(int[] arr, long aim) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > aim) {
                return Integer.MAX_VALUE;
            }
        }
        int parts = 1;
        int all = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (all + arr[i] > aim) {
                parts++;
                all = arr[i];
            } else {
                all += arr[i];
            }
        }
        return parts;
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 100;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int M = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(len, maxValue);
//            int M = 2;
//            int[] arr = new int[]{3, 5, 1, 4};
            int ans1 = splitArray3(arr, M);
            int ans2 = splitArray2(arr, M);

            if (ans1 != ans2) {
                System.out.print("arr : ");
                printArray(arr);
                System.out.println("M : " + M);
                System.out.println("ans1 : " + ans1);
                System.out.println("ans2 : " + ans2);
//                System.out.println("ans3 : " + ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }


}
