package Questions.DP;

/**
 * Created by Defias on 2020/07.
 * Description: 最佳买卖股票时机含冷冻期
 *
 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。


 输入: [1,2,3,0,2]
 输出: 3
 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */

public class BestTimetoBuyandSellStockCooldown {

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        System.out.println(maxProfit(prices));

    }

    /**
     * DP
     * 这题加了个冷冻期，冷冻期只有在不持有股票时才有
     *
     * dp[i][0]: 第i天手上持有股票 的累计最大利润
     * dp[i][1]: 第i天手上不持有股票并且处于冷冻期中 的累计最大利润
     * dp[i][2]: 第i天手上不持有股票并且不在冷冻期中 的累计最大利润
     *
     *
     * */
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if(prices==null || len<2) {
            return 0;
        }

        int[][] dp = new int[len][3];

        dp[0][0] = -prices[0];

        for(int i=1; i<len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2]-prices[i]);
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
        }

        return Math.max(dp[len-1][1], dp[len-1][2]);
    }

    //DP - 状态压缩
    public static int maxProfit_1(int[] prices) {
        int len = prices.length;
        if(prices==null || len<2) {
            return 0;
        }

        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i = 1; i < len; ++i) {
            int newf0 = Math.max(f0, f2 - prices[i]);
            int newf1 = f0 + prices[i];
            int newf2 = Math.max(f1, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }

        return Math.max(f1, f2);
    }
}
