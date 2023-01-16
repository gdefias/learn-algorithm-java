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
     3,2,1 → 1,2,3   2 3 1
     1,1,5 → 1,5,1
     1,2,3,4,6,5 --> 1,2,3,5,4,6
     1,2,3,6,7,5 --> 1,2,3,7,5,6
 */
public class GetNextPermutation {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,6,7,5};

        nextPermutation(nums);

        printArray(nums);
    }


    //两遍扫描
    //思路：需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列
    //同时要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列
    //这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小
    public static void nextPermutation(int[] nums) {
        if(nums==null || nums.length<2) {
            return;
        }

        //从后向前寻找尽量靠右的「较小数」的位置
        int i = nums.length-2;
        for(; i>=0; i--) {
            if(nums[i] < nums[i+1]) { //从后向前如果一直是升序，就要一直往前推进，如果一直没找到就直到数组推进完，此时: i==-1
                break;
            }
        }

        if(i >= 0) {
            int j = nums.length-1;
            for(; j>=0; j--) {
                if(nums[j] > nums[i]) { //从后向前寻找比「较小数」大的「较大数」（第一个被找到的一定是比「较小数」大的最小的那一个）
                    break;
                }
            }
            swap(nums, i, j);  //将满足条件的「较小数」和「较大数」交换
        }

        reverse(nums, i+1, nums.length-1);  //交换后「较大数」之后的数升序排列使变大的幅度最小
        //为什么是翻转而不是排序？
        //使用排序肯定是可以的但是没必要，因为交换前「较小数」之后是降序的，而「较大数」是比「较小数」大的最小的那一个，所以交换后，
        //原来「较小数」之后（也就是当前「较大数」之后）仍然是降序的，所以只需要翻转即成为升序的
    }

    //翻转
    public static void reverse(int[] nums, int p, int q) {
        while(p < q) {
            swap(nums, p, q);
            p++;
            q--;
        }
        return;
    }
}
