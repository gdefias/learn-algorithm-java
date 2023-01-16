package Algorithm.Math;

/**
 * @author: Defias
 * @date: 2020/12/5
 * @description: 丑数

  https://leetcode.cn/problems/ugly-number/

    丑数 就是只包含质因数 2、3 和 5 的正整数。
    给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。


     输入: 6
     输出: true
     解释: 6 = 2 × 3

     输入: 8
     输出: true
     解释: 8 = 2 × 2 × 2

    输入：n = 1
    输出：true
    解释：1 没有质因数，因此它的全部质因数是 {2, 3, 5} 的空集。习惯上将其视作第一个丑数。

    输入：n = 14
    输出：false
    解释：14 不是丑数，因为它包含了另外一个质因数 7 。

    输入不会超过 32 位有符号整数的范围: [−2^31,  2^31 − 1]
 */
public class UglyNumber1 {
    public static void main(String[] args) {
        System.out.println(isUglyNumber(8));
    }

    //数学
    public static boolean isUglyNumber(int num) {
        if(num<=0) {
            return false;
        }

        while(num%2==0) {
            num = num / 2;
        }

        while(num%3==0) {
            num = num / 3;
        }

        while(num%5==0) {
            num = num / 5;
        }

        return num==1;
    }

}
