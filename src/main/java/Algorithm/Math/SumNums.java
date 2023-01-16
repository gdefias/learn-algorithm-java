package Algorithm.Math;

/**
 * Created with IntelliJ IDEA.
 * Description:  求1+2+…+n
 * User: Defias
 * Date: 2018-10

 https://leetcode.cn/problems/qiu-12n-lcof/

 求1+2+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句(A?B:C)

 输入: n = 3
 输出: 6
 */
public class SumNums {
    public static void main(String[] args) {
        System.out.println(sumNums_(10));
    }

    //方法1：递归   基于A&&B
    //思路：可以将判断是否为递归的出口看作 A && B 表达式中的 A 部分，递归的主体函数看作 B 部分。如果不是递归出口，则返回True，
    //并继续执行表达式 B 的部分，否则递归结束
    public static int sumNums(int n) {
        boolean flag = (n > 0) && (n = n + sumNums(n-1)) >0;
        return n;
    }

    //递归  基于A||B
    public static int sumNums_(int n) {
        boolean flag = (n <= 0) || (n = n + sumNums_(n-1)) >0;
        return n;
    }
}
