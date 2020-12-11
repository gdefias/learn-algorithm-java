package Questions.Math;

/**
 * Created by Defias on 2020/07.
 * Description: 数字序列中某一位的数字
 *
 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 请写一个函数，求任意第n位对应的数字。

 输入：n = 3
 输出：3

 输入：n = 11
 输出：0

 0 <= n < 2^31
 */


public class FindNthDigit {
    public int findNthDigit(int n) {
        long start = 1;
        int digit = 1;
        long count = 9;

        while(n > count) {
            n -= count;
            start *= 10;
            digit++;
            count = start * digit * 9;
        }

        long num = start + ((n-1)/digit);

        return  Long.toString(num).charAt((n - 1)%digit) - '0';
    }
}
