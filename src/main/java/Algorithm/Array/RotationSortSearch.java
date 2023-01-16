package Algorithm.Array;

/**
 * @author: Felix
 * @date: 2022/12/30
 * @description: 搜索旋转排序数组 - 无重复元素

    https://leetcode.cn/problems/search-in-rotated-sorted-array/

    整数数组 nums 按升序排列，数组中的值互不相同 。
    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为
    [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
    例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

    给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
    你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

    输入：nums = [4,5,6,7,0,1,2], target = 0
    输出：4

    输入：nums = [4,5,6,7,0,1,2], target = 3
    输出：-1

    输入：nums = [1], target = 0
    输出：-1


    提示：
    1 <= nums.length <= 5000
    -10^4 <= nums[i] <= 10^4
    nums 中的每个值都 独一无二
    题目数据保证 nums 在预先未知的某个下标上进行了旋转
    -10^4 <= target <= 10^4
 */
public class RotationSortSearch {

    public static void main(String[] args) {
        int[] nums = new int[] {4,5,6,7,0,1,2};
        System.out.println(search(nums, 2));
    }

    //方法1：二分查找
    //与右边界值进行比较
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while(start < end) {
            int mid = start+(end-start)/2;
            if(target == nums[mid]) {
                return mid;
            }

            //当没找到时需要区分当前mid是在前面的递增区间还是后面的递增区间
            if(nums[mid] > nums[end]) {
                if(target > nums[mid] || target <= nums[end]) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            } else {
                if(target < nums[mid] || target > nums[end]) {
                    end = mid-1;
                } else {
                    start = mid+1;
                }
            }
        }

        if(nums[start] == target) {
            return start;
        }
        return -1;
    }

    //二分查找
    //与左边界值进行比较
    public static int search_(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        while(start < end) {
            int mid = start+(end-start)/2;
            if(target == nums[mid]) {
                return mid;
            }

            if(nums[mid] > nums[start]) {  //[1,3]
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
            return start;
        }
        return -1;
    }


    //方法2：两次二分查找
    //先找到最小值的位置，得到两个递增数组
    //判断target在哪个递增数组中
    //在所在的递增数组中再进行一次二分查找
    public static int search2(int[] arr, int target) {
        int start = 0;
        int end = arr.length-1;
        int minIndex = 0;

        while(start < end) {
            int mid = start+(end-start)/2;
            if(arr[mid] < arr[end]) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        minIndex = start;

        if(target == arr[arr.length-1]) {
            return arr.length-1;
        } else if(target > arr[arr.length-1]) {
            start = 0;
            end = minIndex-1;
        } else {
            start = minIndex;
            end = arr.length-1;
        }


        while (start < end) {
            int mid = start+(end-start)/2;
            if(arr[mid] == target) {
                return mid;
            } else if(arr[mid] < target) {
                end = mid+1;
            } else {
                start = mid-1;
            }
        }

        if(arr[start] == target) {
            return start;
        }

        return -1;
    }
}
