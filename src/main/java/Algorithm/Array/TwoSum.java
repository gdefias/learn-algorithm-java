package Algorithm.Array;

/**
 * Created by Defias on 2020/07.
 * Description: 和为s的两个数字

 https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/

 输入一个递增排序的数组和一个数字s，在数组查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，输出任意一对即可

     输入：nums = [2,7,11,15], target = 9
     输出：[2,7] 或者 [7,2]

     1 <= nums.length <= 10^5
     1 <= nums[i] <= 10^6
 */

public class TwoSum {
    public static int[] indexnums;

    public static void main(String[] args) {
        int[] A = {1,2,4,7,11,15};
        int[] result = twoSum(A, 15);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    //双指针
    public static int[] twoSum(int[] nums, int target) {
        if(nums==null||nums.length<=1) {
            return null;
        }
        int i = 0;
        int j = nums.length-1;

        while(i<j) {
            int sum = nums[i]+nums[j];
            if(sum == target) {
                return new int[] {nums[i],nums[j]};
            } else if(sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return  new int[0];
    }


}
