package Questions.DP;

/**
 * Created by Defias on 2020/07.
 * Description:  买卖股票的最佳时机 III
 *
 *
 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。


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
        int[] prices = new int[] {3,3,5,0,0,3,1,4};
        System.out.println(maxProfit1(prices));

    }

    /**
     * DP - 三维数组
     * dp[i][j][0] 代表在第i天，已经买入了j次股票(取值范围：0、1、2)，此时手上并没有持有股票的最大利润
     * dp[i][j][1] 代表在第i天，已经买入了j次股票(取值范围：0、1、2)，此时手上持有股票的最大利润
     *
     * */
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if(prices==null || len<2) {
            return 0;
        }

        int [][][] dp = new int [len][3][2];

        dp[0][0][0] = 0;//第一天，买入股票次数为0且未持有股票，那么利润肯定为0
        dp[0][0][1] = Integer.MIN_VALUE;//第一天，买入股票次数为0且持有股票，这肯定是不可能发生的，给利润赋一个不可能得到的值

        for(int i=1; i<=2 ;i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0]; //第一天，买入股票次数为i且持有股票，利润为 -price[0]，因为花钱买了，还没卖
        }
        for(int i=1 ; i<len ;i++){
            for(int j = 1 ;j <= 2 ;j++){
                //代表在第i天，已经买入了j次股票，此时手上并没有持有股票的最大利润，有可能是从前一天什么都不做转移来的，也有可能是前一天持有股票，然后卖了，转移来的
                dp[i][j][0] = Math.max(dp[i - 1][j][0],dp[i - 1][j][1] + prices[i]);

                //代表在第i天，已经买入了j次股票，此时手上持有股票的最大利润，有可能是从前一天什么都不做转移来的，也有可能是前一天没有持有股票，然后买了今天的股票转移来的了
                dp[i][j][1] = Math.max(dp[i - 1][j][1],dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[len-1][2][0];
    }

    /**
     * DP - 二维数组
     *
     * */
    public static int maxProfit1(int[] prices) {
        int len = prices.length;
        if(prices==null || len<2) {
            return 0;
        }

        //dp[i][0] 未买卖 累计最大收益
        //dp[i][1] 第一次买入 累计最大收益
        //dp[i][2] 第一次卖出 累计最大收益
        //dp[i][3] 第二次买入 累计最大收益
        //dp[i][4] 第二次卖出 累计最大收益
        int[][] dp=new int[len][5];
        dp[0][0]=0;
        dp[0][1]=-prices[0];

        for (int i = 0; i <len ; i++) {
            dp[i][3]=Integer.MIN_VALUE;
        }
        for (int i = 1; i <len ; i++) {
            dp[i][0]=0;//不操作永远为0
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
            dp[i][2]=Math.max(dp[i-1][2],dp[i-1][1]+prices[i]);
            dp[i][3]=Math.max(dp[i-1][3],dp[i-1][2]-prices[i]);
            dp[i][4]=Math.max(dp[i-1][4],dp[i-1][3]+prices[i]);
        }
        //比较当前手里 股票卖出状态即可
        return Math.max(0,Math.max(dp[len-1][2],dp[len-1][4]));
    }
}
