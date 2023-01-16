package Algorithm.Math;

/**
 * Created by Defias on 2017/10/7.

 反转整数

 https://leetcode.cn/problems/reverse-integer/

 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 假设环境不允许存储 64 位整数（有符号或无符号）。

 输入：x = 123
 输出：321

 输入：x = -123
 输出：-321

 输入：x = 120
 输出：21

 输入：x = 0
 输出：0
 */
public class NumberReverseN {
    public int res = 0;

    public static void  main(String[] args) {
        int num = 100;
        System.out.println(reverse(num));

    }

    //写法1
    public static int reverse(int n) {
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


    //写法2
    public int reverse2(int x) {
        boolean neg = x < 0;
        if(neg) {
            x = 0 - x;
        }


        while(x > 0) {
            int low = x % 10;
            if(addDigit(low)) {
                return 0;
            }
            x = x / 10;
        }

        if(neg) {
            this.res = 0 - this.res ;
        }

        return this.res;
    }

    public boolean addDigit(int low) {
        int a = this.res;
        this.res = a*10 + low;
        if(this.res%10==low && this.res/10==a) {
            return false;
        }
        return true;
    }
}
