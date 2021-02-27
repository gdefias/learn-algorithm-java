package Algorithm.Math;
/**
 * Created by Defias on 2016/3/2.
 */

public class Base {

    public static void  main(String[] args) {
//        int result = reverse3bitNum(123);
//        System.out.print(result + "\n");
//        exchange2Num();
//        char a = '%';
//        result = charToInteger(a);
//        System.out.print(result + "\n");

        int num = 1234543211;
        System.out.println(isPalindrome(num));
    }

    //reverse 3-digit integer
    public static int reverse3bitNum(int Num) {
        int g,s,b;
        g = Num % 10;
        s = Num / 10 % 10;
        b = Num / 100;
        return g*100+s*10+b;
    }

    //exchange two integers
    public static void exchange2Num() {
        int a = 100;
        int b = 123;
        System.out.println("a = " + a + ", b = " + b);
        int tmp = a;
        a = b;
        b = tmp;
        System.out.println("a = " + a + ", b = " + b);
    }

    //将字符转换为一个整数
    public static int charToInteger(char character) {
        return (int)character;
    }

    //判断一个正整数是不是回文数 - 二逼方法
    //转为字符串然后使用双指针
    public static boolean palindromeNumber(int num) {
        // Write your code here
        String strnum = num + "";
        int i = 0;
        int j = strnum.length()-1;
        while (i<=j) {
            if(strnum.charAt(i) != strnum.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //判断一个正整数是不是回文数 - 文青
    //与另一半倒过来进行比较
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
