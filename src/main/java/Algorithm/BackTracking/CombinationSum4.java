package Questions.BackTracking;

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
        //System.out.println(combinationSum2(nums,target));
        System.out.println(combinationSum3(nums,target));
    }

    //方法1：回溯
    public static int combinationSum(int[] nums, int target) {
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
            if(sum+nums[i]>target) {
                continue;
            }

            sum += nums[i];
            backtrack(nums, target, sum);
            sum -= nums[i];
        }
    }


    ////方法2：与方法1类似，只是没有专门保存sum，而是根据当前sum每次改变target值
    //public static int combinationSum2(int[] nums, int target) {
    //    if(nums==null || nums.length==0 || target<=0) {
    //        return 0;
    //    }
    //
    //    backtrack2(nums, target);
    //    return res;
    //
    //}
    //
    //public static void backtrack2(int[] nums, int target) {
    //    if(target == 0) {
    //        res++;
    //        return;
    //    }
    //
    //    for(int i=0; i<nums.length; i++) {
    //        if(target-nums[i]<0) {
    //            continue;
    //        }
    //
    //        backtrack2(nums, target-nums[i]);
    //    }
    //}

  // DP
  // 状态转移方程：dp[i]= dp[i - nums[0]] + dp[i - nums[1]] + dp[i - nums[2]] + ... （当 [] 里面的数 >= 0）
  // 例子： nums=[1, 3, 4], target=7;  dp[7] = dp[6] + dp[4] + dp[3]
  public static int combinationSum3(int[] nums, int target) {
        if(nums==null || nums.length==0 || target<=0) {
            return 0;
        }

        int[] dp = new int[target+1];
        dp[0] = 1;  //未赋值的数都是0

        for(int i=1; i<=target; i++) {
            for(int num: nums) {   //累加
                if(num <= i) {
                    dp[i] += dp[i-num];
                }
            }

        }

        return dp[target];
    }
}
