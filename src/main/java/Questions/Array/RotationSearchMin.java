package Questions.Array;

/**
 * Created by Defias on 2017/10/7.

 寻找旋转排序数组中的最小值

 https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/

 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]
 请找出其中最小的元素

 示例 1：
 输入：nums = [3,4,5,1,2]
 输出：1

 示例 2：
 输入：nums = [4,5,6,7,0,1,2]
 输出：0

 示例 3：
 输入：nums = [1]
 输出：1

 1 <= nums.length <= 5000
 -5000 <= nums[i] <= 5000
 nums 中的所有整数都是 唯一 的
 nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转

 */

public class RotationSearchMin {

    public static int findMin(int[] num) {
        if(num==null || num.length==0) {
            return -1;
        }

        int num0 = num[0];
        int start = 1;
        int end = num.length-1;
        while(start < end-1) {
            int mid  = start + (end - start) / 2;
            if(num[mid] < num[mid-1]) {
                return num[mid];
            } else if(num[mid]>num0) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if(num[start] < num[start-1]) {
            return num[start];
        }

        if(num[end] < num[end-1]) {
            return num[end];
        }
        return num0;
    }
}
