package class31;

public class Code012_SegmentTree {

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

            int mid = (l + r) >> 1;

            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        public void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                change[rt << 1] = change[rt];
                sum[rt << 1] = change[rt] * ln;
                lazy[rt << 1] = 0;

                update[rt << 1 | 1] = true;
                change[rt << 1 | 1] = change[rt];
                sum[rt << 1 | 1] = change[rt] * rn;
                lazy[rt << 1 | 1] = 0;

                update[rt] = false;
            }

            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] = sum[rt << 1] + lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] = sum[rt << 1 | 1] + lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                sum[rt] += (r - l + 1) * C;
                lazy[rt] += C;
                return;
            }

            int mid = (l + r) >> 1;

            pushDown(rt, mid - l + 1, r - mid);

            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }

            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }

            pushUp(rt);
        }


        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = C;
                sum[rt] = (R - L) * C;
                lazy[rt] = 0;
                return;
            }

            int mid = (l + r) >> 1;

            pushDown(rt, mid - l, r - mid + 1);

            if (L < mid) {
                update(L, R, C, l, mid, rt << 1);
            }

            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }

            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }

            int mid = (l + r) >> 1;

            pushDown(rt, mid - l + 1, r - mid);

            int ans = 0;

            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }

            if (L < mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }

            return ans;
        }


    }


}
