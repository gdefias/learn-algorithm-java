package Algorithm.DP;

/**
 * Created by Defias on 2020/07.
 * Description:  买卖股票的最佳时机 III

    https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/
     给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
     注意:你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

     (隐含：在每一天，你可以决定是否购买和/或出售股票)

     输入: [3,3,5,0,0,3,1,4]
     输出: 6
     解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
         随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。


     输入: [1,2,3,4,5]
     输出: 4
     解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
         注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
         因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

     输入: [7,6,4,3,1]
     输出: 0
     解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */

public class BestTimetoBuyandSellStockIII {

    public static void main(String[] args) {
        int[] prices = new int[] {1,10,20,19,18,17,16};
        System.out.println(maxProfit_2(prices));

    }

    /**
     * 方法1： DP - 三维数组
     * dp[i][j][0] 代表在第i天，已经买入了j次股票(取值范围：0、1、2)，此时手上并没有持有股票的最大利润
     * dp[i][j][1] 代表在第i天，已经买入了j次股票(取值范围：0、1、2)，此时手上持有股票的最大利润
     * */
    public static int maxProfit(int[] prices) {
        if(prices==null || prices.length<2) {
            return 0;
        }

        //边界
        int [][][] dp = new int [prices.length][3][2];
        dp[0][0][0] = 0;//第一天，买入股票次数为0且未持有股票，那么利润肯定为0
        dp[0][0][1] = Integer.MIN_VALUE;//第一天，买入股票次数为0且持有股票，这肯定是不可能发生的，给利润赋一个不可能得到的值
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0]; //第一天，买入股票次数为1且持有股票，利润为 -price[0]，因为花钱买了，还没卖
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];

        //递推
        for(int i=1 ; i<prices.length ;i++){
            for(int j=1 ;j<=2 ;j++){
                //代表在第i天，已经买入了j次股票，此时手上并没有持有股票的最大利润
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1]+prices[i]);

                //代表在第i天，已经买入了j次股票，此时手上持有股票的最大利润
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0]-prices[i]);
            }
        }
        return dp[prices.length-1][2][0];
    }

    /**
     * 方法1空间优化 DP - 二维数组
     * */
    public static int maxProfit_1(int[] prices) {
        if(prices==null || prices.length<2) {
            return 0;
        }

        //一天一共就有五个状态
        //dp[i][0] 未买卖(没有操作) 累计最大利润
        //dp[i][1] 第一次买入 累计最大利润
        //dp[i][2] 第一次卖出 累计最大利润
        //dp[i][3] 第二次买入 累计最大利润
        //dp[i][4] 第二次卖出 累计最大利润

        //边界
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        for (int i=1; i<prices.length; i++) {
            dp[i][0] = 0;   //不操作永远为0
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1]+prices[i]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2]-prices[i]);
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3]+prices[i]);
        }

        //比较当前手里 股票卖出状态即可
//        return Math.max(0, Math.max(dp[prices.length-1][2], dp[prices.length-1][4]));
        return dp[prices.length-1][4];
    }


    /**
     * 方法1空间优化 DP - 一维滚动数组
     * */
    public static int maxProfit_2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        //边界
        int[] dp = new int[5];
        dp[0] = 0;
        dp[1] = -prices[0];
        dp[2] = 0;
        dp[3] = -prices[0];
        dp[4] = 0;

        for (int i=1; i<prices.length; i++) {
            dp[0] = 0;   //不操作永远为0
            dp[1] = Math.max(dp[1], dp[0]-prices[i]);
            dp[2] = Math.max(dp[2], dp[1]+prices[i]);
            dp[3] = Math.max(dp[3], dp[2]-prices[i]);
            dp[4] = Math.max(dp[4], dp[3]+prices[i]);
        }

        //比较当前手里 股票卖出状态即可
//        return Math.max(0, Math.max(dp[2], dp[4]));
        return dp[4];
    }
}
