package Algorithm.Sort.Compare;

/**
 * Created by Jeff on 2016/3/27.
 *
 * 快速排序 - 双指针版本 - 把等于切分元素的所有元素分到了数组的两侧
 *
 * 时间复杂度：O(nlgn) ~ O(n^2)
 * 空间复杂度：O(lgn) ~ O(n)
 *
 * 先整体有序，再局部有序
 * 不稳定排序
 *
 */
public class QuickSort3 {

    public static void main(String[] args) {
//        int[] A = new int[] {12,334,545,6,78,34,233,12,444,5,23,54,73,3,2,12,46,444};
        int[] A = new int[] {5,2,3,1};
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

        quickSort(A, left, right);

    }

    public static void quickSort(int[] A, int left, int right) {
        if(left>=right) {
            return;
        }

        int mid = partition(A, left, right);  //切分
//        int mid = partition2(A, left, right);

        quickSort(A, left, mid-1);   //递归
        quickSort(A, mid+1, right);
    }

    //切分版本1
    public static int partition(int[] A, int left, int right) {
        int pv = A[left];  //选基准元素(第一个元素)

        while(left<right) {
            while(left<right && A[right]>=pv) {   //选第一个元素为基准元素时从尾部开始扫描
                --right;
            }
            A[left] = A[right];

            while(left<right && A[left]<=pv) {
                ++left;
            }
            A[right] = A[left];
        }

        A[left] = pv;  //将基准元素放入正确的位置
        return left;
    }

    //切分版本2
    public static int partition2(int[] A, int left, int right) {
        int pv = A[right];  //选基准元素(最后一个元素)

        while(left<right) {
            while(left<right && A[left]<=pv) {  //选最后一个元素为基准元素时从头部开始扫描
                ++left;
            }
            A[right] = A[left];

            while(left<right && A[right]>=pv) {
                --right;
            }
            A[left] = A[right];
        }

        A[right] = pv;  //将基准元素放入正确的位置
        return right;
    }
}
