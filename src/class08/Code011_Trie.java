package class08;

public class Code011_Trie {

    // 测试链接 : https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
    // 提交Trie类可以直接通过
    // 原来代码是对的，但是既然找到了直接测试的链接，那就直接测吧
    // 这个链接上要求实现的功能和课上讲的完全一样
    // 该前缀树的路用数组实现
    class Trie {

        class Node {
            public int pass;
            public int end;
            public Node[] nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public boolean insert(String target) {
            if (target == null || "".equals(target)) {

            }
            Node parent = root;
            parent.pass++;
            char[] chars = target.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (parent.nexts[path] == null) {
                    Node node = new Node();
                    parent.nexts[path] = node;
                }
                parent.nexts[path].pass++;
                parent = parent.nexts[path];
            }

            parent.end++;

            return true;
        }

        public boolean query(String target) {
            Node parent = root;

            char[] chars = target.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (parent.nexts[path] == null) {
                    return false;
                }
                parent = parent.nexts[path];
            }
            return parent.end > 0;
        }

        public int querySamePath(String target) {
            Node parent = root;

            char[] chars = target.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (parent.nexts[path] == null) {
                    return 0;
                }
                parent = parent.nexts[path];
            }
            return parent.pass;
        }

    }

}
