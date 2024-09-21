package sorts;

public class MergeSort extends Example {
    private Comparable[] help;

    @Override
    public void sort(Comparable[] a) {
        help = new Comparable[a.length];

        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) >> 1;

        sort(a, left, mid);

        sort(a, mid + 1, right);

        merge(a, left, mid, right);
    }

    private void merge(Comparable[] a, int left, int mid, int right) {
//        int i = left;
//        int j = mid + 1;
//        int index = 0;
//        while (i < mid && j < right) {
//            help[index++] = less(a[i], a[j]) ? a[j++] : a[i++];
//        }
//
//        while (i <= mid) {
//            help[index++] = a[i++];
//        }
//
//        while (j < right) {
//            help[index++] = a[j++];
//        }

        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if(i > mid) {
                a[k] = help[j++];
            }else if(j > right){
                a[k] = help[i++];
            }else if(less(a[j],a[i])){
                a[k] = help[j++];
            }else{
                a[k] = help[i++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        Comparable[] comparables = {1, 4, 6, 2, 8, 3, 9, 5};
        mergeSort.sort(comparables);
        mergeSort.show(comparables);
    }
}
