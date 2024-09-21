package class27;

public class Code011_KMP {

    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0;
        int y = 0;

        // O(M) m <= n
        int[] next = getNextArray(str2);

        while (x < s1.length() && y < s2.length()) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }

        return y == s2.length() ? x - y : -1;
    }

    public static int[] getNextArray(char[] str) {
        if (str.length == 1) {
            return new int[]{-1};
        }

        int[] res = new int[str.length];

        res[0] = -1;
        res[1] = 0;
        int i = 2;
        int cn = 0;

        while (i < str.length) {
            if (str[i - 1] == str[cn]) {
                res[i++] = ++cn;
            } else if (cn > 0) {
                cn = res[cn];
            } else {
                res[i++] = 0;
            }
        }

        return res;
    }

}
