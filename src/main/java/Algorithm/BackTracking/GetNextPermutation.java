package Algorithm.BackTracking;

import  static Lib.Base.*;
/**
 * Created by Defias on 2020/07.
 * Description: 下一个排列

 https://leetcode-cn.com/problems/next-permutation/

 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

 必须原地修改，只允许使用额外常数空间。

 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 123465 --> 123546
 */
public class GetNextPermutation {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4,6,5};

        nextPermutation(nums);

        printArray(nums);
    }

    public static void nextPermutation(int[] nums) {
        if(nums==null || nums.length<2) {
            return;
        }

        int i = nums.length-2;
        while(i>=0 && nums[i]>=nums[i+1]) {
            i--;
        }

        if(i>=0) {
            int j = nums.length-1;
            while(j>=0 && nums[j]<=nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i+1, nums.length-1);
    }

    public static void reverse(int[] nums, int p, int q) {
        while(p<q) {
            swap(nums, p, q);
            p++;
            q--;
        }
        return;
    }
}
