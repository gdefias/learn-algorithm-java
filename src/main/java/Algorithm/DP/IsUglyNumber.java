package Algorithm.DP;

/**
 * @author: Defias
 * @date: 2020/12/5
 * @description: 丑数

 编写一个程序判断给定的数是否为丑数

 丑数就是只包含质因数 2, 3, 5 的正整数


 输入: 6
 输出: true
 解释: 6 = 2 × 3

 输入: 8
 输出: true
 解释: 8 = 2 × 2 × 2

1 是丑数
输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]
 */
public class IsUglyNumber {
    public static void main(String[] args) {
        System.out.println(isUglyNumber(8));
    }

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
