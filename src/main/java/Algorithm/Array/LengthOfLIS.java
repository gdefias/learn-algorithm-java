package Algorithm.Array;

/**
 * @author: Defias
 * @date: 2021/3/12
 * @description: 最长递增子序列

给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

https://leetcode-cn.com/problems/longest-increasing-subsequence/
 
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4

输入：nums = [0,1,0,3,2,3]
输出：4

输入：nums = [7,7,7,7,7,7,7]
输出：1

提示：
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 

进阶：
你可以设计时间复杂度为 O(n2) 的解决方案吗？
你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = new int[] {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS2(nums));
    }


    //DP
    //dp[j] 代表 nums[0…j] 中以 nums[j] 结尾的最长上升子序列
    public static int lengthOfLIS(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxlen = 1;
        for(int i=1; i<nums.length; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }

            maxlen = Math.max(maxlen, dp[i]);
        }

        return maxlen;
    }


    //贪心 + 二分
    //数组d[i]，表示长度为i+1的最长上升子序列的末尾元素的最小值 从0开始
    public static int lengthOfLIS2(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        int[] d = new int[nums.length];
        int len = 0;
        d[len] = nums[0];

        for(int i=1; i<nums.length; i++) {
            if(nums[i]>d[len]) {
                d[++len] = nums[i];
            } else {
                int index = binSearch(d, len, nums[i]);
                d[index] = nums[i];
            }
        }
        return len+1;
    }

    public static int binSearch(int[] d, int end, int target) {
        int i = 0;
        int j = end;

        while(i<=j) {

            int mid = i+(j-i)/2;
            if(d[mid]>target) {
                if(mid==0 || d[mid-1]<target) {
                    return mid;
                } else {
                    j = mid-1;
                }
            } else {
                if(d[mid+1]>target) {
                    return mid+1;
                } else {
                    i = mid+1;
                }
            }
        }

        return 0;
    }
}
