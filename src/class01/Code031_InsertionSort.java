package class01;

import java.util.Arrays;

public class Code031_InsertionSort {

	public static void insertionSort(int[] arr) {
		if(arr == null || arr.length < 2){
			return ;
		}
		int N = arr.length;
		for (int i = 1; i < N; i++) {
			for (int j = i-1; j >= 0 && arr[j]>arr[j+1]; j--) {
				swap(arr,j,j+1);
			}
		}
	}

	// i和j是一个位置的话，会出错
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}



}
