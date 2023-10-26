package Algorithm.Sort.Compare;
import static  Lib.Base.*;
/**
 * @author: Felix
 * @date: 2022/12/23
 * @description:
 *
 * 快速排序 - 基础版本 - 把等于切分元素的所有元素分到了数组的同一侧，可能会造成递归树倾斜
 *
 * 时间复杂度：O(nlgn) ~ O(n^2)
 * 空间复杂度：O(lgn) ~ O(n)
 *
 * 先整体有序，再局部有序
 * 不稳定排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] A = new int[] {12,334,545,6,78,34,233,12,444,5,23,54,73,3,2,12,46,444};

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

    public static int partition(int[] A, int left, int right) {
        int pv = A[left];  //基准元素一直不变
        int lt = left;  //lt作为边界，lt及其左边的元素都小于或等于lt所在的元素，lt右边的元素都大于lt所在的元素

        for (int i=left+1; i<=right; i++) {
            if (A[i] <= pv) {
                lt++;
                swap(A, i, lt);
            }
        }

        swap(A, left, lt);
        return lt;
    }

    public static int partition2(int[] A, int left, int right) {
        int pv = A[right];
        int lt = left-1;  //lt: 比基准小或等的元素的右边界(同时也就确定了比基准大的元素的左边界)

        for (int i=left; i<right; i++) {
            if (A[i] <= pv) {
                lt++;
                swap(A, i, lt);
            }
        }

        swap(A, lt+1, right);
        return lt+1;
    }
}
