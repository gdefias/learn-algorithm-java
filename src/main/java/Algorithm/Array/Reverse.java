package Algorithm.Array;

/**
 * Created by Jeff on 2016/3/7.
 *
 * 反转
 *
 * https://leetcode-cn.com/problems/reverse-integer/
 */

public class Reverse {
    public static void main(String[] args) {

        int reversedNumber = reverse3(1020);
        System.out.println(reversedNumber);

    }

    //方法1
    public static int reverse1(int number) {

        //整数反转reverse
        //int number = 100;
        boolean negative = number < 0;
        if (negative) {
            number = 0 - number;
        }

        int reversedNumber = 0;
        boolean firstw = true;
        while (number>0) {
            int digit = number % 10;
            reversedNumber = addDigit(reversedNumber, digit);
            if(reversedNumber > 0) {
                firstw = false;
            }
            if(reversedNumber==0 && !firstw) {
                return 0;
            }
            number /= 10;
        }

        if (negative) {
            reversedNumber = 0 - reversedNumber;
        }
        return reversedNumber;

    }

    public static int addDigit(int num, int digit) {
        int a = num;
        num = num * 10 + digit;
        if((num%10==digit) && (num/10 == a)) {
            return num;
        }
        return 0;
    }

    //方法2：借助long类型
    public static int reverse2(int number) {
        long n = 0;
        while(number != 0) {
            n = n*10 + number%10;
            number = number/10;
        }
        return (int)n==n?(int)n:0;
    }

    //方法3：转字符串
    public static int reverse3(int x) {
        String xString = Integer.toString(x);
        String string = xString;
        int flag = 1;
        if (x < 0) {
            flag = -1;
            string = xString.substring(1);
        }
        try {
            return Integer.valueOf((new StringBuilder(string)).reverse().toString()) * flag;
        }catch (Exception e){
            return 0;
        }
    }
}
