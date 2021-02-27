package Algorithm.Sorts;

/**
 * Created by Jeff on 2016/3/27.
 *
 * 快速排序
 * 时间复杂度：O(nlgn) ~ O(n^2)
 * 空间复杂度：O(lgn) ~ O(n)
 *
 * 先整体有序，再局部有序
 *
 * 不稳定排序
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] A = new int[] {12,334,545,6,78,34,233,12,444,5,23,54,73,3,2,12,46,444};
        //int[] A = new int[] {};

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

       int provitw = partition(A, left, right);  //切分

        helper(A, left, provitw-1);   //递归
        helper(A, provitw+1, right);
    }

    //切分版本1
    public static int partition(int[] A, int left, int right) {
        int provit = A[left];  //选基准元素(第一个元素)

        while(left<right) {

            while(left<right && A[right]>provit) {   //选第一个元素为基准元素时从尾部开始扫描
                --right;
            }
            A[left] = A[right];

            while(left<right && A[left]<=provit) {
                ++left;
            }
            A[right] = A[left];
        }

        A[left] = provit;  //将基准元素放入正确的位置
        return left;
    }

    //切分版本2
    public static int partition2(int[] A, int left, int right) {
        int provit = A[right];  //选基准元素(最后一个元素)

        while(left<right) {
            while(left<right && A[left]<=provit) {   //选最后一个元素为基准元素时从头部开始扫描
                ++left;
            }
            A[right] = A[left];

            while(left<right && A[right]>=provit) {
                --right;
            }
            A[left] = A[right];
        }

        A[right] = provit;  //将基准元素放入正确的位置
        return right;
    }
}
