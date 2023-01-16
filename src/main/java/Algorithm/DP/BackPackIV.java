package Algorithm.DP;

/**
 * @author: Felix
 * @date: 2023/1/10
 * @description: 背包问题 IV
    给出 n 个物品, 以及一个数组, nums[i]代表第i个物品的大小, 保证大小均为正数并且没有重复, 正整数 target 表示背包的大小, 找到能
    填满背包的方案数。每一个物品可以使用无数次

    输入: nums = [2,3,6,7] 和 target = 7
    输出: 2
    解释:
    方案有:
    [7]
    [2, 2, 3]

    输入: nums = [2,3,4,5] 和 target = 7
    输出: 3
    解释:
    方案有:
    [2, 5]
    [3, 4]
    [2, 2, 3]
 */
public class BackPackIV {

    //方法1：回溯  类似组合总和问题CombinationSum  只计算次数

    //方法2：DP
    public int backPackIV(int[] nums, int target) {

        int[][] dp = new int[nums.length][target+1]; //前i件物品放入容量j的背包，填满背包的方案数

        //边界
        for(int i=0; i<nums.length; i++) {
            dp[i][0] = 1;
        }

        for(int j=1; j<=target; j++) {
            if(j%nums[0] == 0) {
                dp[0][j] = 1;
            }
        }

        //递推
        for(int i=1; i<nums.length; i++) {
            for(int j=1; j<=target; j++) {
                if(j >= nums[i]) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-nums[i]];  //可重复取dp[i][j-nums[i]]
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[nums.length-1][target];
    }

    //方法2空间优化：DP 一维滚动数组
    public int backPackIV_(int[] nums, int target) {

        int[] dp = new int[target+1]; //容量j的背包，填满背包的方案数

        //边界
        for(int j=0; j<=target; j++) {
            if(j%nums[0] == 0) {
                dp[j] = 1;
            }
        }

        //递推
        for(int i=1; i<nums.length; i++) {
            for(int j=0; j<=target; j++) {
                if(j >= nums[i]) {
                    dp[j] = dp[j] + dp[j-nums[i]];
                }
            }
        }

        return dp[target];
    }
}
