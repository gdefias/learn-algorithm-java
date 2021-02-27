package Algorithm.Sorts;

/**
 * Created by Defias on 2016/3/23.
 *
 * 基础排序算法
 */
import static  Lib.Base.*;

public class Sort {
  public static void main(String[] args) {
    int[] A = new int[] {8, 59, 3, 542, 748, 749, 61, 214, 3, 3, 154, 61, 616};

    // selectsort(A);
     insertsort(A);
    // insertsort2(A);
    // bubblesort(A);

    for (int n : A) {
      System.out.println(n);
    }
  }


  // 简单选择排序  时间复杂度：O(n^2)
  public static void selectsort(int[] A) {
    for (int i=0; i<A.length; ++i) {
      int min = i;
      for (int j=i+1; j<A.length; ++j) {
        if (A[j] < A[min])
          min = j;
      }
      if (min != i)
        swap(A, i, min);
    }
  }

  // 递归选择排序
  public static void selectsort2(int[] A) {
    selectsort2(A, 0, A.length-1);
  }

  public static void selectsort2(int[] A, int low, int high) {
    if(low>=high) {
      return;
    }

    int minIndex = low;
    int min = A[minIndex];
    //找出最小值和最小值的位置
    for (int i=low+1; i<=high; i++) {
      if (A[i] < min) {
          min = A[i];
          minIndex = i;
      }
    }

    //交换首元素与最小元素（使首元素是最小的）
    A[minIndex] = A[low];
    A[low] = min;

    //递归排序除首元素之外的元素
    selectsort2(A, low + 1, high);

  }

  //直接插入排序   时间复杂度：O(n^2)
  public static void insertsort(int[] A) {
    for (int i=1; i<A.length; ++i) {
      for (int j=i; j>0; --j) {
        if (A[j] < A[j-1]) {
          swap(A, j, j-1);
        }
      }
    }
  }

  //直接插入排序 另一个版本
  public static void insertsort2(int[] A) {
    for (int i=1; i<A.length; ++i) {
      int key = A[i];
      int j = i-1;
      while (j>=0 && A[j]>key) {
        A[j+1] = A[j];
        j = j-1;
      }
      A[j+1] = key;
    }
  }

  // 冒泡排序   时间复杂度：O(n^2)  稳定排序
  public static void bubblesort(int[] A) {
    boolean flag = true;
    for (int i=A.length-1; i>=1; --i) {
      for (int j=1; j<=i; ++j) {
        if (A[j-1] > A[j]) {
          swap(A, j-1, j);
          flag = false;
        }
      }
      if (flag)
        break;
    }
  }
}
