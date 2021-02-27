package Algorithm.String;
/**
 * Created by Defias on 2017/9/3.
 *
 * 字符串排序 - 三向字符串快速排序
 */


public class StringSortQuick3string {

    public static void main(String[] args) {
        String[] A = { "2I",  "4PF", "20"};
        Quick3StringSort(A);

        for(String a: A) {
            System.out.println(a);
        }
    }

    //三向字符串快速排序
    public static void Quick3StringSort(String[] A) {
        Quick3StringSort(A, 0, A.length-1, 0);
    }


    public static void Quick3StringSort(String[] A, int left, int right, int d) {
        if(left>=right) {
            return;
        }

        int lt = left, gt = right;
        int v = charAt(A[left], d);  //首字符作为基准切分字符
        int i = left+1;

        //三向切分
        while(i<=gt) {
            int t = charAt(A[i], d);
            if(t<v) {  //比基准下就移到基准的左边（基准不变）
                InsertionSort.exch(A, lt++, i++);
            } else if (t>v) {   //比基准大就与最右边的字符串进行交换
                InsertionSort.exch(A, i, gt--);
            } else {
                i++;
            }
        }

        //将三向切分得到的的三个子数组进行排序
        Quick3StringSort(A, left, lt-1, d);   //首字母小于切分字符的字符串子数组
        if(v>=0) {
            Quick3StringSort(A, lt, gt, d+1);  //首字母等于切分字符的字符串子数组
        }
        Quick3StringSort(A, gt+1, right, d);  //首字母大于切分字符的字符串子数组
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
}


