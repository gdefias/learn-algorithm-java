package Algorithm.Math;

/**
 * Created by Defias on 2017/10/7.

 回文数

 https://leetcode.cn/problems/palindrome-number/

 判断一个正整数是不是回文数。回文数的定义是，将这个数反转之后，得到的数仍然是同一个数

 输入：x = 121
 输出：true

 输入：x = -121
 输出：false

 */
public class NumberPalindrome {

    public static void  main(String[] args) {
        int num = 1234543211;
        System.out.println(isPalindrome(num));
    }

    //方法1：转为字符串然后使用双指针
    public static boolean isPalindrome(int num) {
        String strnum = num + "";
        int i = 0;
        int j = strnum.length()-1;
        while (i<=j) {
            if(strnum.charAt(i) != strnum.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //类似双指针
    public static boolean isPalindrome_(int num) {
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


    //方法2：与另一半倒过来进行比较
    public static boolean isPalindrome2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
