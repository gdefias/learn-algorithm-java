package Questions.DP;
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
        System.out.println(O.uniquePaths1(3, 2));
    }

    //DP
    //dp[i][j]: i行j列的网格从Start到Finish的不同路径数 (i,j从0开始)
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        //基本情况
        for(int j=0; j<n; j++) {  //第1行各列的值
            dp[0][j] = 1;
        }

        for(int i=0; i<m; i++) {  //第1列各行的值
            dp[i][0] = 1;
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    //DP 空间优化
    //根据状态转换公式 dp[i][j] = dp[i-1][j] + dp[i][j-1] 可知：当要计算第 i 行的值时，除了会用到第 i - 1 行外，其他第 1 至 第 i-2 行的值
    //都是不需要用到的，也就是说，对于那部分用不到的值没必要保存他们
    public int uniquePaths1(int m, int n) {

        int[] dp = new int[n];  //某1行各列的值

        //基本情况
        for(int j=0; j<n; j++) {  //第1行各列的值
            dp[j] = 1;
        }

        for(int i=1; i<m; i++) {  //从第2行第2列开始计算
            for(int j=1; j<n; j++) {
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }
}
