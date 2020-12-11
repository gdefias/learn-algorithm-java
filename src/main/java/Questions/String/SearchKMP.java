package Questions.String;

/**
 * @author: Defias
 * @date: 2020/12/8
 * @description:  KMP算法
 */
public class SearchKMP {

    public static void main(String[] args){
        //String s = "CDFGFABABAFABABAAAQWEDC";
        //String t = "ABABAA";
        //int[] next = kmpNext(t);
        //int res = kmp(s, t, next);
        //if (res!=-1){
        //    System.out.println("起始位置为："+res);
        //}
        //else System.out.println("主串中不包含字符串："+t);
        //printNext("ABCDABD");
        //printNext("ABABAA");
        //printNext("ABAABCAC");

        printNext("AABAADABBC");
    }

    //打印next[]数组
    public static void printNext(String s){
        System.out.println("********************");
        int[] nextI = kmpNext(s);
        System.out.print("模式串：'"+s+"'的next[]数组为：[");
        for(int i = 0; i < (nextI.length); i++){
            System.out.print(nextI[i]+" ");
        }
        System.out.println("]");
        System.out.println("模式串长度为："+nextI.length);
    }



    public static int[] kmpNext(String str){
        int[] next = new int[str.length()];
        next[0] = 0;
        for(int j = 1,k = 0; j < str.length(); j++){
            while(k > 0 && str.charAt(k) != str.charAt(j)){
                k = next[k - 1];
            }
            if(str.charAt(j) == str.charAt(k)){
                k++;
            }
            next[j] = k;
        }
        return next;
    }

    public static int kmp(String s, String t, int[] next){//s主串  t模式串
        for(int i = 0, j = 0; i < s.length(); i++){

            while(j > 0 && s.charAt(i) != t.charAt(j)){
                j = next[j - 1];    //下一个匹配位为next数组的第j-1位
            }
            if(s.charAt(i) == t.charAt(j)){
                j++;//主串通过i进行加1，模式串通过j加1
            }
            if(j == t.length()){
                return i-j+1;//返回匹配位置
            }
        }
        return -1;
    }




}
