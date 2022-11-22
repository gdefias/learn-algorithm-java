package Algorithm.Array;

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


    //DP 时间复杂度： O(n)   空间复杂度： O(n)
    public int trap(int[] height) {

        int[] leftmax = new int[height.length];
        int[] rightmax = new int[height.length];

        leftmax[0] = height[0];
        for(int i=1; i<height.length-1; i++) {
            leftmax[i] = Math.max(leftmax[i-1], height[i-1]);
        }

        rightmax[height.length-1] = height[height.length-1];
        for(int i=height.length-2; i>0; i--) {
            rightmax[i] = Math.max(rightmax[i+1], height[i+1]);
        }

        int ans = 0;
        for(int i=1; i<height.length-1; i++) {
            int min = Math.min(leftmax[i], rightmax[i]);
            if(min>height[i]) {
                ans += (min-height[i]);
            }
        }

        return ans;
    }

    //双指针  时间复杂度： O(n)   空间复杂度： O(1)
    public int trap2(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = height.length - 2; // 加右指针进去
        for (int i = 1; i < height.length - 1; i++) {
            //从左到右更
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;

                //从右到左更
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }
}
