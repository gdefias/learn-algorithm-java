package Algorithm.DP;

/**
 * Created by Defias on 2020/07.
 * Description: 礼物的最大价值

 https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/

 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、
 直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

 输入:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 输出: 12
 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物

 0 < grid.length <= 200
 0 < grid[0].length <= 200

 贪心无法保证全局最优解，如：
 [
 [1,3,1],
 [1,5,4],
 [44,1,1]
 ]


 */

public class MaxValue {


    //DP 动态规划
    //思路：设f(i,j)为从棋盘左上角走至单元格(i,j)的礼物最大累计价值，易得到以下递推关系：f(i,j)等于f(i,j−1)和f(i−1,j)中的较大值
    //加上当前单元格礼物价值grid(i,j)  即: f(i,j) = max[f(i,j-1), f(i-1,j)] + grid(i,j)  需要考虑边界
    //时间复杂度 O(MN) 空间复杂度 O(MN)
    public int maxValue(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        dp[0][0] = grid[0][0];  //起始位置

        //第一行 只可从左边到达
        for(int j=1; j<cols; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        //第一列 只可从上边到达
        for(int i=1; i<rows; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        //可从左边或上边到达
        for(int i=1; i<rows; i++) {
            for(int j=1; j<cols; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]) + grid[i][j];
            }
        }

        return dp[rows-1][cols-1];
    }


    //DP 动态规划 空间优化 一维滚动数组
    //时间复杂度 O(MN) 空间复杂度 O(N)
    public int maxValue_1(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];

        dp[0] = grid[0][0];

        for(int j=1; j<cols; j++) {
            dp[j] = dp[j-1] + grid[0][j];
        }

        for(int i=1; i<rows; i++) {
            dp[0] = dp[0] + grid[i][0];
            for(int j=1; j<cols; j++) {
                dp[j] = Math.max(dp[j-1], dp[j]) + grid[i][j];
            }
        }
        return dp[cols-1];
    }

    //DP 动态规划 空间优化 直接在grid上原地修改
    //时间复杂度 O(MN) 空间复杂度 O(1)
    public int maxValue_2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        //第一行
        for(int j=1; j<cols; j++)  {
            grid[0][j] += grid[0][j-1];
        }

        //第一列
        for(int i=1; i<rows; i++) {
            grid[i][0] += grid[i-1][0];
        }

        for(int i=1; i<rows; i++) {
            for(int j=1; j<cols; j++) {
                grid[i][j] += Math.max(grid[i][j-1], grid[i-1][j]);
            }
        }

        return grid[rows-1][cols-1];
    }
}
