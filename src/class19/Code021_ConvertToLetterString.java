package class19;

/**
 * 规定1和A对应、2和B对应、3和C对应…
 * 那么一个数字字符串比如"111” 就可以转化为：
 * “AAA"、"KA"和“AK”
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code021_ConvertToLetterString {

    public static void main(String[] args) {
//        String val = randomString(10);
        String val = "11111";
        int number2 = number2(val);
//        String val2 = "11111";
//        int number = number(val2);
//        System.out.println(number);
        System.out.println(number2);
    }

    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    public static int number2(String str) {
        char[] chars = str.toCharArray();

        int N = chars.length;

        int[] dp = new int[N + 1];

        for (int i = 0; i < N+1; i++) {
            dp[i] = -1;
        }

        return process2(chars, 0, dp);
    }

    public static int process2(char[] str, int index, int[] dp) {
        if (dp[index] != -1) {
            return dp[index];
        }

        int ans = 0;
        if (index == str.length) {
            ans = 1;
        } else if (str[index] == '0') {
            ans = 0;
        } else {
            int ways = process2(str, index + 1, dp);

            // (str[i] - '0') * 10 + str[i + 1] - '0' < 27
            // 俩个字符组合要小于等于26，超过就不能组成字符了
            if ((index + 1 < str.length) && (str[index] - '0') * 10 + str[index + 1] - '0' < 27) {
                ways += process2(str, index + 2, dp);
            }
            ans = ways;
        }

        dp[index] = ans;

        return ans;
    }


    public static int number(String str) {
        char[] chars = str.toCharArray();

        return process(chars, 0);
    }

    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }

        if (str[index] == '0') {
            return 0;
        }

        int ways = process(str, index + 1);

        // (str[i] - '0') * 10 + str[i + 1] - '0' < 27
        // 俩个字符组合要小于等于26，超过就不能组成字符了
        if ((index + 1 < str.length) && ((str[index] - '0') * 10 + str[index + 1] - '0') < 27) {
            ways += process(str, index + 2);
        }

        return ways;
    }

}
