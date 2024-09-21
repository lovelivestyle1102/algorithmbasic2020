package class38;

/**
 * intDd， d0：i号怪兽的能力
 * intDp，d1：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力小于i号怪兽的能力，你必须付出p口的钱，贿赂这个怪兽，然后
 * 个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
 * 返回通过所有的怪兽，需要花的最小钱数。
 */
public class Code041_MoneyProblem {

    // int[] d d[i]：i号怪兽的武力
    // int[] p p[i]：i号怪兽要求的钱
    // ability 当前你所具有的能力
    // index 来到了第index个怪兽的面前

    public static int spanMoney(int[] d, int[] p) {
        return process(d, p, 0, 0);
    }

    public static int process(int[] d, int[] p, int index, int ability) {
        if (index == d.length) {
            return 0;
        }

        //能力不满足必须贿赂
        if (ability < d[index]) {
            return p[index] + process(d, p, index + 1, ability + d[index]);
        } else {
            return Math.min(
                    p[index] + process(d, p, index + 1, ability + d[index]),
                    process(d, p, index + 1, ability)
            );
        }
    }

    public static int spanMoney2(int[] d, int[] p) {
        int[][] dp = new int[d.length + 1][d.length + 1];

        for (int i = 0; i <= d.length; i++) {
            for (int j = 0; j <= d.length; j++) {
                dp[i][j] = -1;
            }
        }

        return process2(d, p, 0, 0, dp);
    }

    public static int process2(int[] d, int[] p, int index, int ability, int[][] dp) {
        if (dp[index][ability] != -1) {
            return dp[index][ability];
        }

        if (index == d.length) {
            return 0;
        }

        int ans = 0;

        //能力不满足必须贿赂
        if (ability < d[index]) {
            ans = p[index] + process2(d, p, index + 1, ability + d[index], dp);
        } else {
            ans = Math.min(
                    p[index] + process2(d, p, index + 1, ability + d[index], dp),
                    process2(d, p, index + 1, ability, dp)
            );
        }

        dp[index][ability] = ans;

        return ans;
    }

    public static int spanMoney3(int[] d, int[] p) {
        int[][] dp = new int[d.length][d.length];
        for (int i = d.length - 1; i >= 0; i--) {
            for (int j = 0; j <= d.length; j++) {
                int ans = 0;
                if (j < d[i]) {
                    ans = p[i] + dp[i + 1][j + d[i]];
                } else {
                    ans = Math.min(
                            p[i] + dp[i + 1][j + d[i]], dp[i + 1][j]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][0];
    }

}
