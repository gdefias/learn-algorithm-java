package Algorithm.Array;

/**
 * @author: Felix
 * @date: 2022/12/30
 * @description:

  寻找旋转排序数组中的最小值 II - 可能存在重复元素
  https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/
  https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/


    已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
    例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
    若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
    若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
    注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

    给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
    你必须尽可能减少整个过程的操作步骤。

    输入：nums = [1,3,5]
    输出：1

    输入：numbers = [2,2,2,0,1]
    输出：0

    提示：
    n == nums.length
    1 <= n <= 5000
    -5000 <= nums[i] <= 5000
    nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */

public class RotationSearchMin2 {

    public static void main(String[] args) {
//        int[] nums = new int[] {3,4,5,6,7,1,2};
        int[] nums = new int[] {2};
//        int[] nums = new int[] {2,2,2,3,4,0,1,2};
        System.out.println(findMin2(nums));
    }

    //方法1：二分查找
    //与右边界值进行比较  [4,5,6,7,0,0,1,2,3]  [2,2,2,3,4,0,1,2]   [2,2,2,3,4,2]
    //题解：https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solutions/340801/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-by-leetcode-s/
    public static int findMin(int[] nums) {
        int low = 0;
        int hight = nums.length-1;

        while(low < hight) {
            int mid = low + (hight-low)/2;
            if(nums[mid] < nums[hight]) {
                hight = mid;  //nums[mid]有可能是最小值 所以mid不能丢弃
            } else if(nums[mid] > nums[hight]) {
                low = mid+1; //nums[mid]不可能是最小值 所以mid可以丢弃
            } else {  //因为可能存在重复，所以有可能相等，此时最小值有可能在mid的左边也有可能在mid的右边，唯一可做的是把上界减小1
                hight--;
            }
        }
        return nums[low];
    }

    //方法2：迭代
    //找到比前一个元素小的元素即是最小元素
    public static int findMin2(int[] nums) {
        int len = nums.length;
        int pre = 0;
        for(int i=0; i<len; i++) {
            pre = i-1>=0? i-1: i-1+len;
            if(nums[i] < nums[pre]) {
                return nums[i];
            }
        }

        return nums[0];
    }
}
