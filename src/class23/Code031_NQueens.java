package class23;

/**
 *
 * 题目三
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，也不在同一条斜线上
 * 给定-n=1，
 * 菠斷数n。返回1皇后的摆法有多公种。
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 *
 */
public class Code031_NQueens {

	public static int num1(int n) {
		if (n < 1) {
			return 0;
		}
		int[] record = new int[n];
		return process1(0, record, n);
	}

	public static int process1(int index,int[] record,int rest){
		if(index == record.length){
			return 1;
		}

		int res = 0;
		for (int i = 0; i < rest; i++) {
			if(valid(index,i,record)){
				record[index] = i;
				res += process1(index + 1,record,rest);
			}
		}
		return res;
	}

	private static boolean valid(int index, int i, int[] record) {
		for (int j = 0; j < record.length; j++) {
			if(record[j]==i || Math.abs(record[j] - i) == Math.abs(index - j)){
				return false;
			}
		}

		return true;
	}
}
