package Questions.DP;

/**
 * Created by Defias on 2020/07.
 * Description:   青蛙跳台阶问题

 https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/

 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 输入：n = 2
 输出：2

 输入：n = 7
 输出：21

 0 <= n <= 100
 */
public class NumWays {

    // DP
    // dp[i] = dp[i-1] + dp[i-2]
    public int numWays(int n) {
        if(n<=1) {
            return 1;
        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }


    // DP 空间优化
    public int numWays2(int n) {
        if(n<=1) {
            return 1;
        }

        int prevv = 1;
        int prev = 2;
        int cur = 2;

        for(int i=3; i<=n; i++) {
            cur = (prev + prevv)%1000000007;
            prevv = prev;
            prev = cur;
        }

        return cur;
    }
}
