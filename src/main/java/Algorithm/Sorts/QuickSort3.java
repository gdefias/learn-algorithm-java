package Algorithm.Sorts;

import java.util.Random;
import static  Lib.Base.*;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-10
 *
 * 快速排序  另一个版本
 *
 */
public class QuickSort3 {
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

        //int provitw = partition(A, left, right);  //切分
        int provitw = random_partition(A, left, right);


        helper(A, left, provitw-1);
        helper(A, provitw+1, right);
    }


    //版本3
    public static int partition(int[] A, int left, int right) {
        int provit = A[left];  //选基准元素
        int i = left;
        int j = right+1;

        while (true) {
            while (A[++i] < provit) {
                if (i==right)
                    break;
            }
            while (A[--j] >= provit) {
                if (j==left)
                    break;
            }
            if (i>=j)
                break;
            swap(A, i, j);
        }

        swap(A, left, j);  //把最左边的基准元素与j位置的元素（比基准小的元素的最后第一个元素）交换
        return j;
    }



    //版本4
    public static int partition2(int[] A, int left, int right) {
        int provit = A[right];  //选基准元素
        int i = left-1;  //i: 比基准小的元素的右边界(同时也就确定了比基准大的元素的左边界)

        for (int j=left; j<=right-1; j++) {  //j: 比基准大的元素的右边界
            if (A[j]<=provit) {
                i = i+1;
                swap(A, i, j);
            }
        }
        swap(A, i+1, right);  //把最右边的基准元素与i+1位置的元素（比基准大的元素的开头第一个元素）交换
        return i+1;
    }



    //随机化
    public static int random_partition(int[] A, int left, int right) {
        int proviti = (new Random()).nextInt(right-left+1)+left;  //随机选基准元素
        swap(A, proviti, right);

        return partition(A, left, right);
        //return partition2(A, left, right);
    }

}
