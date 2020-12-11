package Questions.Sorts;

/**
 * Created by Defias on 2017/9/16.
 *
 * 数组排序
 */

public class SortArray {
    public static void main(String[] args) {
        int[] A = {6, 4, 5, 7, 2, 4, 3, 4, 7, 8};
        selectionSort(A);
        //insertionSort(A);
        //bubbleSort(A);
        //QuickSort(A);
        //mergeSort(A);
        SortObject.printArray(A);
    }

    //选择排序
    private static void selectionSort(int[] A) {
        for (int i = 0; i < A.length-1; ++i) {
            int minIndex = i;
            for (int j = i; j < A.length; ++j) {
                if (A[j] < A[minIndex]) {
                    minIndex = j;
                }
            }
            swap(A, minIndex, i);
        }
    }

    //插入排序
    private static void insertionSort(int[] A) {
        for (int i = 1; i < A.length; ++i) {
            int j = i;
            while (j > 0 && A[j - 1] > A[j]) {
                swap(A, j - 1, j);
                j--;
            }
        }
    }

    //冒泡排序
    private static void bubbleSort(int[] A) {
        // 这里写的是没有优化的版本
        for (int i = 0; i < A.length-1; ++i) {
            for (int j = 0; j < A.length-1-i; ++j) {
                if (A[j] > A[j+1]) {
                    swap(A, j, j+1);
                }
            }
        }
    }

    //交换
    private static void swap(int[] A, int i, int j) {
        int t = A[j];
        A[j] = A[i];
        A[i] = t;
    }

    //快速排序
    private static void quickSort(int[] A) {
        int left = 0;
        int right = A.length-1;
        quickSort(A, left, right);
    }


    private static void quickSort(int[] A, int left, int right) {
        if (left >= right) {
            return;
        }

        //partition
        int pivot = A[left + (right-left) / 2];
        int i = left, j = right;
        while (i<=j) {
            while (i<=j && A[i]<pivot) {
                i++;
            }
            while (i<=j && A[j]>pivot) {
                j--;
            }

            // 左大右小
            if (i <= j) {
                swap(A, i, j);
                i++;
                j--;
            }
        }
        quickSort(A, left, j);
        quickSort(A, i, right);
    }

       //3 1 1 2 (pivot: 2)
       //i     j
       //
       //2 1 1 3
       //  i j
       //
       //2 1 1 3
       //    j i
       //
       //
       //3 4 2 2 1 (pivot: 2)
       //i       j
       //
       //1 4 2 2 3
       //  i   j
       //
       //1 2 2 4 3
       //    i
       //    j
       //
       //1 2 | 2 | 3 4
       //  j       i

    //归并排序
    public static void mergeSort(int[] A) {
        int left = 0;
        int right = A.length-1;
        int[] temp = new int[A.length];

        mergeSort(A, temp, left, right);
    }

    public static void mergeSort(int[] A, int[] temp, int left, int right) {
        if (left>=right) {
            return;
        }

        int middle = left + (right-left)/2;
        mergeSort(A, temp, left, middle);
        mergeSort(A, temp, middle + 1, right);
        merge(A, temp, left, middle, right);
    }

    private static void merge(int[] A, int[] temp, int left, int middle, int right) {
        int i = left, j = middle + 1;
        for (int k=0; k<right-left+1; ++k) {
            if (i<=middle && (j>right || A[i]<=A[j])) {
                temp[k] = A[i];
                i++;
            } else {
                temp[k] = A[j];
                j++;
            }
        }

        for (int k = 0; k < right - left + 1; ++k) {
            A[left + k] = temp[k];
        }
    }


}
