package Algorithm.Array;

/**
 * Created by Defias on 2020/07.
 * Description: 0～n-1中缺失的数字

 https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/

 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，
 请找出这个数字

 输入: [0,1,3]
 输出: 2

 输入: [0,1,2,3,4,5,6,7,9]
 输出: 8

 [0,2,3,4,5,6,7,8,9]
 [0,1,2,3,5,6,7,8,9]

 1 <= 数组长度 <= 10000
 */

public class MissingNumber {
    public int missingNumber(int[] nums) {
        int i = 0;
        int j = nums.length-1;

        //二分法思想
        while(i<=j) {
            int mid = i+(j-i)/2;
            if(nums[mid]!=mid) {
                if((mid-1<0) || (nums[mid-1]!=mid)) {
                    return mid;
                } else {
                    j = mid-1;
                }
            } else {
                i = mid+1;
            }
        }

        return i;
    }
}
