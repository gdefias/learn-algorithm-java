package Algorithm.DP;

/**
 * Created by Defias on 2020/07.
 * Description:  组合总和 Ⅳ

     https://leetcode-cn.com/problems/combination-sum-iv/

     给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数

     nums = [1, 2, 3]
     target = 4

     所有可能的组合为：
     (1, 1, 1, 1)
     (1, 1, 2)
     (1, 2, 1)
     (1, 3)
     (2, 1, 1)
     (2, 2)
     (3, 1)

     请注意，顺序不同的序列被视作不同的组合
     因此输出为 7  ----只需输出符合要求的组合的个数，无需输出具体的组合

     进阶：
     如果给定的数组中含有负数会怎么样？
     问题会产生什么变化？
     需要在题目中添加什么限制来允许负数的出现？
 */

public class CombinationSum4 {
    public static int res = 0;

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 4, 5};
        int target = 10;

        System.out.println(combinationSum(nums,target));
    }


    /**
     * 方法1：DP
     *
     * dp[i] 表示选取的元素之和等于 i 的方案数，目标是求 dp[target]
     *
     * 状态转移方程：
     * dp[i]= dp[i-nums[0]] + dp[i-nums[1]] + dp[i-nums[2]] + ... （当 [] 里面的数 >= 0）
     * 因为元素之和等于i-nums[0]的每个方案，向方案中添加一个nums[0]就可以得到素之和等于i的方案，依次类推...
     *
     * 例子： nums=[1, 3, 4], target=7;  dp[7] = dp[6] + dp[4] + dp[3]
     *
     * 时间复杂度：O(target × n)，其中targe是目标值，n是数组nums的长度。需要计算长度为 target+1 的数组 dp 的每个元素的值，对于每
     * 个元素，需要遍历数组 nums 之后计算元素值。
     * 空间复杂度：O(target)。需要创建长度为 target+1 的数组 dp
     */
    public static int combinationSum(int[] nums, int target) {
        if(nums==null || nums.length==0 || target<=0) {
            return 0;
        }

        int[] dp = new int[target+1];
        dp[0] = 1; //元素之和为0的方案就是什么也不取，方案数为1

        //递推
        for(int i=1; i<=target; i++) {
            for(int num: nums) {
                if(num <= i) {
                    dp[i] += dp[i-num];
                }
            }
        }

        return dp[target];
    }


    /**
     * 方法2：回溯
     * 时间复杂度：O(n∗n!)   leecode会超时
     * */
    public static int combinationSum2(int[] nums, int target) {
        if(nums==null || nums.length==0 || target<=0) {
            return 0;
        }
        int sum = 0;
        backtrack(nums, target, sum);
        return res;

    }

    public static void backtrack(int[] nums, int target, int sum) {
        if(sum == target) {
            res++;
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(sum+nums[i] > target) {  //累加和已经大于目标了
                continue;
            }

            sum += nums[i];
            backtrack(nums, target, sum);
            sum -= nums[i];
        }
    }
}
