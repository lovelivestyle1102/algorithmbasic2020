package class27;

public class Code012_KMP {

    public static int getIndexOf(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int[] nextArray = getNextArray(chars2);

        int i = 0, j = 0;

        while (i < chars1.length && j < chars2.length) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else if (nextArray[j] == -1) {
                i++;
            } else {
                j = nextArray[j];
            }
        }
        return j == chars2.length ? i - j : -1;
    }

    public static int[] getNextArray(char[] str) {
        int[] res = new int[str.length];

        res[0] = -1;
        res[1] = 0;

        int cn = 0;
        for (int i = 2; i < str.length; i++) {
            if (str[cn] == str[i - 1]) {
                res[i] = ++cn;
            } else if (cn > 0) {
                cn = res[cn];
            } else {
                res[i] = 0;
            }
        }

        return res;
    }

}
