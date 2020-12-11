package Questions.Math;

/**
 * Created by Defias on 2020/07.
 * Description: 整数拆分
 *
 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

 输入: 2
 输出: 1
 解释: 2 = 1 + 1, 1 × 1 = 1。

 输入: 10
 输出: 36
 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。


 可以假设 n 不小于 2 且不大于 58。
 */


public class IntegerBreak {

    //贪心  根据数学推导： 将数字n尽可能以因子3等分时，乘积最大
    public static int integerBreak(int n) {
        if(n<=0) {
            return 0;
        }

        if(n<=3) {  //n≤3时，按照规则应不拆分，但由于题目要求必须拆分，因此必须拆出一个因子1 ，即返回n-1
            return n-1;
        }
        //n>3时，求n除以3的整数部分a和余数部分b （即n=3a+b），并分为以下三种情况：
        int a = n / 3;
        int b = n % 3;
        int res;
        if(b==0) {
            res = (int)Math.pow(3, a);
        } else if(b==1) {  //当b=1时，要将一个1+3转换为2+2
            res = (int)Math.pow(3, a-1) * 4;
        } else {
            res = (int)Math.pow(3, a) * 2;
        }

        return res;
    }


    //DP dp[i]: 数字i拆分为至少两个正整数之和的最大乘积
    public static int integerBreak1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {  //j代表着数字i可以拆分成 j + (i - j)
                dp[i] = max3(dp[i], j * dp[i-j], j * (i-j));
            }
        }
        return dp[n];
    }

    private static int max3(int num1, int num2, int num3) {
        return Math.max(Math.max(num1, num2), num3);
    }

    //DP优化
    public static int integerBreak2(int n) {
        int[] dp = {0, 1, 1};

        for (int i = 3; i < n + 1; i++) {
            dp[i % 3] = max3(Math.max(dp[(i - 1) % 3], i - 1), 2 * Math.max(dp[(i - 2) % 3], i - 2), 3 * Math.max(dp[(i - 3) % 3], i - 3));
        }
        return dp[n % 3];
    }

}
