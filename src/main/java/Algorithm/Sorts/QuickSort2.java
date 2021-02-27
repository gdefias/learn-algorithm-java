package Algorithm.Sorts;

import static  Lib.Base.*;
/**
 * Created by Defias on 2020/06.
 * Description: 快速排序  另一个版本
 *
 */

public class QuickSort2 {
    public static void main(String[] args) {
        int[] A = new int[] {12,334,545,6,78,34,233,444,5,23,54,73,3,2,12,46};
        Sort(A);

        for(int n: A) {
            System.out.println(n);
        }
    }

    public static void Sort(int[] A) {
        if(A==null || A.length<1) {
            return;
        }

        int left = 0;
        int right = A.length-1;

        helper(A, left, right);

    }

    public static void helper(int[] A, int left, int right) {
        if(left>=right) {
            return;
        }

        int[] provit = partition(A, left, right);  //切分

        helper(A, left, provit[0]);   //递归
        helper(A, provit[1], right);
    }


    //版本2
    public static int[] partition(int[] A, int left, int right) {
        int provit = A[left+(right-left)/2];
        int[] res = new int[2];
        int i = left;
        int j = right;

        while(i<=j) {
            while(i<=j && A[i]<provit) {
                ++i;
            }

            while(i<=j && A[j]>provit) {
                --j;
            }

            if(i<=j) {
                swap(A, i, j);
                ++i;
                --j;
            }
        }

        res[0]=j;   //j成为小于或等于基准元素的的右边界
        res[1]=i;   //i成为大于或等于基准元素的的左边界
        return res;
    }

}
