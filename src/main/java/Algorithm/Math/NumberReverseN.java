package Algorithm.Math;

/**
 * Created by Defias on 2017/10/7.
 *
 *
 * 反转整数
 * Reverse Integer： Reverse digits of an integer. Returns 0 when the reversed integer overflows (signed 32-bit integer)
 */
public class NumberReverseN {
    public static void  main(String[] args) {
        int num = 100;
        System.out.println(reverseInteger(num));

    }

    public static int reverseInteger(int n) {
        int reversed_n = 0;
        while (n != 0) {
            int temp = reversed_n * 10 + n % 10;
            n = n / 10;

            //防止数太大而发生溢出
            if (temp / 10 != reversed_n) {
                reversed_n = 0;
                break;
            }
            reversed_n = temp;
        }
        return reversed_n;
    }
}
