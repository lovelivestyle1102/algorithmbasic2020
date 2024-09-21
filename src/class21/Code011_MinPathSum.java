package class21;

/**
 *
 * 给定一个二维数组matrix,一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数宇都累加就是距离累加和
 * 返回最小距离累加和
 *
 */
public class Code011_MinPathSum {

	public static int getMinPathSum(int[][] matrix){
		int N = matrix.length;
		int M = matrix[0].length;

		int[][] dp = new int[N][M];

		dp[0][0] = matrix[0][0];

		for (int i = 1; i < M; i++) {
			dp[0][i] = dp[0][i-1]+matrix[0][i];
		}

		for (int i = 1; i < N; i++) {
			dp[i][0] = dp[i-1][0]+matrix[i][0];
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j])+matrix[i][j];
			}
		}

		return dp[N-1][M-1];
	}


	/**
	 * 空间压缩
	 *
	 * @param matrix
	 * @return
	 */
	public static int getMinPathSum2(int[][] matrix){
		int N = matrix.length;
		int M = matrix[0].length;

		int[] dp = new int[M];

		dp[0] = matrix[0][0];
		for (int i = 1; i < M; i++) {
			dp[i] = dp[i-1] + matrix[0][i];
		}

		for (int i = 1; i < N; i++) {
			dp[0] = dp[0]+matrix[i][0];
			for (int j = 1; j < M; j++) {
				dp[j] = Math.min(dp[j-1],dp[j])+matrix[i][j];
			}
		}

		return dp[M-1];
	}




}
