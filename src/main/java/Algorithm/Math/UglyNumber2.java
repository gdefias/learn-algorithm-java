package Algorithm.Math;
/**
 * Created with IntelliJ IDEA.
 * Description: 丑数 II
 * User: Defias
 * Date: 2018-10

  https://leetcode.cn/problems/ugly-number-ii/
  https://leetcode-cn.com/problems/chou-shu-lcof/

 编写一个程序，找出第 n 个丑数。
 丑数就是质因数只包含 2, 3, 5 的正整数。

 输入: n = 10
 输出: 12
 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。

 1 是丑数。
 n 不超过1690。
 */
public class UglyNumber2 {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(7));
    }


    //DP 动态规划 + 三指针（从小到大取数）
    public static int nthUglyNumber(int n) {
        int p1 = 1;
        int p2 = 1;
        int p3 = 1;

        // dp[i]表示第i个丑数，第n个丑数即为dp[n]
        int[] dp = new int[n+1];
        dp[1] = 1;  //第一个丑数是1

        for(int i=2; i<=n; i++) {
            int num1 = 2*dp[p1];
            int num2 = 3*dp[p2];
            int num3 = 5*dp[p3];
            dp[i] = Math.min(Math.min(num1, num2), num3);

            if(dp[i] == num1) {
                p1++;
            }

            if(dp[i] == num2) {
                p2++;
            }

            if(dp[i] == num3) {
                p3++;
            }
        }
        return dp[n];
    }
}
