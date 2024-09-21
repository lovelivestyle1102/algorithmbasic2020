package sorts;

public class InsertSort extends Example {
    @Override
    public void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        Comparable[] comparables = {1, 4, 6, 2, 8, 3, 9, 5};
        insertSort.sort(comparables);
        insertSort.show(comparables);
    }
}
