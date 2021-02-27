package Algorithm.Math;

/**
 * Created by Defias on 2017/10/7.
 *
 * 回文数
 * 判断一个正整数是不是回文数。回文数的定义是，将这个数反转之后，得到的数仍然是同一个数
 */
public class NumberPalindrome {

    public static void  main(String[] args) {
        int num = 1;
        System.out.println(isPalindrome(num));

    }

    public static boolean isPalindrome(int num) {
        String strnum = num + "";
        char befor, after;
        int i;
        int len = strnum.length();
        for(i=0; i<len/2; i++) {
            befor = strnum.charAt(i);
            after = strnum.charAt(len-i-1);
            if(befor != after)
                return false;
        }
        return true;
    }

    //方法2
    public static boolean isPalindrome2(int x) {
        if(x < 0) {
            return false;
        }
        return x == reverse(x);
    }

    public static int reverse(int x) {     //反转整数  没有考虑整数溢出
        int rst = 0;
        while(x != 0) {
            rst = rst*10 + x%10;
            x = x/10;
        }
        return rst;
    }
}
