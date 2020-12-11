package Questions.Math;

/**
 * Created with IntelliJ IDEA.
 * Description: 数值的整数次方
 * User: Defias
 * Date: 2018-10
 *
 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。

 输入: 2.00000, 10
 输出: 1024.00000

 输入: 2.00000, -2
 输出: 0.25000
 解释: 2^-2 = 1/2^2 = 1/4 = 0.25

 -100.0 < x < 100.0
 n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 */
public class MyPow {

    public static void main(String[] args) {
        System.out.println(myPow2(1.23, -5));
    }

    //迭代
    public static double myPow(double x, int n) {
        if(x==0) {
            return 0;
        }

        double res = 1.0;

        //Java代码中:
        // int32变量n∈[−2147483648,2147483647]
        // 因此当 n = -2147483648时执行n=−n 会因越界而赋值出错。解决方法是先将n存入long变量b，后面用b操作即可。
        long m = n;

        if(m<0) {
            m = -m;
            x = 1/x;
        }

        while(m>0) {
            if((m&1)==1) {
                res *= x;
            }
            x *= x;
            m >>= 1;  //m/2
        }
        return res;
    }

    //分治 - 递归
    public static double myPow2(double x, int n) {
        if(n==0) {
            if(x==0)
                throw new ArithmeticException();
            return 1;
        }

        if(n<0) {
            n = -n;
            x = 1/x;
        }
        return (n%2==0)? myPow2(x*x, n/2) : myPow2(x*x, n/2)*x;
    }


    //乘法
    public static double myPow3(double base, int exponent) {
        if(Math.abs(base)<0.000001)
            throw new ArithmeticException("base is 0 error!");
        boolean flag = false;

        if(exponent<0) {
            flag = true;
            exponent = -exponent;
        }

        double result = 1.0;
        for(int i=0; i<exponent; i++) {
            result *= base;
        }

        if(flag) {
            result = 1.0 / result;
        }
        return result;
    }
}
