package Algorithm.DP.Base;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 求n的阶乘
 * User: Defias
 * Date: 2018-10

 */
public class Factorial {

    public int[] facto;

    public static void main(String[] args) {
        Factorial O = new Factorial();
        System.out.println(O.getFactorial2(5));

//        System.out.println(O.getFactorial3(2));
        System.out.println(O.getFactorial3(3));
        System.out.println(O.getFactorial3(4));
        System.out.println(O.getFactorial3(5));

    }

    //方法1：递归
    public int getFactorial(int n) {
        if(n<0) {
            throw new ArithmeticException();
        }
        if(n<=1) {
            return 1;
        }
        return n * getFactorial(n-1);
    }


    //方法2：非递归 - 不存在子问题重叠
    public int getFactorial2(int n) {
        if(n<0) {
            throw new ArithmeticException();
        }
        if(n<=1) {
            return 1;
        }

        int result = 1;
        for(int i=1; i<=n; i++) {
            result = result * i;
        }
        return result;
    }

    //方法3：动态规划备忘录思想，缓存已计算过的阶乘
    public int getFactorial3(int n) {
        if(n<0) {
            throw new ArithmeticException();
        }
        if(n<=1) {
            return 1;
        }

        facto = new int[n+1];
        facto[0] = 1;
        facto[1] = 1;

        return helper3(n);
    }

    public int helper3(int n) {
        if(facto[n] != 0) {
            return facto[n];
        }

        facto[n] = n * helper3(n-1);

        return  facto[n];
    }

}
