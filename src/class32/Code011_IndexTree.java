package class32;

public class Code011_IndexTree {

    // 下标从1开始！
    public static class IndexTree {

        private int[] tree;

        private int N;

        // 0位置弃而不用！
        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1];
        }

        public int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }

        public void add(int index, int d) {
            while (index < N) {
                tree[index] += d;
                index += index & -index;
            }
        }
    }


}
