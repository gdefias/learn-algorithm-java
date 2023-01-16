package Algorithm.Math;
/**
 * Created by Defias on 2017/9/15.
 *
 * 字符串转整数
 */

import static Lib.Object.*;

public class Atoi {

    public static void main(String[] args) {
        String str = "-671234567";
        System.out.println(atoi(str));
    }

    //字符串转整数
    public static int atoi(String str) {
        int start = 0;
        boolean negative = false;

        //判断正负
        if (str.charAt(0) == '-') {
            negative = true;
            start++;
        } else if (str.charAt(0) == '+') {
            start++;
        }

        int number = 0;  //基
        for (int i=start; i<str.length(); ++i) {
            char ch = str.charAt(i);

            //有效性检查
            if (!Character.isDigit(ch)) {
                return negative ? 0-number : number;
            }

            //越界检查
            int digit = ch - '0';
            if (!negative && checkOverflow(number, digit)) {
                return Integer.MAX_VALUE;
            }
            if (negative && checkNegativeOverflow(number, digit)) {
                return Integer.MIN_VALUE;
            }

            //转换
            number = number*10 + digit;
        }

        return negative ? 0-number : number;  //恢复正负
    }

    //检查数字位digit加到number上后的正整数是否越界 （number和digit都为正数）
    public static boolean checkOverflow(int number, int digit) {
        int newNumber = number * 10 + digit;
        return !(newNumber % 10 == digit && newNumber / 10 == number);
    }


    //检查数字位digit加到number上后的负整数是否越界（number和digit都为正数）
    public static boolean checkNegativeOverflow(int number, int digit) {
        if (-number * 10 - digit == Integer.MIN_VALUE) {   //why?   becouse:  -2^31 ~ 2^31-1
            return false;
        }
        return checkOverflow(number, digit);
    }

}
