package Algorithm.Math;

/**
 * Created by Defias on 2017/10/7.
 *
 * 反转一个只有3位数的整数
 */
public class NumberReverse3bit {
    public static void  main(String[] args) {
        //int[] nums = {1,2,334,5,62,23,12,332,445,346,678,42,345,235};
        //int second = secondMax(nums);
        //System.out.println(second);

        //int num = 1;
        //System.out.println(reverseInteger(num));
        //System.out.println(isPalindrome(num));

    }

    public static int reverse3bitInteger(int number) {
        int g,s,b;
        g = number % 10;
        s = number / 10 % 10;
        b = number / 100;
        return g*100+s*10+b;
    }

}
