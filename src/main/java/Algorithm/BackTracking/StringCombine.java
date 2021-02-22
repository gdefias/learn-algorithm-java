package Questions.BackTracking;

/**
 * Created by Defias on 2020/07.
 * Description: 字符串的全组合
 *
 * 全组合(所有子集)
 *
 * n个不重复字符的全组合个数: 2^n-1
 * 如123: 1 2 3 12 13 23 123
 */


public class StringCombine {

    public static void main(String[] args) {
        String S = "abcd";
        combination(S);
    }

    public static void combination(String S) {
        char[] A = S.toCharArray();
        combination(A);
    }


    //基于位图的解法: 假设原有元素n个，最终的组合结果有2^n-1. 可以使用2^n-1个位，1表示取该元素，0表示不取
    //所以a表示001,取ab是011。001,010,011,100,101,110,111对应输出组合结果为：a,b,ab,c,ac,bc,abc
    public static void combination(char[] A) {
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

    public static void Combination(char [] s){
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
