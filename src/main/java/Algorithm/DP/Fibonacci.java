package Algorithm.DP;

/**
 * Created by Defias on 2020/06.
 * Description:  斐波拉契数列
 *
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * https://leetcode-cn.com/problems/fibonacci-number/
 *
 * 查找斐波纳契数列中第N个数。斐波纳契数列的前几个数字是：0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 */

public class Fibonacci {
    public static Integer[] cache;

    public static void main(String[] args) {
        System.out.println(Fib0(10));
        System.out.println(Fib1(10));
        System.out.println(Fib2(10));
        System.out.println(Fib3(11));
        System.out.println(Fib4(11));

        System.out.println(FibDP1(10));
        System.out.println(FibDP2(10));
        System.out.println(FibDP3(10));
    }


    //自顶向下的备忘录递归方法
    //时间复杂度：O(n)   空间复杂度：O(n)
    public static int FibDP1(int n) {
        if (n<=1) {
            return n;
        }

        cache = new Integer[n+1];  //cache中存放对应位置值的斐波拉契数
        cache[0] = 0;
        cache[1] = 1;
//        Fibonacci.cache = cache;

        return helper1(n);
    }

    public static int helper1(int n) {
        if(cache[n]!=null) {
            return cache[n];
        }

        cache[n] = helper1(n-1) + helper1(n-2);
        return  cache[n];
    }



    //自底向上的动态规划迭代方法
    //时间复杂度：O(n)   空间复杂度：O(n)
    public static int FibDP2(int n) {
        if (n<=1) {
            return n;
        }

        Integer[] cache = new Integer[n+1];  //cache中存放对应位置值的斐波拉契数
        cache[0] = 0;
        cache[1] = 1;

        for(int i=2; i<=n; ++i) {
            cache[i] = cache[i-1] + cache[i-2];
        }
        return cache[n];
    }



    //自底向上的动态规划迭代方法  空间优化：无需一整个数组，只需要两个变量地址存储前两次计算结果之和即可，无需存储所有先前返回的值
    //时间复杂度：O(n)   空间复杂度：O(1)
    public static int FibDP3(int n) {
        if (n<=1) {
            return n;
        }

        int prevv = 0;  //前前一个值
        int prev = 1;   //前一个值

        int current=0;    //待求的当前值

        for(int i=2; i<=n; ++i) {
            current = prev + prevv;
            prevv = prev;
            prev = current;
        }

        return current;
    }


    //公式法：黄金分割率计算第n个斐波那契数   O(1)
    public static int Fib0(int n) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int)Math.round(Math.pow(goldenRatio, n)/ Math.sqrt(5));
    }

    //普通递归  O(2^n)
    public static int Fib1(int n) {
        if(n<=1)
            return n;

        return Fib1(n-1) + Fib1(n-2);      //---双递归
    }


    //迭代循环 自底向上进行迭代  相当于对FibDP3进行一点优化，将base case也融入到迭代中
    //时间复杂度O(n)   空间复杂度O(1)
    public static int Fib2(int n) {
        int a = 0;
        int b = 1;

        for(int i=0; i<n; i++) {
            int b_ = a + b;
            a = b;
            b = b_;
        }

        return a;
    }

    //尾递归 O(n)
    public static int Fib4(int n) {

        return helper4(0, 1, n);
    }

    public static int helper4(int bbefor, int befor, int n) {
        if (n==0) {
            return bbefor;
        } else {
            return helper4(befor, bbefor+befor, n-1);
        }
    }



    //线性递归（单递归）  O(n)
    public static int Fib3(int n) {
        if(n<=1)
            return n;

        return helper3(1, 1, n);
    }

    public static int helper3(int bbefor, int befor, int n) {
        if (n==2) {
            return 1;
        } else {
            return bbefor + helper3(befor, bbefor+befor, n-1);
        }
    }


}
