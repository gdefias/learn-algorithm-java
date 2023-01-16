package Algorithm.Array;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author: Felix
 * @date: 2023/1/2
 * @description:  连续子数组的最大和

    给你一个整数数组nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素） 返回子数组的左边界、有边界及最大和
    子数组 是数组中的一个连续部分。

    输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
    输出: [3, 6, 6]
    解释: 连续子数组 [4,-1,2,1] 的和最大，左右边界下标分别为3,6，和为6

    输入：nums = [1]
    输出：[0,0,1]
    解释: 连续子数组 [1] 的和最大，左右边界下标分别为0,0，和为1

    输入：nums = [5,4,-1,7,8]
    输出：[0,4,23]
    解释: 连续子数组 [5,4,-1,7,8] 的和最大，左右边界下标分别为0,4，和为23
 */
public class MaxSubArray2 {

    public static void main(String[] args) {
        int[] A = {-2,1,-3,4,-1,2,1,-5,4};
        int[] result = maxSubArray(A);

        for(int i=0; i<result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }


    //方法1：分治
    public static int[] maxSubArray(int[] A) {
        if(A==null || A.length==0) {
            return null;
        }

        int low = 0;
        int high = A.length-1;
        int[] result = maxSubArray(A, low, high);
        return result;
    }

    //求和最大的连续子数组的左右下标及和
    public static int[] maxSubArray(int[] A, int low, int high) {
        int[] result = new int[3];

        //子数组只含1个元素的基本情况
        if(low == high) {
            result[0] = low;    //子数组左边界
            result[1]  = high;   //子数组右边界
            result[2]  = A[low];  //最大子数组的和
            return  result;
        }


        //最大子数组要么在中点的左边，要么在中点的右边，要么跨越中点两边
        int mid = low+(high-low)/2;
        int[] leftres = maxSubArray(A, low, mid);
        int[] rightres = maxSubArray(A, mid+1, high);

        //跨越中点两边
        int[] midres = maxCrossSubArray(A, low, mid, high);

        //哪个和最大即为最终结果
        if(leftres[2] >= rightres[2] && leftres[2] >= midres[2]) {  //比较三个子数组的和
            return leftres;
        } else if(rightres[2] >= leftres[2] && rightres[2] >= midres[2]) {
            return rightres;
        } else {
            return midres;
        }

    }

    public static int[] maxCrossSubArray(int[] A,int low, int mid, int high) {
        //中点左右两边的最大和
        int leftsum = Integer.MIN_VALUE;
        int rightsum = Integer.MIN_VALUE;

        //中点左右两边最远的边界
        int leftindex = mid;
        int rightindex = mid+1;

        int sum = 0;  //累加和

        //寻找左边界和中点左边的最大和  贪心
        for(int i=mid; i>=low; --i) {
            sum += A[i];
            if(sum >= leftsum) {
                leftsum = sum;
                leftindex = i;   //左边离中心最远的边界
            }
        }

        //寻找右边界和中点右边的最大和  贪心
        sum = 0;
        for (int i=mid+1; i<=high; ++i) {
            sum += A[i];
            if(sum >= rightsum) {
                rightsum = sum;
                rightindex = i;  //右边离中心最远的边界
            }
        }

        //返回左右边界及最大和
        int[] res = new int[3];
        res[0] = leftindex;
        res[1] = rightindex;
        res[2] = leftsum+rightsum;
        return  res;
    }
}
