package Algorithm.DivideAndConquer;

/**
 * Created by Defias on 2020/07.
 * Description:  数组中的逆序对

     https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/

     在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数
     逆序对： 前大后小

     输入: [7,5,6,4]
     输出: 5

     归并排序（分治思想）
 */
public class ReversePairs {

    public static void main(String[] args) {
        ReversePairs reversePairs = new ReversePairs();
        int[] nums = new int[] {2,3,6,7,0,1,4,5};
        System.out.println(reversePairs.reversePairs(nums));
    }


    //方法1：分治  归并排序的思想
    //时间复杂度：同归并排序 O(nlogn)
    //空间复杂度：同归并排序 O(n)，因为归并排序需要用到一个临时数组
    public int reversePairs(int[] nums) {
        if(nums==null || nums.length<2) {
            return 0;
        }

        int left = 0;
        int right = nums.length-1;
        int[] tmp = new int[nums.length];
        return reversePairs(nums, left, right, tmp);
    }


    //对nums数组进行归并排序，并返回逆序对的个数
    public int reversePairs(int[] nums, int left, int right, int[] tmp) {
        if(left >= right) {
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
            if(i<=mid && (j>right || nums[i]<=nums[j])) {  //另一种方式：当左边的数较小时，那当前右边的数的左侧（右边已经遍历过的）的数都比这个左边的数小，构成j-mid-1个逆序对
                tmp[k] = nums[i];
                i++;
//                count += (j-mid-1);
            } else {  //当右边的数较小时，那当前左边的数及左边其余的数都比这个右边的数大，构成mid-i+1个逆序对
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
