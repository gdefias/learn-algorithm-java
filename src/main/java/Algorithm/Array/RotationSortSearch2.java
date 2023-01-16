package Algorithm.Array;

/**
 * @author: Felix
 * @date: 2022/12/30
 * @description: 搜索旋转排序数组 II - 可能存在重复元素

    https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/description/

    已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为
    [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
    例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。

    给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值
    target ，则返回 true ，否则返回 false 。
    你必须尽可能减少整个操作步骤。

    输入：nums = [2,5,6,0,0,1,2], target = 0
    输出：true

    输入：nums = [2,5,6,0,0,1,2], target = 3
    输出：false

    提示：
    1 <= nums.length <= 5000
    -104 <= nums[i] <= 104
    题目数据保证 nums 在预先未知的某个下标上进行了旋转
    -104 <= target <= 104

 */
public class RotationSortSearch2 {

    public static void main(String[] args) {
        int[] nums = new int[] {2,5,6,0,0,1,2};
        System.out.println(search(nums, 2));
    }

    //二分查找
    //与右边界值进行比较
    public static boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while(start < end) {
            int mid = start+(end-start)/2;
            if(target == nums[mid]) {
                return true;
            }

            //当没找到时需要区分当前mid是在前面的递增区间还是后面的递增区间
            if(nums[mid] > nums[end]) {
                if(target > nums[mid] || target <= nums[end]) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            } else if(nums[mid] < nums[end]) {
                if(target < nums[mid] || target > nums[end]) {
                    end = mid-1;
                } else {
                    start = mid+1;
                }
            } else {  //mid值与上界值相等时，上界减小
                end--;
            }
        }

        if(nums[start] == target) {
            return true;
        }
        return false;
    }

    //二分查找
    //与左边界值进行比较
    public static boolean search_(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        while(start < end) {
            int mid = start+(end-start)/2;
            if(target == nums[mid]) {
                return true;
            }

            if(nums[mid] > nums[start]) {
                if(target<nums[mid] && target>=nums[start]) {
                    end = mid-1;
                } else {
                    start = mid+1;
                }

            } else if(nums[mid] < nums[start]) {
                if(target>nums[mid] && target<nums[start]) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            } else {
                start++;
            }
        }

        if(nums[start] == target) {
            return true;
        }
        return false;
    }


}
