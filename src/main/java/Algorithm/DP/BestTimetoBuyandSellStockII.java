package Algorithm.DP;

/**
 * Created by Defias on 2017/10/15.

   买卖股票的最佳时机 II
   最多持一股，可以买卖任意次的最大收益

   https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/

     给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     返回 你能获得的 最大 利润 。


     输入: [7,1,5,3,6,4]
     输出: 7
     解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
         随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。

     输入: [1,2,3,4,5]
     输出: 4
     解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
         注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
         因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。


     输入: [7,6,4,3,1]
     输出: 0
     解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class BestTimetoBuyandSellStockII {

    public static void main(String[] args) {
        int[] prices = new int[] {1,10,20};
        System.out.println(maxProfit(prices));

    }



    /**
     * 方法1: DP
     * 每天都有三种动作：买入（buy）、卖出（sell）、无操作（rest）。
     *
     * dp[i][j]  两个维度分别是天数（0,1,...,n-1）和是否持有股票（1 表持有，0 表不持有） 即表示：第i天持有股票和不持有股票的累计最大利润
     *
     *
     * 状态转移方程
     * Case 1，今天我没有股票，有两种可能：
     * 昨天我手上就没有股票，今天不做任何操作（rest）；
     * 昨天我手上有一只股票，今天按照时价卖掉了（sell），收获了一笔钱
     *
     * Case 2，今天持有一只股票，有两种可能：
     * 昨天我手上就有这只股票，今天不做任何操作（rest）；
     * 昨天我没有股票，今天按照时价买入一只（sell），花掉了一笔钱
     *
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     *
     */
    public static int maxProfit(int[] prices) {
        if(prices==null||prices.length<2) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;  //第一天什么都不做 利润为0
        dp[0][1] = -prices[0];  //第一天买入 利润为负数  （第一天不可能卖出，买入需要花费当天股票价格的现金）

        for(int i=1; i<=prices.length-1; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
        }


        //最后一天不持有股票的累计最大利润  因为一定有 dp[len-1][0] > dp[len-1][1]
        return dp[prices.length-1][0];
    }

    //方法1空间优化  一维滚动数组
    public static int maxProfit_(int[] prices) {
        if(prices==null||prices.length<2) {
            return 0;
        }

        int[] dp = new int[2];

        dp[0] = 0;
        dp[1] = -prices[0];
        for(int i=1; i<=prices.length-1; i++) {
            dp[0] = Math.max(dp[0], dp[1]+prices[i]);
            dp[1] = Math.max(dp[1], dp[0]-prices[i]);
        }

        return dp[0];
    }

    //方法1状态分离 两个一维数组
    //持有和不持有股票状态分离为两个数组
    public int maxProfit_1(int[] prices) {
        if(prices==null||prices.length<2) {
            return 0;
        }

        // cash：不持有股票
        // hold：持有股票
        // 状态数组
        // 状态转移：cash → hold → cash → hold → cash → hold → cash
        int[] cash = new int[prices.length];
        int[] hold = new int[prices.length];

        cash[0] = 0;
        hold[0] = -prices[0];

        for (int i=1; i<prices.length; i++) {
            // 这两行调换顺序也是可以的
            cash[i] = Math.max(cash[i-1], hold[i-1] + prices[i]);
            hold[i] = Math.max(hold[i-1], cash[i-1] - prices[i]);
        }
        return cash[prices.length-1];
    }


    //方法1状态分离 + 状态压缩
    //由于当前行只参考上一行，每一行就2个值，因此可以考虑使用“滚动变量”
    public int maxProfit_2(int[] prices) {
        if(prices==null||prices.length<2) {
            return 0;
        }

        // cash：持有现金
        // hold：持有股票
        // 状态转移：cash → hold → cash → hold → cash → hold → cash

        int cash = 0;
        int hold = -prices[0];

        int preCash = cash;
        int preHold = hold;
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(preCash, preHold + prices[i]);
            hold = Math.max(preHold, preCash - prices[i]);

            preCash = cash;
            preHold = hold;
        }
        return cash;
    }


    /**
     * 方法2: 贪心(局部最优解作为全局最优解)
     *
     * 原理：股价有升有落，需要找出所有的升区间，计算每个升区间的价格差（峰值减去谷值）作为收益，最后把所有升区间带来的收益求和就可以了。
     * 对于升区间 [a, b, c, d]，有 a <= b <= c <= d ，那么最大收益为 d - a。而 d - a = (d - c) + (c - b) + (b - a)
     * 因此每当访问到 prices[i] 比前一天价格高，即 prices[i] - prices[i-1] > 0，那么就把 prices[i] - prices[i-1] 添加到收益中
     */
    public static int maxProfit2(int[] prices) {
        if(prices==null||prices.length<2) {
            return 0;
        }
        int maxval=0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] > prices[i-1]) {
                maxval = maxval + (prices[i]-prices[i-1]);
            }
        }
        return maxval;
    }

}
