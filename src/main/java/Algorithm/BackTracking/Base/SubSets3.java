package Algorithm.BackTracking.Base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Defias on 2020/07.
 * Description: 字符串子集问题
 *
 * 全组合(所有子集)
 *
 * n个不重复字符的全组合个数: 2^n-1
 * 如123: 1 2 3 12 13 23 123
 */


public class SubSets3 {

    public static void main(String[] args) {
        String S = "123";
        combination(S);
    }

    //方法1：回溯
    public static void combination(String S) {
        char[] A = S.toCharArray();
        List<String> res = new LinkedList<>();
        String path = "";  //存放已遍历到的结果
        int start = 0;

        backtrace(A, start, res, path);

        for(String item: res) {
            System.out.print(item + " ");
        }
    }


    public static void backtrace(char[] A, int start, List<String> res, String path) {
        res.add(new String(path));

        for(int i=start; i<A.length; i++) {
            path = path+A[i];
            System.out.println("递归之前 => " + path);
            backtrace(A, i+1, res, path);
            path = path.substring(0, path.length()-1);
            System.out.println("递归之后 => " + path);
        }
    }




    //方法2：基于位运算
    //假设原有元素n个，最终的组合结果有2^n-1. 可以使用2^n-1个位，1表示取该元素，0表示不取
    //所以取a是001,取ab是011。001,010,011,100,101,110,111对应输出组合结果为：a,b,ab,c,ac,bc,abc
    public static void combination2(String S) {
        char[] A = S.toCharArray();
        if (A == null || A.length == 0) {
            return;
        }
        int len = A.length;

        int k = (1 << len);
        String[] flag = new String[k];

        for (int i = 0; i < k; i++) {
            flag[i] = Integer.toBinaryString(i);
        }

        for (int i = 1; i < k; i++) {
            //System.out.println(flag[i]);
            String fl = flag[i];
            int fllen = fl.length();
            int start = len - fllen;
            StringBuffer item = new StringBuffer();
            for (int j = 0; j < fllen; j++) {
                if (fl.charAt(j) == '0') {
                    continue;
                }
                item.append(A[start + j]);
            }

            System.out.print(item.toString() + " ");
        }

    }

    //位运算
    public static void combination2_(String S){
        char[] s = S.toCharArray();
        if(s.length == 0){
            return;
        }
        int len = s.length;
        int n = 1<<len;
        //从1循环到2^len-1
        for(int i=0;i<n;i++){
            StringBuffer sb = new StringBuffer();
            //查看第一层循环里面的任意一种取值当中的哪一位是1[比如ab,011]， 如果是1，对应的字符就存在，打印当前组合。
            for(int j=0;j<len;j++){
                if( (i & (1<<j)) != 0) // 对应位上为1，则输出对应的字符
                {
                    sb.append(s[j]);
                }
            }

            System.out.print(sb + " ");
        }
    }
    /*
    * j = 0， 1<<j 为将第一位置1（其余位置为0 最右边为第一位）
      j = 1， 1<<j 为将第二位置1
      j = 2， 1<<j 为将第三位置1
    * */
}
