package Algorithm.Recursion;

/**
 * Created with IntelliJ IDEA.
 * Description: 求n的阶乘
 * User: Defias
 * Date: 2018-10

 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println(getFactorial2(5));
    }

    //非递归
    public static int getFactorial(int n) {
        if(n<0)
            throw new ArithmeticException();
        if(n==0)
            return 1;
        int result = 1;
        for(int i=1; i<=n; i++) {
            result = result * i;
        }
        return result;
    }

    //递归
    public static int getFactorial2(int n) {
        if(n==1) {
            return 1;
        }

        return n * getFactorial2(n-1);
    }


}
