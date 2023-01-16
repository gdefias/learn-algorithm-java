package Algorithm.Sort.Compare;

import java.util.Random;
import static  Lib.Base.*;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-10
 * 快速排序 - 三指针版本 - 把等于切分元素的所有元素挤到了数组的中间，在有很多元素和切分元素相等的情况下，递归区间大大减少
 *
 * 时间复杂度：O(nlgn) ~ O(n^2)
 * 空间复杂度：O(lgn) ~ O(n)
 *
 * 先整体有序，再局部有序
 * 不稳定排序
 *
 */
public class QuickSort4 {
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

        quickSort(A, left, right);

    }

    public static void quickSort(int[] A, int left, int right) {
        if(left >= right) {
            return;
        }

        int[] mids = partition(A, left, right);  //切分


        quickSort(A, left, mids[0]);
        quickSort(A, mids[1], right);
    }


    public static int[] partition(int[] A, int left, int right) {
        int[] mids = new int[2];

        // 循环不变量：
        // all in [left + 1, lt] < pivot
        // all in [lt + 1, i) = pivot
        // all in [gt, right] > pivot

        int pivot = A[left];  //选基准元素
        int lt = left;
        int gt = right+1;

        int i = left + 1;
        while (i < gt) {
            if (A[i] < pivot) {
                lt++;
                swap(A, i, lt);
                i++;
            } else if (A[i] == pivot) {
                i++;
            } else {
                gt--;
                swap(A, i, gt);
            }
        }

        swap(A, left, lt);  //把最左边的基准元素与j位置的元素（比基准小的元素的最后第一个元素）交换
        mids[0] = lt - 1;
        mids[1] = gt;
        return mids;
    }

}
