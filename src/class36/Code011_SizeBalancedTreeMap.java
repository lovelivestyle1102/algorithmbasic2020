package class36;

public class Code011_SizeBalancedTreeMap {

    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;

        public SBTNode<K, V> l;
        public SBTNode<K, V> r;

        public int size; // 不同的key的数量

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
        private SBTNode<K, V> root;

        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode left = cur.l;
            left.r = cur;
            cur.l = left;
            left.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r != null ? cur.r.size : 0);
            return left;
        }

        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r != null ? cur.r.size : 0);
            return right;
        }

        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            int leftSize = cur != null && cur.l != null ? cur.l.size : 0;
            int rightSize = cur != null && cur.r != null ? cur.r.size : 0;

            int leftleftsize = cur != null && cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftrightsize = cur != null && cur.l != null && cur.l.r != null ? cur.l.r.size : 0;

            int rightrightsize = cur != null && cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            int rightleftsize = cur != null && cur.r != null && cur.r.l != null ? cur.r.l.size : 0;

            //LL
            if (leftleftsize > rightSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }

            //LR
            if (leftrightsize > rightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }

            //RR
            if (rightrightsize > leftSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }

            //RL
            if (rightleftsize > leftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }

            return cur;
        }


        // 现在，以cur为头的树上，新增，加(key, value)这样的记录
        // 加完之后，会对cur做检查，该调整调整
        // 返回，调整完之后，整棵树的新头部
        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SBTNode<>(key, value);
            }

            cur.size++;
            if (key.compareTo(cur.key) < 0) {
                cur.l = add(cur.l, key, value);
            } else {
                cur.r = add(cur.r, key, value);
            }

            return maintain(cur);
        }

        // 在cur这棵树上，删掉key所代表的节点
        // 返回cur这棵树的新头部
        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) < 0) {
                cur.l = delete(cur.l, key);
            } else if (key.compareTo(cur.key) > 0) {
                cur.r = delete(cur.r, key);
            } else {
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                } else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                } else {
                    SBTNode<K, V> pre = null;
                    SBTNode<K, V> des = cur.r;
                    des.size--;
                    while (des.l != null) {
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    if (pre != null) {
                        pre.l = des.r;
                        des.r = cur.r;
                    }
                    des.l = cur.l;
                    des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
                    // free cur memory -> C++
                    cur = des;
                }
            }

//            maintain(cur);

            return cur;
        }

    }

}
