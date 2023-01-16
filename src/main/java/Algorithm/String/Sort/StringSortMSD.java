package Algorithm.String.Sort;

import Algorithm.String.Sort.InsertionSort;

/**
 * Created by Defias on 2020/06.
 * Description:  字符串排序 -  高位优先MSD
 */


public class StringSortMSD {
    private static int R = 256;   //基数
    private static final int M = 1;  //小数组切换到其他排序方式的阀值

    public static void main(String[] args) {
        String[] A = { "2I",  "4PSSSF", "220"};
        MSDSort(A);
        for(String a: A) {
            System.out.println(a);
        }
    }

    //高位优先的字符串排序
    public static void MSDSort(String[] A) {
        int N = A.length;
        String[] aux = new String[N]; //辅助空间
        MSDSort(A, 0, N-1, 0, aux);
    }

    //重写根据索引取值
    private static int charAt(String A, int d) {
        assert d >= 0 && d <= A.length();
        if(d<A.length()) {
            return A.charAt(d);
        } else {
            return -1;   //索引超出时返回-1
        }
    }

    public static void MSDSort(String[] A, int left, int right, int d, String[] aux) {
        //以第d个字符为键将A[left]至A[right]排序

        if((left+M)>=right) {   //小数组切换到其他排序
            InsertionSort.sort(A, left, right, d);
            return;
        }

        int[] count = new int[R+2];   //计算频率
        for(int i=left; i<=right; i++) {
            count[charAt(A[i], d)+2]++;   //什么情况下索引会超出范围呢？  递归里面
        }

        for(int r=0; r<R+1; r++) {   //将频率转为索引
            count[r+1] += count[r];
        }

        for(int i=left; i<=right; i++) {  //数据分类
            aux[count[charAt(A[i], d)+1]++] = A[i];
        }

        for(int i=left; i<=right; i++) {  //回写
            A[i] = aux[i-left];
        }

        //递归以每个字符为键进行排序
        for(int r=0; r<R; r++) {
            MSDSort(A, left+count[r], left+count[r+1]-1, d+1, aux);
        }
    }

}
