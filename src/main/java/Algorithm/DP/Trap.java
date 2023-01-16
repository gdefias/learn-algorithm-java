package Algorithm.DP;

/**
 * @author: Defias
 * @date: 2021/3/15
 * @description: 接雨水 / 直方图的水量
 *
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * https://leetcode-cn.com/problems/volume-of-histogram-lcci/
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */

public class Trap {


    //DP 动态规划
    //时间复杂度： O(n)   空间复杂度： O(n)
    public int trap(int[] height) {

        int len = height.length;
        int[] left = new int[len]; //left[i]: i位置左侧比i位置高度高的最大高度（如果没有比i位置更高的值为0）
        int[] right = new int[len]; //right[i]: i位置右侧比i位置高度高的最大高度（如果没有比i位置更高的值为0）

        int max = height[0];
        left[0] = 0;
        for(int i=1; i<len; i++) {
            if(height[i] < max) {
                left[i] = max;
            } else {
                left[i] = 0;
            }

            max = Math.max(max, height[i]);
        }

        max = height[len-1];
        right[len-1] = 0;
        for(int i=len-2; i>=0; i--) {
            if(height[i] < max) {
                right[i] = max;
            } else {
                right[i] = 0;
            }

            max = Math.max(max, height[i]);
        }

        int ans = 0;
        for(int i=0; i<len; i++) {
            if(left[i] > height[i] && right[i] > height[i]) {
                ans += (Math.min(left[i], right[i]) - height[i]);
            }
        }

        return ans;
    }

    //双指针
    //时间复杂度： O(n)   空间复杂度： O(1)
    public int trap2(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;

        int left = 1;
        int right = height.length - 2; // 加右指针进去

        for(int i=1; i<height.length-1; i++) {
            //从左到右更
            if (height[left-1] < height[right+1]) {
                max_left = Math.max(max_left, height[left-1]);
                int min = max_left;
                if (min > height[left]) {
                    sum += min-height[left];
                }
                left++;
            } else {   //从右到左更
                max_right = Math.max(max_right, height[right+1]);
                int min = max_right;
                if (min > height[right]) {
                    sum += min-height[right];
                }
                right--;
            }
        }
        return sum;
    }
}
