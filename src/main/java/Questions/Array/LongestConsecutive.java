package Questions.Array;

import java.util.HashSet;

/**
 * Created by Defias on 2017/10/7.

 最长连续序列
 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度

 https://leetcode-cn.com/problems/longest-consecutive-sequence/

 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？

 输入：nums = [100,4,200,1,3,2]
 输出：4
 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 输出：9

 0 <= nums.length <= 10^4
 -10^9 <= nums[i] <= 10^9


 */

public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = new int[] {-5, 1, 2, -3, 3, 6};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            set.add(nums[i]);
        }

        int longest = 0;
        for (int i=0; i<nums.length; i++) {
            int down = nums[i] - 1;
            while (set.contains(down)) {
                set.remove(down);
                down--;
            }
            int up = nums[i] + 1;
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }

            longest = Math.max(longest, up-down-1);
        }
        return longest;
    }

    //DP
    //public static int longestConsecutive2(int[] nums) {
    //
    //}
}
