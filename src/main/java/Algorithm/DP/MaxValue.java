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
 */

public class MaxValue {

    public int maxValue(int[][] grid) {
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
                int max = Math.max(dp[j-1], dp[j]);
                dp[j] = max + grid[i][j];
            }
        }
        return dp[cols-1];
    }
}
