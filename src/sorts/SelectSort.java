package sorts;

public class SelectSort extends Example{

    @Override
    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int change = i;
            for (int j = i+1; j < a.length; j++) {
                if(less(a[j],a[change])){
                    change = j;
                }
            }
            exchange(a,i,change);
        }
    }


    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        Comparable[] comparables = {1,4,6,2,8,3,9,5};
        selectSort.sort(comparables);
        selectSort.show(comparables);
    }
}
