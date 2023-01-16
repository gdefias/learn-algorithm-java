package Algorithm.DP;
/**
 * Created by Defias on 2020/07.
     买卖股票的最佳时机
     最多持一股，只能买卖一次的最大收益

     https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/

     给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。


     输入: [7,1,5,3,6,4]
     输出: 5
     解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

     输入: [7,6,4,3,1]
     输出: 0
     解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

     提示：
     1 <= prices.length <= 105
     0 <= prices[i] <= 104
 */

public class BestTimetoBuyandSellStock {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));

    }

    //方法1：DP
    //时间复杂度：O(N) 空间复杂度：O(N)
    public static int maxProfit(int[] prices) {
        if(prices==null || prices.length<2) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];
        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数 (利润)
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数

        //状态转移方程：
        //dp[i][0]：今天不持股，有以下两种情况：
        //昨天不持股，今天什么都不做（现金数与昨天一样）；
        //昨天持股，今天卖出股票（现金数增加），

        //dp[i][1]：今天持股，有以下两种情况：
        //昨天持股，今天什么都不做（现金数与昨天一样）；
        //昨天不持股，今天买入股票（注意：只允许交易一次，因此手上的现金数就是当天的股价的相反数）

        dp[0][0] = 0;  //第一天不持股（什么都不做） 利润为0
        dp[0][1] = -prices[0];  //第一天持股 利润为负数  （第一天不可能卖出，买入需要花费当天股票价格的现金）

        for(int i=1; i<prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
            //第i天持股 前一天就持有股票（现金数与前一天一样）；前一天没有股票，第i天第一次买入（现金数为当天的股价的相反数）
        }


        //最后一天不持有股票的累计最大利润  因为一定有 dp[len-1][0] > dp[len-1][1]
        return dp[prices.length-1][0];
    }

    //方法1空间优化
    //时间复杂度：O(N) 空间复杂度：O(1)
    public static int maxProfit_(int[] prices) {
        if(prices==null || prices.length<2) {
            return 0;
        }

        int[] dp = new int[2];

        dp[0] = 0;  //第一天不持股（什么都不做） 利润为0
        dp[1] = -prices[0];  //第一天持股 利润为负数  （第一天不可能卖出，买入需要花费当天股票价格的现金）

        for(int i=1; i<prices.length; i++) {
            dp[0] = Math.max(dp[0], dp[1]+prices[i]);
            dp[1] = Math.max(dp[1], -prices[i]);
        }


        return dp[0];
    }


    //方法2： 贪心 一次遍历 [7,1,5,3,6,4]
    //时间复杂度：O(N) 空间复杂度：O(1)
    public static int maxProfit2(int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }

        int maxres = 0;
        int index = 0;
        int minprice = prices[index];
        for(int i=1; i<prices.length; i++) {
            if(prices[i]<=minprice) {
                index = i;
                minprice = prices[index];
                continue;
            }
            if((prices[i]-minprice)>maxres) {
                maxres = prices[i]-minprice;
            }
        }

        return maxres;
    }

    //方法2优化 更简的一次遍历  贪心  [7,1,5,3,6,4]
    public static int  maxProfit2_(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int price: prices) {
            min = Math.min(min, price);
            maxProfit = Math.max(maxProfit, price - min);
        }
        return maxProfit;
    }



    //方法3：分治
    public static int maxProfit3(int[] prices) {
        if(prices==null||prices.length==0) {
            return 0;
        }
        return maxProfitHelper(prices, 0, prices.length-1);
    }

    public static int maxProfitHelper(int[] prices, int left, int right) {
        if(left==right) {
            return 0;
        }

        int[] maxmin = getMaxMin(prices, left, right);
        //for(int m:maxmin) {
        //    System.out.println(m);
        //}

        //最小价格在最大价格的左边 或 所有价格都相等 直接得到最大利润
        if(maxmin[0]<=maxmin[2]) {
            return maxmin[3]-maxmin[1];
        }

        //分三段分别找最大利润
        int resleft = maxProfitHelper(prices, left, maxmin[2]);
        int resright = maxProfitHelper(prices, maxmin[0], right);
        int resmid = maxProfitHelper(prices, maxmin[2]+1, maxmin[0]-1);

        return Math.max(Math.max(resleft, resright), resmid);
    }


    //获得价格数组prices中left到right的最大价格和最小价格及索引
    public static int[] getMaxMin(int[] prices, int left, int right) {
        int maxval = prices[left];
        int maxindex = left;
        int minval = prices[left];
        int minindex = left;


        for(int i=left; i<=right; i++) {
            if(prices[i]<minval) {
                minval = prices[i];
                minindex = i;
            }

            if(prices[i]>maxval) {
                maxval = prices[i];
                maxindex = i;
            }
        }
        return new int[] {minindex, minval, maxindex, maxval};
    }
}
