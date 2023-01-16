package Algorithm.DP;

/**
 * @author: Felix
 * @date: 2023/1/13
 * @description: 打家劫舍

  https://leetcode.cn/problems/house-robber/description/?orderBy=most_votes

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果
两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

输入：[1,2,3,1]
输出：4

输入：[2,7,9,3,1]
输出：12

提示：
1 <= nums.length <= 100
0 <= nums[i] <= 400

 */
public class Rob {

    public static void main(String[] args) {
        Rob O = new Rob();
        int[] nums = new int[] {1,2,3,1};
        System.out.println(O.rob(nums));
    }

    //DP
    //dp[i]: 数组nums中以i下标结束，偷窃到的最高金额
    //时间复杂度：O(n)   空间复杂度：O(n)
    public int rob(int[] nums) {
        int len = nums.length;
        if(len < 2) {
            return nums[0];
        }

        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<len; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }

        return dp[len-1];
    }


    //DP 空间优化
    //时间复杂度：O(n)   空间复杂度：O(1)
    public int rob_(int[] nums) {
        int len = nums.length;
        if(len < 2) {
            return nums[0];
        }

        int prevv = nums[0];
        int prev = Math.max(nums[0], nums[1]);

        for(int i=2; i<len; i++) {
            int tmp = prev;
            prev = Math.max(prev, prevv + nums[i]);
            prevv = tmp;
        }

        return prev;
    }
}
