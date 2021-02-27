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

        System.out.println(O.uniquePathsWithObstacles1(obstacleGrid));
    }


    //DP
    //dp[i][j]: i行j列的网格从Start到Finish的不同路径数 (i,j从0开始)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length==0 || obstacleGrid[0].length==0) {
            return 0;
        }

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        int[][] dp = new int[rows][cols];

        //基本情况
        int n = 1;  //初值，也标识了是否遇到障碍了
        for(int j=0; j<cols; j++) {  //第1行各列的值
            if(n==1 && obstacleGrid[0][j]!=1) {
                dp[0][j] = n;
            } else {
                n = 0;   //第1行一旦遇到障碍了，后面的值就都是0
                dp[0][j] = n;
            }
        }

        n = 1;
        for(int i=0; i<rows; i++) {  //第1列各行的值
            if(n==1 && obstacleGrid[i][0]!=1) {
                dp[i][0] = n;
            } else {
                n = 0;      //第1列一旦遇到障碍了，后面的值就都是0
                dp[i][0] = n;
            }
        }

        for(int i=1; i<rows; i++) {  //从第2行第2列开始计算
            for(int j=1; j<cols; j++) {
                if(obstacleGrid[i][j] == 1) {  //计算到有障碍物的格子就给0值
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[rows-1][cols-1];
    }

    //DP 空间优化
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length==0 || obstacleGrid[0].length==0) {
            return 0;
        }

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        int[] dp = new int[cols];  //某1行各列的值

        //基本情况
        int n = 1;
        for(int j=0; j<cols; j++) {     //第1行各列的值
            if(n==1 && obstacleGrid[0][j]!=1) {
                dp[0] = n;
            } else {
                n = 0;
                dp[j] = n;
            }
        }

        for(int i=1; i<rows; i++) {

            //第i行第1列(j=0)的值
            if(obstacleGrid[i][0] == 1) {
                dp[0] = 0;
            } else {
                dp[0] = 1;
            }

            //第i行j列的值
            for(int j=1; j<cols; j++) {
                if(obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    dp[j] = dp[j] + dp[j-1];
                }
            }
        }
        return dp[cols-1];
    }
}
