package class24;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值一sub中最小值＜=num,返回arr中达标子数组的数量
 */
public class Code022_AllLessNumSubArray {


    public static int num(int[] arr, int sum) {
        int N = arr.length;

        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int R = 0;
        int count = 0;
        for (int L = 0; L < N; L++) {
            while (R < N) {
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }

                qmax.addLast(R);

                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }

                qmin.addLast(R);

                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > sum) {
                    break;
                } else {
                    R++;
                }
            }

            count += R - L;

            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }

            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
        }

        return count;
    }

}
