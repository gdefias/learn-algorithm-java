package Algorithm.Array;

/**
 * Created by Jeff on 2016/3/7.
 *
 * 整数反转
 *
 * https://leetcode-cn.com/problems/reverse-integer/
 */

public class Reverse {
    public static int res = 0;

    public static void main(String[] args) {

        int reversedNumber = reverse2(-211);
        System.out.println(reversedNumber);

    }

    //方法1 从低位到高位逐渐构建反转数
    public static int reverse1(int number) {

        //整数反转reverse
        //int number = 100;
        boolean negative = number < 0;
        if (negative) {
            number = 0 - number;
        }

        int reversedNumber = 0;
        boolean firstw = true;  //首位是否为0
        while (number>0) {
            int digit = number % 10;
            reversedNumber = addDigit(reversedNumber, digit);
            if(reversedNumber > 0) {
                firstw = false;
            }
            if(reversedNumber==0 && !firstw) {  //需要判断是本身首位的0还是超限了
                return 0;
            }
            number /= 10;
        }

        if (negative) {
            reversedNumber = 0 - reversedNumber;
        }
        return reversedNumber;

    }

    //构建数  返回构建的数，超限返回0
    public static int addDigit(int num, int digit) {
        int a = num;
        num = num * 10 + digit;
        if((num%10==digit) && (num/10 == a)) {
            return num;
        }
        return 0;
    }


    //方法1 另一种实现方式
    public static int reverse_(int x) {
        boolean neg = x < 0;
        if(neg) {
            x = 0 - x;
        }

        while(x > 0) {
            int low = x % 10;
            if(addDigit_(low)) {
                return 0;
            }
            x = x / 10;
        }

        if(neg) {
            res = 0 - res ;
        }

        return res;
    }

    //返回是否超限
    public static boolean addDigit_(int low) {
        int a = res;
        res = a*10 + low;
        if(res%10==low && res/10==a) {
            return false;
        }
        return true;
    }



    //方法2：借助long类型
    public static int reverse2(int number) {
//        System.out.println(-1/10);  //0
//        System.out.println(-11%10);  //-1

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
