package Questions.Sorts;

/**
 * Created by Defias on 2020/06.
 * Description:计数排序
 *
 * 时间复杂度：O(n)    （k=O(n)时）
 *           Θ(n+k)  （n表示待排序列的规模，k是待排序列最大值）
 * 稳定排序
 */
import static  Lib.Base.*;

public class CountSort {

    public static void main(String[] args) {
        int[] A = new int[] {0,12,3,4,6,23,3,45,67,89,7};

        int[] B = countSort(A);
        for(int n: B) {
            System.out.println(n);
        }
    }

    public static int[] countSort(int[] A) {
        if(A==null || A.length<2) {
            return A;
        }

        int max = getMaxMin(A)[0];
        int min = getMaxMin(A)[1];
        int[] B = new int[A.length];

        helper(A, B, min, max);

        return B;
    }


    public static void helper(int[] A, int[] B, int min, int max) {
        int[] C = new int[max-min+1];
        //for(int i=0; i<max-min+1; i++) {
        //    C[i] = 0;
        //}

        for (int value: A) {
            C[value-min]++;
        }

        for (int i=1; i<max-min+1; i++) {
            C[i] = C[i-1] + C[i];      //C中记录的是对应元素的最大索引（不包含）
        }

        for(int j=A.length-1; j>=0; j--) {
            B[C[A[j]-min]-1] = A[j];  //注意：B从0开始存，此处需要减1
            C[A[j]-min] = C[A[j]-min] - 1;
        }

    }


}
