package sorts;

public class ShellSort extends Example {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        Comparable[] comparables = {1, 4, 6, 2, 8, 3, 9, 5};
        shellSort.sort(comparables);
        shellSort.show(comparables);
    }
}
