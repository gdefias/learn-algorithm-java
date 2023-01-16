package Algorithm.Array;

/**
 * Created by Defias on 2020/07.
 * Description: 长度最小的子数组

 https://leetcode.cn/problems/minimum-size-subarray-sum/

 给定一个含有n个正整数的数组和一个正整数s ，找出该数组中满足其和 ≥ s 的长度最小的子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。

 示例：

 输入：s = 7, nums = [2,3,1,2,4,3]
 输出：2
 解释：子数组[4,3]是该条件下的长度最小的子数组。
 */
public class MinSubArrayLen {
    public static void main(String[] args) {
        int[] nums = new int[] {2,3,1,2,4,3};

        System.out.println(minSubArrayLen(7,nums));
    }



    //方法1：滑动窗口
    //时间复杂度：O(n) 其中n是数组的长度。指针start和end最多各移动n次
    //空间复杂度：O(1)
    public static int minSubArrayLen(int s, int[] nums) {
        if(nums==null || nums.length==0 || s<=0) {
            return 0;
        }

        int i = 0;
        int j = i;
        int minlen = Integer.MAX_VALUE;
        int sum = nums[0];

        while(i<=j && j<nums.length) {
            if(sum < s) {
                if(++j < nums.length) {
                    sum += nums[j];
                }

            } else {
                minlen = Math.min(minlen, j-i+1);
                if(minlen==1) {  //提前返回
                    return minlen;
                }

                if(sum == s) {
                    if(++j < nums.length) {
                        sum += nums[j];
                    }
                    sum -= nums[i++];
                } else {
                    sum -= nums[i++];
                }
            }
        }

        return minlen!=Integer.MAX_VALUE? minlen : 0;
    }

    //方法2：前缀和 + 二分查找
    //时间复杂度：O(nlogn)  空间复杂度：O(1)
//    public static int minSubArrayLen2(int s, int[] nums) {
//
//    }


    //方法3：暴力法
    //时间复杂度：O(n2)  空间复杂度：O(1)
//    public static int minSubArrayLen2(int s, int[] nums) {
//
//    }
}
