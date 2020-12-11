package Questions.Array;

/**
 * Created with IntelliJ IDEA.
 * Description: 数组中的逆序对
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/

 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对，输入一个数组，求出这个数组中的逆序对的总数

 输入: [7,5,6,4]
 输出: 5

 0 <= 数组长度 <= 50000
 */

public class ReversePairs {
    public static void main(String[] args) {
        ReversePairs O = new ReversePairs();
        int[] A = {7, 5, 6, 4};
        System.out.println(O.reversePairs(A));
    }


    public int reversePairs(int[] nums) {
        if(nums==null || nums.length<2) {
            return 0;
        }

        int left = 0;
        int right = nums.length-1;
        int[] tmp = new int[nums.length];
        return reversePairs(nums, left, right, tmp);
    }

    //分治法  归并排序的思想
    //对nums排序并统计返回逆序对的数量
    public int reversePairs(int[] nums, int left, int right, int[] tmp) {
        if(left>=right) {
            return 0;
        }

        int mid = left+(right-left)/2;
        int leftres = reversePairs(nums, left, mid, tmp);
        int rightres = reversePairs(nums, mid+1, right, tmp);

        //分治的左右两边无交集，不存在一个逆序对的第一个数在左部分另一个数在右部分的情况
        if(nums[mid] <= nums[mid+1]) {
            return leftres + rightres;
        }

        //分治的左右两边有交集，存在一个逆序对的第一个数在左部分另一个数在右部分的情况
        int mergeres = mergePairs(nums, left, mid, right, tmp);
        return leftres + rightres + mergeres;
    }

    //归并过程中统计逆序对
    public int mergePairs(int[] nums, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid+1;
        int count = 0;
        for(int k=left; k<=right; k++) {
            if(i<=mid && (j>right || nums[i]<=nums[j])) {
                tmp[k] = nums[i];
                i++;
            } else {
                tmp[k] = nums[j];
                j++;
                count += (mid-i+1);
            }
        }

        for(int k=left; k<=right; k++) {
            nums[k] = tmp[k];
        }
        return count;
    }
}
