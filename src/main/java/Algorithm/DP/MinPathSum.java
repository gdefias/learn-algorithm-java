package Algorithm.DP;
/**
 * Created by Defias on 2020/07.
 * Description:  最小路径和

 https://leetcode-cn.com/problems/minimum-path-sum/

 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 说明：每次只能向下或者向右移动一步。

 输入:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 输出: 7
 解释: 因为路径 1→3→1→1→1 的总和最小。
 */

public class MinPathSum {


    //DP 动态规划
    //dp[i][j]: i行j列的网格，左上角到右下角的路径最小和
    //时间复杂度 O(MN) 空间复杂度 O(MN)
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        dp[0][0] = grid[0][0];  //起始位置

        //第1行
        for(int j=1; j<cols; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        //第1列
        for(int i=1; i<rows; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for(int i=1; i<rows; i++) {
            for(int j=1; j<cols; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[rows-1][cols-1];
    }

    //DP  动态规划 空间优化 一维滚动数组
    //时间复杂度 O(MN) 空间复杂度 O(N)
    public int minPathSum_1(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];

        dp[0] = grid[0][0];

        //第1行
        for(int j=1; j<cols; j++) {
            dp[j] = dp[j-1] + grid[0][j];
        }

        for(int i=1; i<rows; i++) {  //第2行到最后一行
            dp[0] = dp[0] + grid[i][0];  //任意行第0列的值
            for(int j=1; j<cols; j++) {  //第1列到最后一列
                dp[j] = Math.min(dp[j-1], dp[j]) + grid[i][j];
            }
        }

        return dp[cols-1];
    }


    //DP  动态规划 空间优化 直接在grid上原地修改
    //时间复杂度 O(MN) 空间复杂度 O(1)
    public int minPathSum_2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;


        //第1行
        for(int j=1; j<cols; j++) {
            grid[0][j] += grid[0][j-1];
        }

        //第1列
        for(int i=1; i<rows; i++) {
            grid[i][0] += grid[i-1][0];
        }

        for(int i=1; i<rows; i++) {
            for(int j=1; j<cols; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }

        return grid[rows-1][cols-1];
    }
}
