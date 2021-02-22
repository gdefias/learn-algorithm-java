package Test;

/**
 * Created by Defias on 2017/10/15.
 *
 * Longest Increasing Subsequence
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 最长上升子序列
 *
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 */


public class ArrayLongestIncreasingSubsequence {

    public static void main(String[] args) {
//        int[] A = {10, 9, 2, 5, 3, 7, 101, 18};
//        System.out.println(lengthOfLIS(A));

        int[] nums = new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        ArrayLongestIncreasingSubsequence solution = new ArrayLongestIncreasingSubsequence();
        int lengthOfLIS = lengthOfLIS1(nums);
        System.out.println("最长上升子序列的长度：" + lengthOfLIS);
    }

    //DP
    public static int lengthOfLIS(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int[] dp = new int[nums.length];


        dp[0] = 1;
        for(int i=1; i<nums.length; i++) {
            int maxlen = 1;
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i]) {
                    maxlen = Math.max(maxlen, dp[j]+1);
                }
            }
            dp[i] = maxlen;
        }

        int res = dp[0];
        for(int i=1; i<dp.length; i++) {
            if(dp[i]>res) {
                res = dp[i];
            }
        }
        return res;
    }

    //贪心算法、二分查找
    public static int lengthOfLIS1(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        // tail 数组的定义：长度为 i + 1 的上升子序列的末尾最小是几
        int[] tail = new int[len];
        // 遍历第 1 个数，直接放在有序数组 tail 的开头
        tail[0] = nums[0];
        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;

        for (int i = 1; i < len; i++) {
            // 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
            if (nums[i] > tail[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                end++;
                tail[end] = nums[i];
            } else {
                // 使用二分查找法，在有序数组 tail 中
                // 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
                int left = 0;
                int right = end;
                while (left < right) {
                    // 选左中位数不是偶然，而是有原因的，原因请见 LeetCode 第 35 题题解
                    // int mid = left + (right - left) / 2;
                    int mid = left + ((right - left) >>> 1);
                    if (tail[mid] < nums[i]) {
                        // 中位数肯定不是要找的数，把它写在分支的前面
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
                // 因此，无需再单独判断
                tail[left] = nums[i];
            }
            // 调试方法
            printArray(nums[i], tail);
        }
        // 此时 end 是有序数组 tail 最后一个元素的索引
        // 题目要求返回的是长度，因此 +1 后返回
        end++;
        return end;
    }

    // 调试方法，以观察是否运行正确
    private static void printArray(int num, int[] tail) {
        System.out.print("当前数字：" + num);
        System.out.print("\t当前 tail 数组：");
        int len = tail.length;
        for (int i = 0; i < len; i++) {
            if (tail[i] == 0) {
                break;
            }
            System.out.print(tail[i] + ", ");
        }
        System.out.println();
    }

}
