package Algorithm.Math;

/**
 * Created by Defias on 2020/07.
 * Description: 数字序列中某一位的数字 / 第 N 位数字

 https://leetcode.cn/problems/nth-digit/
 https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/

 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。

 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 请写一个函数，求任意第n位对应的数字。

 输入：n = 3
 输出：3

 输入：n = 11
 输出：0

 0 <= n < 2^31
 */

public class FindNthDigit {
    public static void main(String[] args) {
        FindNthDigit O = new FindNthDigit();

        System.out.println(O.findNthDigit(13));

    }

    //直接计算
    //最小的x位数是：10^(x-1)  x位数的个数是：9 * 10^(x-1)  所有x位数的位数之和是：x * 9 * 10^(x-1)
    public int findNthDigit(int n) {
        long start = 1;  //最小的digit位数
        int digit = 1; //digit位数 从1开始
        long count = 9;  //digit位数的个数

        while(n > count) {
            n -= count;

            digit++;
            start *= 10;   //最小的digit位数

            count = digit * 9 * start;  //digit位数的个数
        }

        long num = start + ((n-1)/digit);   //第n位数所在的数字

        return  Long.toString(num).charAt((n-1)%digit) - '0';
    }



    //模拟  leecode内存超限
    public int findNthDigit2(int n) {
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=n; i++) {
            sb.append(Integer.toString(i));
        }

        String ans = String.valueOf(sb.charAt(n-1));
        return Integer.valueOf(ans) ;
    }

}
