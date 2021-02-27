package Algorithm.DP;
import java.util.Arrays;
/**
 * Created by Defias on 2020/07.
 * Description: 零钱兑换

 https://leetcode-cn.com/problems/coin-change/

 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 如果没有任何一种硬币组合能组成总金额，返回 -1。
  

 输入: coins = [1, 2, 5], amount = 11
 输出: 3
 解释: 11 = 5 + 5 + 1

 输入: coins = [2], amount = 3
 输出: -1

 可以认为每种硬币的数量是无限的。
 */

public class CoinChange {
    public static int ans = Integer.MAX_VALUE;  //所需的最少硬币数量

    public static void main(String[] args) {
        int[] coins = new int[] {1,2,5};
        System.out.println(coinChange2(coins, 11));
    }

    // DP  ---自下而上   时间复杂度：O(Sn) S是金额，n是面额数     空间复杂度：O(S)
    // F(S)=min(F(S−C)) +1
    // F(S)：组成金额S所需的最少硬币数量   最后一枚硬币的面值是C
    // dp[am]: 组成金额am所需的最少硬币数量
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);  //初始化为一个不可能取到的最大数量amount+1
        dp[0] = 0;
        for(int am = 1; am<=amount; am++) {  //自下而上遍历金额
            for(int i=0; i<coins.length; i++) {  //遍历不同的面额
                if(coins[i]<=am) {
                    dp[am] = Math.min(dp[am], dp[am-coins[i]] + 1);
                }
            }
        }
        return dp[amount]==amount+1? -1: dp[amount];
    }

    //递归   时间复杂度：O(S^n)  空间复杂度 O(n)
    //枚举每个硬币数量子集[X_0~X_(n-1)]
    //X_i的取值范围为[0, S/C_i]，S 是总金额，C_i是第 i 枚硬币的面值，X_i是面值为C_i的硬币数量
    public static int coinChange1(int[] coins, int amount) {
        int index = 0;
        return coinChange1(coins, amount, index);
    }

    public static int coinChange1(int[] coins, int amount, int index) {
        if(amount == 0) {
            return 0;
        } else if(index >= coins.length && amount != 0) {
            return -1;
        }

        int coin = coins[index];
        int maxcurr = amount / coin;
        int mincount = Integer.MAX_VALUE;

        for(int i=0; i<=maxcurr; i++) {
            int others = coinChange1(coins, amount-coin*i, index+1);
            if(others != -1) {
                mincount = Math.min(mincount, others+i);
            }
        }

        return mincount==Integer.MAX_VALUE? -1 : mincount;
    }



    //dfs
    public static int coinChange2(int[] coins, int amount) {
        Arrays.sort(coins);  //升序
        int index = coins.length-1;  //从最大面额的开始算
        int cnt = 0;  //所需的硬币数量
        dfs(coins, amount, index, cnt);
        return ans==Integer.MAX_VALUE? -1: ans;
    }

    public static void dfs(int[] coins, int amount, int index, int cnt) {
        if(index<0) {
            return;
        }

        for(int c = amount/coins[index]; c>=0; c--) {
            cnt = cnt + c;  //累加所需的硬币数量

            int na = amount - (c * coins[index]);
            if(na == 0) {  //大面额刚好整除
                ans = Math.min(ans, cnt);
                break;  //剪枝
            }

            if(cnt+1 >= ans) {  //大面额不整除时如果增加硬币数量会大于已得到最少数量就没必要继续算了
                break;  //剪枝
            }

            dfs(coins, na, index-1, cnt);   //转到次大面额

        }
        return;
    }
}
