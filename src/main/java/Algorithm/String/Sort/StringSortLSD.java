package Algorithm.String.Sort;
/**
 * Created by Defias on 2020/06.
 * Description: 字符串排序 -  低位优先LSD
 */

public class StringSortLSD {

    public static void main(String[] args) {
        String[] A = { "2A",  "1B", "3C"};
        LSBSort(A, 2);

        for(String a: A) {
            System.out.println(a);
        }
    }


    //低位优先的字符串排序  只适用于A中字符串等宽W
    //时间复杂度与输入规模（字符总数W*N）成正比
    public static void LSBSort(String[] A, int W) {
        int N = A.length;
        int R = 256;    //字符串含有的字符的ASCII码不超过256

        String[] aux = new String[N];

        for (int i=W-1; i>=0; i--) {  //从低位开始依次遍历字符串的各位

            int[] count = new int[R+1];  //默认初始值为0

            for (int j=0; j<N; j++) {  //统计所有字符串某一位的频率
                count[A[j].charAt(i)+1]++;
            }

            //计数排序思想
            for (int r=0; r<R; r++) {  //将频率转换为索引
                count[r+1] += count[r];
            }

            for (int k=0; k<N; k++) {   //按索引放入辅助空间 相同索引的相邻
//                System.out.println(count[A[k].charAt(i)]);
                aux[count[A[k].charAt(i)]++] = A[k];
//                System.out.println(count[A[k].charAt(i)]);
//                System.out.println("---");
            }

            for (int k=0; k<N; k++) {  //将辅助空间中的字符串会写到原数组中 以便循环进行下一位的排序
                A[k] = aux[k];
            }
        }
    }
}
