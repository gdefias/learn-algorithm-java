package Algorithm.Math;

/**
 * Created with IntelliJ IDEA.
 * Description: 求1+2+…+n
 * User: Defias
 * Date: 2018-10
 *
 求1+2+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句(A?B:C)

 输入: n = 3
 输出: 6
 */
public class SumNums {
    public static void main(String[] args) {
        System.out.println(sumNums(10));
    }

    public static int sumNums(int n) {
        boolean flag = (n > 0) && (n = n + sumNums(n-1)) >0;
        return n;
    }
}
