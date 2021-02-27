package Algorithm.Search;

/**
 * Created by Defias on 2017/10/7.
 *
 * 二分查找：查找第一次出现
 * 时间复杂度：O(lgn)
 *
 * 给定一个排序的整数数组（升序且可能包含重复的数）和一个要查找的整数target，
 * 用O(lgn)的时间查找到target第一次出现的下标（从0开始），如果target不存在于数组中，返回-1
 *
 */

public class BinarySearchFrist {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 22, 22, 22, 234};
        System.out.println(binarySearch(nums, 22));
    }

    //方法1
    public static int binarySearch(int[] nums, int target) {
        if(nums==null || nums.length ==0 || target < nums[0] || target > nums[nums.length-1]) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;

        while(start <= end) {
            int mid = start+(end-start) / 2;
            if(nums[mid] >= target) {
                end = mid-1;     //相等继续往左二分找（因为要找第一次出现的），即使左边再也找不到了，最终start也会刚好指向本次mid
            } else  {
                start = mid+1;
            }
        }

        if(start>=nums.length || nums[start]!=target) {
            return -1;
        }
        return start;
    }

    //方法2
    public static int binarySearch1(int[] nums, int target) {
        if(nums==null || nums.length ==0 || target < nums[0] || target > nums[nums.length-1]) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;

        while(start <= end) {
            int mid = start+(end-start) / 2;
            if(nums[mid]>target) {
                end = mid-1;
            } else if(nums[mid]<target) {
                start = mid+1;
            } else {
                if(mid==0 || nums[mid-1]!=target) { //相等直接判断左边是否还有相同的值，没有说明当前就是第一个
                    return mid;
                } else {
                    end = mid-1;    //否则说明左边还有，所以往左继续二分找
                }
            }
        }

        return -1;
    }

    //方法3： 对方法2进行了分支合并优化
    public static int binarySearch2(int[] nums, int target) {
        if(nums==null || nums.length ==0 || target < nums[0] || target > nums[nums.length-1]) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;

        while(start <= end) {
            int mid = start+(end-start) / 2;
            if(nums[mid]<target) {
                start = mid+1;
            } else {
                if(mid==0 || nums[mid-1]<target) {
                    return mid;
                } else {
                    end = mid-1;
                }
            }
        }
        return -1;
    }

}
