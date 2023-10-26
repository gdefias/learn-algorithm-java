package Algorithm.DP.Base;

/**
 * Created by Defias on 2020/06.
 * Description:  斐波拉契数列

  https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
  https://leetcode-cn.com/problems/fibonacci-number/

  查找斐波纳契数列中第N个数。斐波纳契数列的前几个数字是：0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...

   F(0) = 0,   F(1) = 1
   F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 */

public class Fibonacci {
    public static Integer[] cache;

    public static void main(String[] args) {
        System.out.println(Fib(10));
        System.out.println(Fib2(10));
        System.out.println(Fib3(10));
    }


    //方法1：DP 自顶向下 递归 备忘录  （DP = 递归 + 备忘录）
    //时间复杂度：O(n)   空间复杂度：O(n)
    public static int Fib(int n) {
        if (n<=1) {
            return n;
        }

        cache = new Integer[n+1];  //cache中存放对应位置值的斐波拉契数
        cache[0] = 0;
        cache[1] = 1;
//        Fibonacci.cache = cache;

        return helper(n);
    }

    public static int helper(int n) {
        if(cache[n]!=null) {
            return cache[n];
        }

        cache[n] = helper(n-1) + helper(n-2);
        return  cache[n];
    }



    //方法2：DP 自底向上 备忘录  迭代
    //时间复杂度：O(n)   空间复杂度：O(n)
    public static int Fib2(int n) {
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



    //方法3：DP 自底向上 备忘录优化  迭代
    //空间优化：备忘录无需使用数组存储所有先前返回的值，只需要两个变量地址存储前两次计算结果之和即可
    //时间复杂度：O(n)   空间复杂度：O(1)
    public static int Fib3(int n) {
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

    //相当于对FibDP3进行一点优化，将base case也融入到迭代中
    public static int Fib3_(int n) {
        int current = 0;
        int after = 1;

        for(int i=0; i<n; i++) {
            int newa = current + after;
            current = after;
            after = newa;
        }

        return current;
    }


    //方法4：递归 普通递归
    //时间复杂度：O(2^n)
    public static int Fib4(int n) {
        if(n<=1)
            return n;

        return Fib4(n-1) + Fib4(n-2);      //---双递归
    }


    //方法5：递归 尾递归 线性递归 O(n)
    public static int Fib5(int n) {

        return helper5(0, 1, n);
    }

    public static int helper5(int prevv, int prev, int n) {
        if (n==0) {
            return prevv;
        } else {
            return helper5(prev, prevv+prev, n-1);
        }
    }


    //方法6： 公式法 黄金分割率计算第n个斐波那契数   O(1)
    public static int Fib6(int n) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int)Math.round(Math.pow(goldenRatio, n)/ Math.sqrt(5));
    }
}
