package Questions.DP;
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


    //DP
    //dp[i][j]: i行j列的网格，左上角到右下角的路径最小和
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        dp[0][0] = grid[0][0];
        for(int i=1; i<rows; i++) {  //仅1列的情况
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for(int j=1; j<cols; j++) {  //仅1行的情况
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        for(int i=1; i<rows; i++) {
            for(int j=1; j<cols; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[rows-1][cols-1];
    }

    //DP  空间优化
    //dp[j]
    public int minPathSum1(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];

        dp[0] = grid[0][0];
        for(int j=1; j<cols; j++) {  //第0行 各列的值
            dp[j] = dp[j-1] + grid[0][j];
        }

        for(int i=1; i<rows; i++) {  //第1行到最后一行
            dp[0] = dp[0] + grid[i][0];  //任意行第0列的值
            for(int j=1; j<cols; j++) {  //第1列到最后一列
                dp[j] = Math.min(dp[j-1], dp[j]) + grid[i][j];
            }
        }

        return dp[cols-1];
    }
}
