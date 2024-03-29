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
        System.out.println(solve(nums, 10));
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
}
