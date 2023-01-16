package Algorithm.DP;

/**
 * @author: Felix
 * @date: 2023/1/13
 * @description: 打家劫舍 II

https://leetcode.cn/problems/house-robber-ii/description/?orderBy=most_votes

你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是
紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。

输入：nums = [2,3,2]
输出：3

输入：nums = [1,2,3,1]
输出：4

提示：
1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */
public class Rob2 {

    public static void main(String[] args) {
        Rob2 O = new Rob2();
        int[] nums = new int[] {2,3,2};
        System.out.println(O.rob(nums));
    }


    //DP
    public int rob(int[] nums) {
        int len = nums.length;
        if(len < 2) {
            return nums[0];
        }

        //第一家偷与不偷 获得最高金融更高的那一个
        int ans = Math.max(helper(nums, 1, len-1), helper(nums, 0, len-2));
        return ans;
    }


    //dp[i]: 数组nums[start, end]中以i下标结束，偷窃到的最高金额
    public int helper(int[] nums, int start, int end) {
        int len = end-start+1;
        if(len < 2) {
            return nums[start];
        }

        int[] dp = new int[len];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start+1]);

        for(int i=2; i<len; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[start+i]);
        }

        return dp[len-1];
    }
}
