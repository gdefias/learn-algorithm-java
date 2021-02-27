package Algorithm.Array;

/**
 * Created by Defias on 2017/10/7.

 寻找旋转排序数组中的最小值
 https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/

 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]
 请找出其中最小的元素

 输入：nums = [3,4,5,1,2]
 输出：1

 输入：nums = [4,5,6,7,0,1,2]
 输出：0

 输入：nums = [1]
 输出：1

 输入：[3,4,5,1,2]
 输出：1

 1 <= nums.length <= 5000
 -5000 <= nums[i] <= 5000
 nums 中的所有整数都是 唯一 的
 nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转
 */

public class RotationSearchMin {
    public static void main(String[] args) {
        int[] num = new int[] {4,5,6,7,1,2};
        System.out.println(findMin(num));
    }

    public static int findMin(int[] num) {
        if(num==null || num.length==0) {
            return -1;
        }

        int low = 0;
        int hight = num.length-1;
        if(num[hight] > num[low]) {
            return num[low];
        }

        while(low<hight) {
            int mid = low + (hight-low)/2;
            if(num[mid] > num[hight]) {
                low = mid+1;
            } else if(num[mid] < num[hight]) {
                hight = mid;
            } else {
                hight--;
            }
        }

        return num[low];
    }

    public static int findMin2(int[] num) {
        if(num==null || num.length==0) {
            return -1;
        }

        if(num.length==1) {
            return num[0];
        }

        int start = 0;
        int end = num.length-1;
        if(num[end] > num[start]) {
            return num[start];
        }

        while(start <= end) {
            int mid  = start + (end - start) / 2;
            if(num[mid] > num[mid+1]) {
                return num[mid + 1];
            }
            if(num[mid] < num[mid-1]) {
                return num[mid];
            }

            if(num[mid]>num[start]) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return -1;
    }
}
