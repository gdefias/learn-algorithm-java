package Algorithm.Search;

/**
 * Created by Jeff on 2016/5/2.

 寻找峰值
 https://leetcode-cn.com/problems/find-peak-element/

 峰值元素是指其值大于左右相邻值的元素。

 给你一个输入数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 你可以假设nums[-1] = nums[n] = -∞

 1 <= nums.length <= 1000
 -231 <= nums[i] <= 231 - 1
 对于所有有效的 i 都有 nums[i] != nums[i + 1]

 进阶：你可以实现时间复杂度为 O(logN) 的解决方案吗？

 输入：nums = [1,2,3,1]
 输出：2
 解释：3 是峰值元素，你的函数应该返回其索引 2。

 输入：nums = [1,2,1,3,5,6,4]
 输出：1 或 5
 解释：你的函数可以返回索引 1，其峰值元素为 2；或者返回索引 5， 其峰值元素为 6。
 */

public class BinarySearchPeak {

    public static void main(String[] args) {
//        int[] array = new int[]{1, 2, 1, 3, 6, 5, 4};
        int[] array = new int[] {1, 2, 100};
        System.out.println(findPeak(array));
    }

    public static int findPeak(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }


    public static int findPeak2(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 1;
        int end = A.length - 2;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            }

            //在题目描述中出现了 nums[-1] = nums[n] = -∞，这就代表着 只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值
            if (A[mid] < A[mid - 1] && A[mid] > A[mid + 1]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if ((start == A.length - 1) && (A[start] > A[start - 1])) {
            return start;
        }

        if ((end == 0) && (A[end] > A[end + 1])) {
            return end;
        }

        return -1;
    }

    public static int findPeak3(int[] A) {

        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1])
                return i;
        }
        return A.length - 1;
    }
}
