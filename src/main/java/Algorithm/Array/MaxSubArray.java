package Algorithm.Array;

/**
 * Created with IntelliJ IDEA.
 * Description: 连续子数组最大和
 * User: Defias
 * Date: 2018-10
 * @author yzh
输入一个整形数组，数组里面有正数也有负数，数组中一个或连续的多个整数组成一个子数组。求所有子数组和的最大值，要求时间复杂度为O(n)

https://leetcode-cn.com/problems/maximum-subarray/comments/

输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

1 <= arr.length <= 10^5
-100 <= arr[i] <= 100
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] A = {-122, -23, -1, -2};
        System.out.println(maxSubArray(A));
    }

    public static int maxSubArray(int[] nums) {
        if(nums==null || nums.length==0) {
            return -1;
        }

        int maxsum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<=nums.length-1; i++) {
            if(sum>0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }

            if(sum>maxsum) {
                maxsum = sum;
            }
        }
        return maxsum;
    }

    public static int maxSubArray2(int[] nums) {
        int res = nums[0];
        for(int i=1; i<=nums.length-1; i++) {
            nums[i] += Math.max(0, nums[i-1]);
            res = Math.max(res, nums[i]);
        }
        return res;
    }
}
