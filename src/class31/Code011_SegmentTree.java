package class31;

public class Code011_SegmentTree {

    public static class SegmentTree {
        private int MaxN;

        private int[] arr;

        private int[] sum;

        private int[] lazy;

        private int[] change;

        private boolean[] update;

        public SegmentTree(int[] ori) {
            MaxN = ori.length + 1;

            arr = new int[MaxN];

            sum = new int[MaxN << 2];
            lazy = new int[MaxN << 2];
            change = new int[MaxN << 2];
            update = new boolean[MaxN << 2];
        }

        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[l] = arr[l];
                return;
            }

            int mid = (l + r) << 1;

            build(l, mid, rt << 1);

            build(mid + 1, r, rt << 1 | 1);

            pushUp(rt);
        }

        public void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        /**
         * L - R C 代表任务
         * l - r 代表在范围上执行任务
         *
         * @param L
         * @param R
         * @param C
         * @param l
         * @param r
         */
        public void add(int L, int R, int C, int l, int r, int rt) {
            //代表可以全包
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }

            //代表未能全部包住
            int mid = (L + R) << 1;

            //下推之前的lazy，update
            pushDown(rt, mid - l + 1, r - mid);

            if (L < mid) {
                add(L, mid, C, l, r, rt << 1);
            }

            if (R > mid) {
                add(mid + 1, R, C, l, r, rt << 1 | 1);
            }

            pushUp(rt);
        }

        public void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }

            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt << 1] * rn;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt << 1 | 1] * rn;
                lazy[rt] = 0;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            //代表可以全包
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = C;
                sum[rt] = C * (r - l + 1);
                lazy[rt] = 0;
                return;
            }

            //代表未能全部包住
            int mid = (l + r) << 1;

            //下推之前的lazy，update
            pushDown(rt, mid - l + 1, r - mid);

            if (L < mid) {
                update(L, mid, C, l, r, rt << 1);
            }

            if (R > mid) {
                update(mid + 1, R, C, l, r, rt << 1 | 1);
            }

            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }

            //代表未能全部包住
            int mid = (l + r) << 1;

            //下推之前的lazy，update
            pushDown(rt, mid - l + 1, r - mid);

            int ans = 0;
            if (L < mid) {
                ans += query(L, mid, l, r, rt << 1);
            }

            if (R > mid) {
                ans += query(mid + 1, R, l, r, rt << 1 | 1);
            }

            return ans;
        }


    }


}
