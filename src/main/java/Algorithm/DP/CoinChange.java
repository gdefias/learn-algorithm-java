package Algorithm.DP;
import java.util.Arrays;
/**
 * Created by Defias on 2020/07.
 * Description: 零钱兑换

 https://leetcode-cn.com/problems/coin-change/

 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 如果没有任何一种硬币组合能组成总金额，返回-1。
 

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
        System.out.println(coinChange3(coins, 11));
    }

    //方法1：递归
    //时间复杂度：O(S^n)
    //空间复杂度 O(n)
    //思路：枚举每个硬币数量，取值范围为[0, S/C_i]，S是总金额，C_i是第i枚硬币的面值
    public static int coinChange(int[] coins, int amount) {
        int index = 0;
        return coinChange(coins, amount, index);
    }

    public static int coinChange(int[] coins, int amount, int index) {
        if(amount == 0) {
            return 0;
        } else if(index >= coins.length && amount != 0) {
            return -1;
        }

        int ans = Integer.MAX_VALUE;
        int coin = coins[index];
        int maxcurr = amount / coin;  //最多maxcurr枚面值为coin的硬币
        for(int i=0; i<=maxcurr; i++) {
            int others = coinChange(coins, amount-coin*i, index+1);
            if(others != -1) {
                ans = Math.min(ans, others+i);
            }
        }

        return ans==Integer.MAX_VALUE? -1 : ans;
    }

    //方法2：DP 动态规划 记忆法搜索  自上而下
    //时间复杂度：O(Sn) S是金额，n是面额数
    //空间复杂度：O(S)
    //memo[n] 表示钱币n可以被换取的最少的硬币数，不能换取就为−1
    public static int coinChange2(int[] coins, int amount) {
        int[] memo = new int[amount+1];
        Arrays.fill(memo, -2);
        memo[0] = 0;

        return coinChange2(coins, amount, memo);
    }

    public static int coinChange2(int[] coins, int amount, int[] memo) {
        if(amount < 0) {
            return -1;
        } else if(amount == 0){
            return 0;
        }

        if(memo[amount] != -2) {
            return memo[amount];
        }

        int min = Integer.MAX_VALUE;
        for(int coin: coins) {
            int num = coinChange2(coins, amount-coin, memo);
            if(num>=0 && num+1<min) {
                min = num+1;
            }
        }

        memo[amount] = (min==Integer.MAX_VALUE) ? -1 : min;
        return  memo[amount];
    }


    //方法3：DP 动态规划  自下而上
    //时间复杂度：O(Sn) S是金额，n是面额数
    //空间复杂度：O(S)
    //转移方程： F(S) = min(F(S−C)) +1
    //F(S): 组成金额S所需的最少硬币数量   最后一枚硬币的面值是C
    //memo[n] 表示钱币n可以被换取的最少的硬币数，不能换取就为−1
    public static int coinChange3(int[] coins, int amount) {
        int[] memo = new int[amount+1];

        Arrays.fill(memo, amount+1);  //初始化为一个不可能取到的最大数量amount+1
        memo[0] = 0;

        for(int am=1; am<=amount; am++) {  //自下而上遍历金额
            for(int i=0; i<coins.length; i++) {  //遍历不同的面额
                if(coins[i]<=am) {
                    // memo[am]有两种情况：
                    // 包含当前的coins[i],那么剩余钱就是am-coins[i],这种操作要兑换的硬币数是 memo[am-coins[i]] + 1
                    // 不包含当前的coins[i]，要兑换的硬币数是memo[am]
                    memo[am] = Math.min(memo[am], memo[am-coins[i]]+1);
                }
            }
        }
        return memo[amount]==amount+1? -1: memo[amount];
    }



    //方法4：DFS
    public static int coinChange4(int[] coins, int amount) {
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

        for(int c=amount/coins[index]; c>=0; c--) {
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
