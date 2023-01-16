package Algorithm.DP.SubSequence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author: Felix
 * @date: 2023/1/12
 * @description: 不相交的线

   https://leetcode.cn/problems/uncrossed-lines/
    在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
    现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：

    nums1[i] == nums2[j]
    且绘制的直线不与任何其他连线（非水平线）相交。
    请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。

    以这种方法绘制线条，并返回可以绘制的最大连线数。

    输入：nums1 = [1,4,2], nums2 = [1,2,4]
    输出：2

    输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
    输出：3
 */
public class MaxUncrossedLines {

    public static void main(String[] args) {
        MaxUncrossedLines O = new MaxUncrossedLines();
        int[] nums1 = new int[] {2,1};
        int[] nums2 = new int[] {1,2,1,3,3,2};
        System.out.println(O.maxUncrossedLines(nums1, nums2));
    }


    //DP 动态规划 就是最长公共子序列LCS问题
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int[][] dp = new int[len1+1][len2+1];

        for(int i=1; i<=len1; i++) {
            for(int j=1; j<=len2; j++) {
                if(nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[len1][len2];
    }
}
