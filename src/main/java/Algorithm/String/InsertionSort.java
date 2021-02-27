package Algorithm.String;
/**
 * Created by Defias on 2017/10/7.
 *
 * 字符串数组插入排序
 */

public class InsertionSort {
    public static void sort(String[] A, int left, int right, int d) {
        for(int i=left; i<right; i++) {
            for(int j=i; j>left; j--) {
                if(less(A[j], A[j-1], d)) {
                    exch(A, j, j-1);
                }
            }
        }
    }

    public static boolean less(String i, String j, int beginIndex) {
        return i.substring(beginIndex).compareTo(j.substring(beginIndex)) < 0;
    }

    public static void exch(String[] A, int a, int b) {
        String tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }
}
