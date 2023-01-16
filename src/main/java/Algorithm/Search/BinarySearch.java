package Algorithm.Search;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-10
 * @author yzh
 *
 *
 * 基本的二分查找
 * 时间复杂度：O(lgn)
 *
 * 给定一个排序的整数数组（升序且无重复的数）和一个要查找的整数target
 * 用O(lgn)的时间查找到target出现的下标（从0开始），如果target不存在于数组中，返回-1
 *
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 20, 22, 23, 234};
        System.out.println(binarySearch(nums, 0));
    }

    //方法1: 迭代版本
    public static int binarySearch(int[] nums, int target) {
        if(nums==null || nums.length==0 || target < nums[0] || target > nums[nums.length-1]) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;

        while (start <= end) {
            int mid = start+(end-start) / 2;
            //int mid = start+((end-start)>>1);
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        System.out.println(start);
        System.out.println(end);
        return  -1;
    }

    //方法2: 递归版本
    public static int binarySearch2(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;

        return binSHelper(nums, start, end, target);
    }

    public static int binSHelper(int[] nums, int start, int end, int target) {
        if (start <= end) {
            int mid = start+(end-start) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                return binSHelper(nums,start,mid-1,target);
            } else {
                return binSHelper(nums,mid+1,end,target);
            }
        } else {
            return -1;
        }
    }

}
