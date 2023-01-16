package Algorithm.DP;

/**
 * Created by Defias on 2017/10/10.
 三角形最小路径和

 https://leetcode-cn.com/problems/triangle/

 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上
 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点

 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 自顶向下的最小路径和为11（即，2+3+5+1= 11）


 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分
 */
import java.util.*;

public class TriangleMinimumTotal {

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6, 7),
                Arrays.asList(4, 1, 8, 3)
        );

        System.out.println(minimumTotal2(triangle));
    }

    //方法1：DP 动态规划
    //dp[i][j]: 从三角形顶部(0, 0)走到位置(i,j)的最小路径和
    //时间复杂度: O(MN) 空间复杂度: O(MN)
    public static int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[][] dp = new int[rows][rows];

        dp[0][0] = triangle.get(0).get(0);  //顶部

        for(int i=1; i<rows; i++) {
            int cols = triangle.get(i).size();
            for(int j=0; j<cols; j++) {
                if(j == 0) { //第一列
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                } else if(j == cols-1) {  //最后一列
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                } else { //中间列
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
                }
            }
        }

        //最后一行各列值中最小即是最终答案
        int min = dp[rows-1][0];
        for(int j=1; j<rows; j++) {
            min = Math.min(min, dp[rows-1][j]);
        }
        return min;
    }

    //DP 动态规划 方法1空间优化 一维滚动数组
    //时间复杂度: O(MN) 空间复杂度: O(M)
    public static int minimumTotal_1(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[] dp = new int[rows];

        dp[0] = triangle.get(0).get(0);  //顶部
        int leftup = dp[0];

        for(int i=1; i<rows; i++) {
            int cols = triangle.get(i).size();
            for(int j=0; j<cols; j++) {
                int tmp = dp[j];
                if(j == 0) { //第一列
                    dp[j] = dp[j] + triangle.get(i).get(j);
                } else if(j == cols-1) {  //最后一列
                    dp[j] = leftup + triangle.get(i).get(j);
                } else { //中间列
                    dp[j] = Math.min(leftup, dp[j]) + triangle.get(i).get(j);
                }
                leftup = tmp;
            }
        }

        //最后一行各列值中最小即是最终答案
        int min = dp[0];
        for(int j=1; j<rows; j++) {
            min = Math.min(min, dp[j]);
        }
        return min;
    }

    //DP 动态规划 方法1空间优化  在原三角形矩阵中进行修改
    //时间复杂度: O(MN) 空间复杂度: O(1)
    public static int minimumTotal_2(List<List<Integer>> triangle) {
        int rows = triangle.size();

        for(int i=1; i<rows; i++) {
            int cols = triangle.get(i).size();
            for(int j=0; j<cols; j++) {
                if(j == 0) { //第一列
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j));
                } else if(j == cols-1) {  //最后一列
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j-1));
                } else { //中间列
                    triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i-1).get(j),  triangle.get(i-1).get(j-1)));
                }
            }
        }

        //最后一行各列值中最小即是最终答案
        int min = triangle.get(rows-1).get(0);
        for(int j=1; j<rows; j++) {
            min = Math.min(min, triangle.get(rows-1).get(j));
        }
        return min;
    }


    //方法2：DP 动态规划 （三角矩阵从下到上计算）
    //dp[i][j]: 从位置(i,j)到最后一行的最小路径和
    //时间复杂度: O(MN) 空间复杂度: O(MN)
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[][] dp = new int[rows][rows];

        for(int i=rows-1; i>=0; i--) {
            int cols = triangle.get(i).size();
            for(int j=0; j<cols; j++) {
                if(i == rows-1) {
                    dp[i][j] = triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
                }
            }
        }

        //三角矩阵的顶点即是最终答案
        return dp[0][0];
    }


    //DP 动态规划 方法2空间优化 一维滚动数组
    //时间复杂度: O(MN) 空间复杂度: O(M)
    public static int minimumTotal2_1(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[] dp = new int[rows];

        for(int i=rows-1; i>=0; i--){
            int cols = triangle.get(i).size();
            for(int j=0; j<cols; j++) {
                if(i == rows-1) {
                    dp[j] = triangle.get(i).get(j);
                } else {
                    dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
                }
            }
        }
        return dp[0];
    }


    //DP 动态规划 方法2空间优化 在原三角形矩阵中进行修改
    //时间复杂度: O(MN) 空间复杂度: O(1)
    public static int minimumTotal2_2(List<List<Integer>> triangle) {
        for(int i=triangle.size()-2; i>=0; i--) {
            for(int j=0; j<=i; j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1)));
            }
        }
        return triangle.get(0).get(0);
    }

}
