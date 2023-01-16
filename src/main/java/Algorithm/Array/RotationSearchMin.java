package Algorithm.Array;

/**
 * Created by Defias on 2017/10/7.

 寻找旋转排序数组中的最小值 - 无重复元素
 https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/

 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

 给你一个元素值互不相同的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。


 输入：nums = [3,4,5,1,2]
 输出：1

 输入：nums = [4,5,6,7,0,1,2]
 输出：0

 输入：nums = [1]
 输出：1

 输入：numbers = [2,2,2,0,1]
 输出：0

 提示：
 n == nums.length
 1 <= n <= 5000
 -5000 <= nums[i] <= 5000
 nums 中的所有整数 互不相同
 nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */

public class RotationSearchMin {
    public static void main(String[] args) {
//        int[] nums = new int[] {3,4,5,6,7,1,2};
//        int[] nums = new int[] {1};
        int[] nums = new int[] {3,4,5,6,7};
        System.out.println(findMin(nums));
    }


    //二分查找
    //时间复杂度：时间复杂度为O(logn)，其中n是数组nums的长度。在二分查找的过程中，每一步会忽略一半的区间，因此时间复杂度为O(logn)。
    //空间复杂度：O(1)
    //思路：https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/solutions/698479/xun-zhao-xuan-zhuan-pai-xu-shu-zu-zhong-5irwp/
    public static int findMin(int[] nums) {
        int low = 0;
        int hight = nums.length-1;

        while(low < hight) {
            int mid = low + (hight-low)/2;
            if(nums[mid] < nums[hight]) {   //与右边界值进行比较
                hight = mid;  //nums[mid]有可能是最小值 所以mid不能丢弃
            } else {
                low = mid+1; //nums[mid]不可能是最小值 所以mid可以丢弃
            }
        }

        return nums[low];
    }

}
