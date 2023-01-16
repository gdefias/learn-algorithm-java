package Algorithm.DP;

/**
 * @author: Felix
 * @date: 2023/1/10
 * @description: 背包问题 V

    给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数, 正整数 target 表示背包的大小, 找到能填满背包的方
    案数。每一个物品只能使用一次

    给出候选物品集合 [1,2,3,3,7] 以及 target 7
    结果的集合为:
    [7]
    [1,3,3]
    返回 2
 */
public class BackPackV {

    //方法1：回溯  类似组合总和II问题CombinationSum2  只计算次数

    //方法2：DP
    public int backPackV(int[] nums, int target) {

        int[][] dp = new int[nums.length][target+1]; //前i件物品放入容量j的背包，填满背包的方案数

        //边界
        for(int i=0; i<nums.length; i++) {
            dp[i][0] = 1;
        }

        for(int j=1; j<=target; j++) {
            if(j == nums[0]) {
                dp[0][j] = 1;
            }
        }

        //递推
        for(int i=1; i<nums.length; i++) {
            for(int j=1; j<=target; j++) {
                if(j >= nums[i]) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[nums.length-1][target];
    }

    //方法2空间优化： 一维滚动数组
    public int backPackV_(int[] nums, int target) {
        int[] dp = new int[target+1]; //容量j的背包，填满背包的方案数

        //边界
        dp[0] = 1;
        for(int j=1; j<=target; j++) {
            if(j == nums[0]) {
                dp[j] = 1;
            }
        }

        //递推
        for(int i=1; i<nums.length; i++) {
            for(int j=target; j>=nums[i]; j--) {
                dp[j] = dp[j] + dp[j-nums[i]];
            }
        }

        return dp[target];
    }
}
