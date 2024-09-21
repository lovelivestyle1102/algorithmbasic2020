package class40;

/**
 * 给定一个正整数组成的无序数組arr给定一个正整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 */
public class Code011_LongestSumSubArrayLengthInPositiveArray {


    public static int getLongestSumSubArrayLength(int[] arr, int K) {
        int N = arr.length;
        int R = 0;
        int L = 0;
        int sum = arr[0];
        int LMAX = 0;
        while (R < N) {
            if (sum == K) {
                LMAX = Math.max(LMAX, R - L + 1);
                sum -= arr[L++];
            } else if (sum < K) {
                R++;
                if (R == arr.length) {
                    break;
                }
                sum += arr[R];
            } else {
                sum -= arr[L++];
            }
        }

        return LMAX;
    }

}
