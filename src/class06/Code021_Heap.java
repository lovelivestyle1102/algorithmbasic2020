package class06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code021_Heap {

    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("栈已经满!");
            }

            heap[heapSize] = value;

            heapInsert(heap, heapSize++);
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while(left < heapSize){
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left]? left + 1 : left;
                largest = arr[largest] > arr[index]?largest : index;
                if(largest == index){
                   continue;
                }
                swap(arr,index,largest);
                index = largest;
                left = index * 2 + 1;
            }
        }

        // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆组织
        public int pop() {
            if(isEmpty()){
                throw new RuntimeException("栈已经空!");
            }
            int temp = heap[0];
            swap(heap,0,--heapSize);
            heapify(heap,0,heapSize);
            return temp;
        }

        private void swap(int[] arr, int p, int q) {
            int temp = arr[p];
            arr[p] = arr[q];
            arr[q] = temp;
        }

    }

}
