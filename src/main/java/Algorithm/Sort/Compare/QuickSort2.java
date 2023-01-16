package Algorithm.Sort.Compare;

import java.util.Random;

import static  Lib.Base.*;
/**
 * Created by Defias on 2020/06.
 * Description:
 *
 * 快速排序 - 双指针版本 - 把等于切分元素的所有元素等概率地分到了数组的两侧，避免了递归树倾斜，递归树相对平衡
 *
 * 时间复杂度：O(nlgn) ~ O(n^2)
 * 空间复杂度：O(lgn) ~ O(n)
 *
 * 先整体有序，再局部有序
 * 不稳定排序
 *
 */

public class QuickSort2 {
    private static final Random RANDOM = new Random();

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
        if(left>=right) {
            return;
        }

        int mid = partition(A, left, right);  //切分

        quickSort(A, left, mid-1);   //递归
        quickSort(A, mid+1, right);
    }

    private static int partition(int[] A, int left, int right) {
//        int randomIndex = left + RANDOM.nextInt(right - left + 1);
//        swap(A, randomIndex, left);


        int pivot = A[left];
        int lt = left + 1;
        int gt = right;

        // 循环不变量：
        // all in [left + 1, lt) <= pivot
        // all in (gt, right] >= pivot

        while (true) {
            while (lt<=right && A[lt] < pivot) {
                lt++;
            }

            while (gt>=left-1 && A[gt] > pivot) {
                gt--;
            }

            if (lt >= gt) {
                break;
            }

            //相等的元素通过交换，等概率分到数组的两边
            swap(A, lt, gt);
            lt++;
            gt--;
        }

        swap(A, left, gt);
        return gt;
    }


}
