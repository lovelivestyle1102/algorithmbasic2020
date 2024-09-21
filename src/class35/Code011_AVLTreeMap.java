package class35;

public class Code011_AVLTreeMap {

    public static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public AVLNode<K, V> l;
        public AVLNode<K, V> r;
        public int h;

        public AVLNode(K key, V value) {
            k = key;
            v = value;
            h = 1;
        }
    }

    public static class AVLTreeMap<K extends Comparable<K>, V> {
        private AVLNode<K, V> root;
        private int size;

        public AVLTreeMap() {
            root = null;
            size = 0;
        }


        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
            left.h = Math.max(left.l == null ? 0 : left.l.h, left.r == null ? 0 : left.r.h) + 1;
            return left;
        }

        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
            right.h = Math.max(right.l == null ? 0 : right.l.h, right.r == null ? 0 : right.r.h) + 1;
            return right;
        }

        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }

            int lH = cur.l == null ? 0 : cur.l.h;
            int rH = cur.r == null ? 0 : cur.r.h;

            //说明已经破坏了平衡
            if (Math.abs(lH - rH) > 1) {
                if (lH > rH) {
                    int leftleftheight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                    int leftrightheight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
                    if (leftleftheight >= leftrightheight) {
                        //LL
                        cur = rightRotate(cur);
                    } else {
                        //LR
                        cur.l = leftRotate(cur.l);
                        cur = rightRotate(cur);
                    }
                } else {
                    int rightrightheight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                    int rightleftheight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
                    if (rightrightheight >= rightleftheight) {
                        //RR
                        cur = leftRotate(cur);
                    } else {
                        //RL
                        cur.r = rightRotate(cur.r);
                        cur = leftRotate(cur);
                    }
                }
            }

            return cur;
        }

        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<>(key, value);
            } else {
                if (cur.k.compareTo(key) > 0) {
                    cur.l = add(cur.l, key, value);
                } else {
                    cur.r = add(cur.r, key, value);
                }
                cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
                return maintain(cur);
            }
        }

        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            if(cur == null){
                return null;
            }

            if(cur.k.compareTo(key) > 0){
                 delete(cur.l,key);
            }else{
                 delete(cur.l,key);
            }

            return maintain(cur);
        }
    }

}
