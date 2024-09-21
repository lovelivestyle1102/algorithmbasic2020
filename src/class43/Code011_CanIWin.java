package class43;

// leetcode 464题
public class Code011_CanIWin {

    // 1~choose 拥有的数字
    // total 一开始的剩余
    // 返回先手会不会赢
    public static boolean canIWin0(int choose, int total) {

        int[] items = new int[choose + 1];

        for (int i = 1; i <= choose; i++) {
            items[i] = i;
        }

        return process(items, total);
    }

    public static boolean process(int[] items, int rest) {
        if (rest <= 0) {
            return false;
        }

        for (int i = 1; i < items.length; i++) {
            if (items[i] != 0) {
                int cur = items[i];
                items[i] = 0;
                boolean res = process(items, rest - cur);
                items[i] = cur;
                if (!res) {
                    return true;
                }
            }
        }
        return false;
    }


}
