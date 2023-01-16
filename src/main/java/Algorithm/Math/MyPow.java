package Algorithm.Math;

/**
 * Created with IntelliJ IDEA.
 * Description: 数值的整数次方 /  Pow(x, n)
 * User: Defias
 * Date: 2018-10

 https://leetcode.cn/problems/powx-n/
 https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/

 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。

 输入: 2.00000, 10
 输出: 1024.00000

 输入: 2.00000, -2
 输出: 0.25000
 解释: 2^-2 = 1/2^2 = 1/4 = 0.25

 提示：
 -100.0 < x < 100.0
 -231 <= n <= 231-1
 n 是一个整数
 -104 <= xn <= 104
 */
public class MyPow {

    public static void main(String[] args) {
        MyPow O = new MyPow();
        System.out.println(O.myPow(1.23, -5));
    }

    //方法1：快速幂 + 递归 分治思想
    //当要计算x^n时，可以先递归地计算出 y=x^⌊n/2⌋
    //根据递归计算的结果，如果n为偶数，那么x^n=y^2   如果n为奇数，那么x^n=y^2 * x；
    //时间复杂度：O(logn)，即为递归的层数
    //空间复杂度：O(logn)，即为递归的层数。这是由于递归的函数调用会使用栈空间
    public double myPow(double x, int n) {
        // Java代码中:
        // int32变量n∈[−2147483648,2147483647]
        // 因此当n=-2147483648时执行n=−n会因越界而赋值出错。解决方法是先将n存入long变量N，后面用N操作即可
        long N = n;

        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N/2);
        return N % 2 == 0 ? y * y : y * y * x;
    }


    //方法2：快速幂 + 迭代 分治思想
    //时间复杂度：O(logn)
    //空间复杂度：O(1)
    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul2(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul2(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;

        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }


    //方法3：暴力法 乘法  leecode超时
    public static double myPow3(double x, int n) {
        boolean flag = false;

        if(n<0) {
            flag = true;
            n = -n;
        }

        double result = 1.0;
        for(int i=0; i<n; i++) {
            result *= x;
        }

        if(flag) {
            result = 1.0 / result;
        }
        return result;
    }
}
