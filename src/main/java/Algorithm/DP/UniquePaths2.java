package Algorithm.DP;
/**
 * Created by Defias on 2020/07.
 * Description: 不同路径II

 https://leetcode-cn.com/problems/unique-paths-ii/

 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

 输入: 网格中的障碍物和空位置分别用 1 和 0 来表示。
 [
  [0,0,0],
  [0,1,0],
  [0,0,0]
 ]
 输出: 2
 解释:
 3x3 网格的正中间有一个障碍物。
 从左上角到右下角一共有 2 条不同的路径：
 1. 向右 -> 向右 -> 向下 -> 向下
 2. 向下 -> 向下 -> 向右 -> 向右
 */

public class UniquePaths2 {

    public static void main(String[] args) {
        UniquePaths2 O = new UniquePaths2();
        int[][] obstacleGrid = new int[][] {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        System.out.println(O.uniquePathsWithObstacles(obstacleGrid));
    }


    //DP 动态规划
    //dp[i][j]: 从第i行第j列网格到Finish的不同路径数
    //时间复杂度: O(MN) 空间复杂度: O(MN)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        if(obstacleGrid[m-1][n-1] == 1 || obstacleGrid[0][0] == 1 ) {
            return 0;
        }

        //最后一行
        dp[m-1][n-1] = 1;
        for(int j=n-2; j>=0; j--) {
            if(obstacleGrid[m-1][j] == 1) {
                dp[m-1][j] = 0;
            } else {
                dp[m-1][j] = dp[m-1][j+1];
            }
        }

        //最后一列
        for(int i=m-2; i>=0; i--) {
            if(obstacleGrid[i][n-1] == 1) {
                dp[i][n-1] = 0;
            } else {
                dp[i][n-1] = dp[i+1][n-1];
            }
        }

        for(int i=m-2; i>=0; i--) {
            for(int j=n-2; j>=0; j--) {
                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }

        return dp[0][0];
    }

    //DP 动态规划 空间优化  一维滚动数组
    //时间复杂度: O(MN) 空间复杂度: O(N)
    public int uniquePathsWithObstacles_(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];

        if(obstacleGrid[m-1][n-1] == 1 || obstacleGrid[0][0] == 1 ) {
            return 0;
        }

        //最后一行各列
        dp[n-1] = 1;
        for(int j=n-2; j>=0; j--) {
            if(obstacleGrid[m-1][j] == 1) {
                dp[j] = 0;
            } else {
                dp[j] = dp[j+1];
            }
        }

        for(int i=m-2; i>=0; i--) {
            if(obstacleGrid[i][n-1] == 1) {
                dp[n-1] = 0;
            } else {
                dp[n-1] = dp[n-1];
            }

            for(int j=n-2; j>=0; j--) {
                if(obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    dp[j] = dp[j] + dp[j+1];
                }
            }
        }

        return dp[0];
    }
}
