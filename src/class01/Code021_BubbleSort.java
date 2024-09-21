package class01;

import java.util.Arrays;

public class Code021_BubbleSort {

	public static void bubbleSort(int[] arr) {
		int N = arr.length;
		for (int i = N-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if(arr[j]>arr[j+1]){
					swap(arr,j,j+1);
				}
			}
		}
	}

	// 交换arr的i和j位置上的值
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}


}
