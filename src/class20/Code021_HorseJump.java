package class20;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，然后把整个棋盘放入第一象限，
 * 棋盘的最左下角是(0,0)位置那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数x，y，k
 * 返回“马”从(0.0位置出发，必须走k步最后落在(x》)上的方法数有多少种？
 */
public class Code021_HorseJump {


    public static int jump(int x, int y, int k) {
        return process(0, 0, x, y, k);
    }

    public static int process(int a, int b, int x, int y, int rest) {
        if(a < 0 || a > 9 || b < 0 || b > 10){
            return 0;
        }
        if (rest == 0) {
            return a == x && b == y ? 1 : 0;
        }

        int ways = process(a+1,b+2,x,y,rest-1);
        ways += process(a+2,b+1,x,y,rest-1);
        ways += process(a+2,b-1,x,y,rest-1);
        ways += process(a+1,b-2,x,y,rest-1);
        ways += process(a-1,b-2,x,y,rest-1);
        ways += process(a-2,b-1,x,y,rest-1);
        ways += process(a-2,b-1,x,y,rest-1);
        ways += process(a-1,b+2,x,y,rest-1);
        return ways;
    }


//
//	public static void main(String[] args) {
//		int x = 7;
//		int y = 7;
//		int step = 10;
//		System.out.println(ways(x, y, step));
//		System.out.println(dp(x, y, step));
//
//		System.out.println(jump(x, y, step));
//	}
}
