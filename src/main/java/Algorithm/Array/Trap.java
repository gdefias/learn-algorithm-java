package Algorithm.Array;

/**
 * @author: Felix
 * @date: 2023/1/3
 * @description: 接雨水

 https://leetcode.cn/problems/trapping-rain-water/description/

 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

    输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
    输出：6

    输入：height = [4,2,0,3,2,5]
    输出：9
 */
public class Trap {
    public static void main(String[] args) {
        Trap O = new Trap();
        int[] height = new int[] {4,2,0,3,2,5};
        System.out.println(O.trap2(height));

    }

    //方法1：动态规划
    //时间复杂度：O(n)  空间复杂度：O(n)
    public int trap(int[] height) {
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];

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

    //方法2：双指针   对方法1进行空间优化
    //时间复杂度：O(n)  空间复杂度：O(1)
    public int trap2(int[] height) {
        int len = height.length;
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = len-1;
        int ans = 0;

        while(left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if(height[left] < height[right]) {
                ans += (leftMax-height[left]);
                left++;
            } else {
                ans += (rightMax-height[right]);
                right--;
            }
        }

        return ans;
    }

    //方法3：单调栈
    //时间复杂度：O(n)  空间复杂度：O(n)
    public int trap3(int[] height) {
        int ans = 0;

        return ans;
    }
}
