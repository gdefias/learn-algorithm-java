package Algorithm.DP;
/**
 * Created by Defias on 2020/07.
 * Description: 不同路径

 https://leetcode-cn.com/problems/unique-paths/

 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 问总共有多少条不同的路径？

 输入: m = 3, n = 2
 输出: 3
 解释:
 从左上角开始，总共有 3 条路径可以到达右下角。
 1. 向右 -> 向右 -> 向下
 2. 向右 -> 向下 -> 向右
 3. 向下 -> 向右 -> 向右

 1 <= m, n <= 100
 题目数据保证答案小于等于 2 * 10 ^ 9
 */


public class UniquePaths {

    public static void main(String[] args) {
        UniquePaths O = new UniquePaths();
        System.out.println(O.uniquePaths(3, 2));
    }

    //DP 动态规划
    //dp[i][j]: 从第i行第j列网格到Finish的不同路径数
    //时间复杂度: O(MN) 空间复杂度: O(MN)
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        //最后一行
        for(int j=n-1; j>=0; j--) {
            dp[m-1][j] = 1;
        }

        //最后一列
        for(int i=m-2; i>=0; i--) {
            dp[i][n-1] = 1;
        }

        for(int i=m-2; i>=0; i--) {
            for(int j=n-2; j>=0; j--) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }

        return dp[0][0];
    }

    //DP 动态规划 空间优化 一维滚动数组
    //时间复杂度: O(MN) 空间复杂度: O(N)
    public int uniquePaths_(int m, int n) {
        int[] dp = new int[n];

        //最后一行
        for(int j=n-1; j>=0; j--) {
            dp[j] = 1;
        }

        for(int i=m-2; i>=0; i--) {
            dp[n-1] = 1;
            for(int j=n-2; j>=0; j--) {
                dp[j] = dp[j] + dp[j+1];
            }
        }

        return dp[0];

    }
}
