package Questions.Greedy;

/**
 * Created by Defias on 2020/06.
 * Description: 最大连续子数组和

 贪心思想

 连续子数组的最大和
 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值
 要求时间复杂度为O(n)

 https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/


 1 <= arr.length <= 10^5
 -100 <= arr[i] <= 100

 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6

 */

public class MaxSubArray {

    public static void main(String[] args) {
        int[] A = {13, -3, -25, 20, -3, -16, -23, 0, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int result = maxSubArraySum(A);
        System.out.println(result);
    }


    //贪心  O(n)
    public static int maxSubArraySum(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++) {
            sum = sum + nums[i];
            max = Math.max(max, sum);

            sum = Math.max(sum, 0); //sum最小就是0，不取负数，大不了一个也不取，子数组为空数组
        }
        return max;
    }

}
