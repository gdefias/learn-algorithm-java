package Algorithm.DivideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Defias on 2020/06.
 * Description: 连续最大子数组  O(nlgn)

  分治思想
 */


public class MaxSubArray {

    public static void main(String[] args) {
        int[] A = {13, -3, -25, 20, -3, -16, -23, 0, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int[] result = findMaxSubArray(A);
        System.out.println(Arrays.stream(result).boxed().collect(Collectors.toList()));

    }

    //寻找最大子数组
    public static int[] findMaxSubArray(int[] A) {
        return findMaxSubArray(A, 0, A.length-1);
    }


    public static int[] findMaxSubArray(int[] A, int low, int high) {
        if(A==null || A.length==0 || low>high ) {
            return null;
        }
        int[] result = new int[3];

        if(low == high) {
            result[0] = low;    //返回子数组左边界
            result[1]  = high;   //返回子数组右边界
            result[2]  = A[low];  //返回子数组的和
            return  result;
        }

        int mid = low+(high-low)/2;
        int[] leftres = findMaxSubArray(A, low, mid);
        int[] rightres = findMaxSubArray(A, mid+1, high);

        int[] midres = findMaxCrossSubArray(A, low, mid, high);

        //最大子数组要么在中点的左边，要么在中点的右边，要么跨越中点两边，哪个和最大即为最终结果
        if(leftres[2] >= rightres[2] && leftres[2] >= midres[2]) {  //比较三个子数组的和
            return leftres;
        } else if(rightres[2] >= leftres[2] && rightres[2] >= midres[2]) {
            return rightres;
        } else {
            return midres;
        }

    }

    //寻找跨越中点的最大子数组
    public static int[] findMaxCrossSubArray(int[] A,int low, int mid, int high) {
        if(A==null || A.length==0 || low>mid || low>high) {
            return null;
        }

        int leftsum = Integer.MIN_VALUE;
        int rightsum = Integer.MIN_VALUE;
        int sum = 0;
        int leftindex = mid;
        int rightindex = mid+1;   // 左右边界


        //寻找左边界
        for(int i=mid; i>=low; --i) {
            sum += A[i];
            if(sum>=leftsum) {
                leftsum = sum;
                leftindex = i;
            }
        }

        //寻找右边界
        sum = 0;
        for (int i=mid+1; i<=high; ++i) {
            sum += A[i];
            if(sum>=rightsum) {
                rightsum = sum;
                rightindex = i;
            }
        }

        //返回左右边界和最大和
        int[] res = new int[3];
        res[0] = leftindex;
        res[1] = rightindex;
        res[2] = leftsum+rightsum;
        return  res;
    }


}
