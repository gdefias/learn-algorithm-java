package Algorithm.DP;

/**
 * Created by Defias on 2020/07.
 * Description:  零钱兑换 II

 https://leetcode-cn.com/problems/coin-change-2/
 https://leetcode-cn.com/problems/coin-change-2/solution/bei-bao-si-xiang-jie-jue-ling-qian-dui-huan-wen-ti/

 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 

 输入: amount = 5, coins = [1, 2, 5]
 输出: 4
 解释: 有四种方式可以凑成总金额:
 5=5
 5=2+2+1
 5=2+1+1+1
 5=1+1+1+1+1

 输入: amount = 3, coins = [2]
 输出: 0
 解释: 只用面额2的硬币不能凑成总金额3。

 输入: amount = 10, coins = [10]
 输出: 1
  
 0 <= amount (总金额) <= 5000
 1 <= coin (硬币面额) <= 5000
 硬币种类不超过 500 种
 结果符合 32 位符号整数
 */

public class CoinChange2 {
    public static int count = 0;

    public static void main(String[] args) {
        int[] coins = new int[] {1,2,5};
        int amount = 5;
        System.out.println(change1st(amount, coins));
    }

    //DP
    public static int change1st(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[0][0] = 1;   //dp[i][j]: 前i种硬币组成总金额j的组合数

        for (int i = 1; i <= n; i++) {    //1~n种硬币
            for (int j = 0; j <= amount; j++) {  //0~amount总金额
                for (int k = 0; k * coins[i-1] <= j; k++) {  //每种硬币的数量从0开始取
                    dp[i][j] += dp[i-1][j - k*coins[i-1]];
                }
            }
        }
        return dp[n][amount];
    }


    //DP
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;  //滚动数组 无硬币时总金额为0的组合数为1

        for (int i=0; i<coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }


    //dfs
    public static int change1(int amount, int[] coins) {
        if(amount<0 || coins==null || (amount>0 && coins.length==0)) {
            return 0;
        }
        if(amount==0 && coins.length==0) {
            return 1;
        }

        int index = 0;
        dfs(coins, amount, index);

        return count;
    }

    public static void dfs(int[] coins, int amount, int index) {
        if(index>=coins.length) {
            return;
        }

        int coin = coins[index];
        int i;
        for(i=amount/coin; i>=0; i--) {
            int na = amount - (i * coin);
            if( na == 0 ) {
                count++;
                continue;
            }
            dfs(coins, na, index+1);
        }
    }
}
