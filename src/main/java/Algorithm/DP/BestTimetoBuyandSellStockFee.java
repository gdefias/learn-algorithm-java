package Algorithm.DP;

/**
 * Created by Defias on 2020/07.
 * Description:
 *
 * 买卖股票的最佳时机含手续费
 * 无限次买入卖出  但是需要收取交易费（买入卖出只收一次）
 *
 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 返回获得利润的最大值。

 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 输出: 8
 解释: 能够达到的最大利润:
 在此处买入 prices[0] = 1
 在此处卖出 prices[3] = 8
 在此处买入 prices[4] = 4
 在此处卖出 prices[5] = 9
 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

 注意:
 0 < prices.length <= 50000.
 0 < prices[i] < 50000.
 0 <= fee < 50000.
 */


public class BestTimetoBuyandSellStockFee {

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(prices, 2));

    }

    //DP
    //时间复杂度：O(N)
    //空间复杂度：O(1)
    public static int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        if(prices==null || len<2) {
            return 0;
        }

        int[][] dp = new int[len][2];

        dp[0][0] = 0;  //第一天什么都不做 利润为0
        dp[0][1] = -prices[0]-fee;  //第一天买入 利润为负数且还需支付手续费  （第一天不可能卖出  规定在买入股票的时候扣除手续费）

        for(int i=1; i<=len-1; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]-fee);
        }


        //最后一天不持有股票的累计最大利润  因为一定有 dp[len-1][0] > dp[len-1][1]
        return dp[len-1][0];
    }

    //DP - 状态压缩
    public static int maxProfit_1(int[] prices, int fee) {
        int len = prices.length;
        if(prices==null || len<2) {
            return 0;
        }

        int[] dp = new int[2];

        dp[0] = 0;  //第一天什么都不做 利润为0
        dp[1] = -prices[0]-fee;  //第一天买入 利润为负数且还需支付手续费  （第一天不可能卖出）

        for(int i=1; i<=len-1; i++) {
            dp[0] = Math.max(dp[0], dp[1]+prices[i]);
            dp[1] = Math.max(dp[1], dp[0]-prices[i]-fee);
        }

        return dp[0];
    }
}
