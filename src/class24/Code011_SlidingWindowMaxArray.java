package class24;

import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr.
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4.3,5,4,3,3,6,7. W = 3
 * 返回：「5.5.5.4.6.71
 */
public class Code011_SlidingWindowMaxArray {

    public static int[] right(int[] arr, int w) {
        int N = arr.length;

        int[] result = new int[N - w + 1];

        //构建最大值双端队列
        LinkedList<Integer> qmax = new LinkedList<>();

        int index = 0;
        for (int R = 0; R < N; R++) {
            //维护双端队列最大值
            while (!qmax.isEmpty() && arr[qmax.peekLast()] < arr[R]) {
                qmax.pollLast();
            }

            qmax.addLast(R);

            //判断是否过期，过期则弹出
            if (qmax.peekFirst() == R - w) {
                qmax.pollFirst();
            }

            //是否达到窗口条件，满足则记录结果
            if (R >= w - 1) {
                result[index++] = arr[qmax.peekFirst()];
            }

        }

        return result;
    }

}
