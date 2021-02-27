package Algorithm.DP;
/**
 * Created by Defias on 2020/07.

 买卖股票的最佳时机
 最多持一股，只能买卖一次的最大收益

 https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/

 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？


 输入: [7,1,5,3,6,4]
 输出: 5
 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

 输入: [7,6,4,3,1]
 输出: 0
 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

 0 <= 数组长度 <= 10^5
 */

public class BestTimetoBuyandSellStock {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));

    }

    //DP
    public static int maxProfit(int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;  //第一天什么都不做 利润为0
        dp[0][1] = -prices[0];  //第一天买入 利润为负数  （第一天不可能卖出）

        for(int i=1; i<=prices.length-1; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }


        //最后一天不持有股票的累计最大利润  因为一定有 dp[len-1][0] > dp[len-1][1]
        return dp[prices.length-1][0];
    }



    //一次遍历 贪心
    public static int maxProfit1(int[] prices) {
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

    //更简的一次遍历  贪心
    public static int  maxProfit1_2(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int price: prices) {
            min = Math.min(min, price);
            maxProfit = Math.max(maxProfit, price - min);
        }
        return maxProfit;
    }



    //分治
    public static int maxProfit2(int[] prices) {
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


    //价格数组prices中left到right的最大价格和最小结构及索引
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
