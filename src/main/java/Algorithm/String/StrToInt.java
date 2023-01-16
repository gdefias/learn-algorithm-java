package Algorithm.String;
/**
 * Created with IntelliJ IDEA.
 * Description: 把字符串转换成整数
 * User: Defias
 * Date: 2018-10

   https://leetcode.cn/problems/string-to-integer-atoi/

 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，
 则直接将其与之后连续的数字字符组合起来，形成整数。

 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

 在任何情况下，若函数不能进行有效的转换时，请返回 0。

 说明：
 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为[−231, 231− 1]。
 如果数值超过这个范围，请返回 INT_MAX (231− 1) 或INT_MIN (−231) 。
 */
public class StrToInt {
    public static void main(String[] args) {

    }

    public int strToInt(String str) {
        if(str==null) {
            return 0;
        }

        str = str.trim();
        if(str.length()==0) {
            return 0;
        }

        boolean flag = false;  //负数标识
        int num = 0; //转换成的数字
        int p = 0;  //处理指针

        char first = str.charAt(p);
        if(first=='-'||first=='+') {
            if(first=='-') {
                flag = true;
            }
            p++;
        } else if(!isNum(first)) {
            return 0;
        }

        while(p<str.length() && isNum(str.charAt(p))) {
            int digit = toNum(str.charAt(p));

            if(checkOverflow(flag, num, digit)) {
                if(flag) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
            num = num * 10 + digit;
            p++;
        }

        return flag?(0-num):num;
    }

    public boolean checkOverflow(boolean flag, int num, int digit) {
        if(flag) {
            if(-num * 10 - digit == Integer.MIN_VALUE) {
                return false;
            }
        }
        int newnum = num * 10 + digit;
        return !(newnum % 10 == digit && newnum / 10 == num);
    }

    public boolean isNum(char c) {
        return ('0'<=c && '9'>=c);
    }

    public int toNum(char c) {
        return (c-'0');
    }
}
