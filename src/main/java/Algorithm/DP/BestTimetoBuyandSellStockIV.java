package Algorithm.DP;

import java.util.Arrays;

/**
 * Created by Defias on 2020/07.
 * Description: 买卖股票的最佳时机 IV

     https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/

     给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

     (隐含：在每一天，你可以决定是否购买和/或出售股票)

     输入: [2,4,1], k = 2
     输出: 2
     解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。

     输入: [3,2,6,5,0,3], k = 2
     输出: 7
     解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
         随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */


public class BestTimetoBuyandSellStockIV {

    public static void main(String[] args) {
        int[] prices = new int[] {3,2,6,5,0,3};
        System.out.println(maxProfit(2, prices));

    }

    /**
     * 方法1：DP
     * 这道题用动态规划完成，比之前的股票问题多一个限制，则有后效性，因此可以多设置一个状态去消除这种后效性。
     * “无后效性”有两层含义：（1）当前做的决策一旦确定，后面的决策不会影响到之前决策的结果；
     *                      （2）当前决策得到一个最优值，这个最优值怎么来的，后面的决策并不关心。
     *
     * dp[i][k][s] ：表示到第i天为止(从0开始)，已经交易了k次，并且当前持股状态为s的累计最大利润（s: 0 不持股  1 持股）
     * */
    public static int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if(prices==null || len<2 || k==0) {
            return 0;
        }

        int[][][] dp = new int[len][k+1][2];
        for(int j=0; j<=k; j++) {
            dp[0][j][1] = -prices[0];
            dp[0][j][0] = 0;
        }

        for(int i=1; i<len; i++) {
            for(int j=0; j<=k; j++) {
                if(j==0) {
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j][0]-prices[i]);
                    dp[i][j][0] = 0;
                } else {
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j][0]-prices[i]);
                    dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j-1][1]+prices[i]);
                }
            }
        }

        return dp[len-1][k][0];
    }

    //DP 空间优化/状态压缩
    //dp[k][s] ：已经交易了k次，并且当前持股状态为s的累计最大利润（s: 0 不持股  1 持股）
    public static int maxProfit_1(int k, int[] prices) {
        int len = prices.length;
        if(prices==null || len<2 || k==0) {
            return 0;
        }

        int[][] dp = new int[k+1][2];
        for(int j=0; j<=k; j++) {
            dp[j][1] = -prices[0];
            dp[j][0] = 0;
        }

        for(int price: prices) {
            for(int j=0; j<=k; j++) {
                if(j==0) {
                    dp[j][1] = Math.max(dp[j][1], dp[j][0]-price);
                    dp[j][0] = 0;
                } else {
                    dp[j][1] = Math.max(dp[j][1], dp[j][0]-price);
                    dp[j][0] = Math.max(dp[j][0], dp[j-1][1]+price);
                }
            }
        }
        return dp[k][0];
    }
}
