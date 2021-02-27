package Algorithm.Array;

/**
 * Created by Defias on 2020/07.
 * Description:
 *
 * 给定整数数组中是否存在若干数的和是给定的目标值
 */


public class TargetSum {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,7,23,44,100};
        System.out.println(solve2(nums, 11));
    }

    public static boolean solve(int[] nums, int target) {
        int i = 0;
        return recur(nums, i, target);
    }

    //数组从第i项到最后是否存在若干项的和为target
    public static boolean recur(int[] nums, int i, int target) {
        if(i>=nums.length) {
            return false;
        }

        if(nums[i]==target) {
            return true;
        }

        if(recur(nums, i+1, target) || recur(nums, i+1, target-nums[i])) {
            return true;
        }
        return false;
    }


    public static boolean solve2(int[] nums, int target) {
        int i = 0;
        int sum = 0;
        return dfs(nums, i, sum, target);
    }

    //数组前i项中是否存在若干项的和为target
    public static boolean dfs(int[] nums, int i, int sum, int target) {
        if(i==nums.length) {
            return sum == target;
        }

        if(dfs(nums, i+1, sum, target) || dfs(nums, i+1, sum, target-nums[i])) {
            return  true;
        }

        return false;
    }
}
