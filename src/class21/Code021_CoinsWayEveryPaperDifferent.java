package class21;

/***
 *
 * arr是货币数组，其中的值都是正数。再给定一个正数aim
 * 每个值都认为是一张货币，即便是值相同的货币也认为每一张都是不同的，
 * •返回组成aim的方法数
 * 例如：arr = [1,1,1], aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 *
 *
 *
 */
public class Code021_CoinsWayEveryPaperDifferent {

	public static int coinWays(int[] arr, int aim) {
		return process(arr, 0, aim);
	}

	/**
	 *
	 * 从左到右的尝试模型
	 *
	 * @param arr
	 * @param index
	 * @param rest
	 * @return
	 */
	public static int process(int[] arr,int index, int rest){
//		此时将只会统计靠前面的方法，后面的将无法尝试
//		if(rest == 0){
//			return 1;
//		}

		if (rest < 0) {
			return 0;
		}

		if (index == arr.length) { // 没钱了！
			return rest == 0 ? 1 : 0;
		} else {
			return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
		}
	}

}
