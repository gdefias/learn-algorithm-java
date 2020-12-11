package Questions.Math;

/**
 * Created by Defias on 2017/9/15.
 *
 * 字符串转整数
 */

import static Lib.Object.*;

public class Atoi {

    public static void main(String[] args) {
        //testStudent();
        //testString();
        //testToUpperCase();
        //testswap();
        //testatoi0();
        testatoi();

        Atoi O = new Atoi();
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
    }



    public static void testIntegrerOverflow() {
        // integer overflow
        int number = 1922334455; // -2^31 ~ 2^31 - 1
        number = number * 5;
        System.out.println(number);
    }

    public static void testString() {
        String str = "Hello world";
        char firstChar = str.charAt(0);
        char secondChar = str.charAt(1);
        char lastChar = str.charAt(str.length() - 1);
        for (int i=0; i<str.length(); ++i) {
            System.out.print(str.charAt(i));
        }

        //字符串连接
        str += " guojing";
        str = str + " guojing";
        System.out.println(str);

        int age = 18;
        str = "I am " + (age+1) + " year old";
        System.out.println(str);


        String s = "abc";
        s = s+"def";

        s = s+10+1; // "abcdef101"
        s += 1.1;
        s += true;
        s += 'c';
        System.out.println(s);
    }

    public static void testToUpperCase() {
        String str = "Akjdhskh876876adKJHAKH";
        String result = toUpperCase(str);

        String answer = str.toUpperCase();
        if (answer.equals(result)) {
            System.out.println("test passed");
        } else {
            System.out.println("test failed");
        }
    }

    public static String toUpperCase(String str) {
        StringBuffer newStr = new StringBuffer();
        for (int i=0; i<str.length(); ++i) {
            char ch = str.charAt(i);
            if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }
            newStr.append(ch);
        }
        return newStr.toString();
    }

    public static void testswap() {
        int number1 = 10;
        int number2 = 20;
        swap(number1, number2);
        System.out.println(number1 + ", " + number2);
    }

    public static void swap(int a, int b) {
        // a: 10, b: 20
        int t = a;
        a = b;
        b = t;
        // a: 20, b:10
    }


    public static void testatoi0() {
        String str = "234234";
        System.out.println(Integer.valueOf(str));
        System.out.println(Integer.parseInt(str));
    }


    public static void testatoi() {
        String str = "-671234567";
        System.out.println(atoi(str));
    }





    //字符串转整数
    public static int atoi(String str) {
        int start = 0;
        boolean negative = false;

        //判断正负
        if (str.charAt(0) == '-') {
            negative = true;
            start++;
        } else if (str.charAt(0) == '+') {
            start++;
        }

        int number = 0;  //基
        for (int i = start; i < str.length(); ++i) {
            char ch = str.charAt(i);

            //有效性检查
            if (!Character.isDigit(ch)) {
                return negative ? 0-number : number;
            }

            //越界检查
            int digit = ch - '0';
            if (!negative && checkOverflow(number, digit)) {
                return Integer.MAX_VALUE;
            }
            if (negative && checkNegativeOverflow(number, digit)) {
                return Integer.MIN_VALUE;
            }

            //转换
            number = number*10 + digit;
        }

        return negative ? 0-number : number;  //恢复正负
    }

    //检查正整数是否越界
    public static boolean checkOverflow(int number, int digit) {
        int newNumber = number * 10 + digit;
        return !(newNumber % 10 == digit && newNumber / 10 == number);
    }


    //检查负整数是否越界
    public static boolean checkNegativeOverflow(int number, int digit) {
        if (-number * 10 - digit == Integer.MIN_VALUE) {   //why?   becouse:  -2^31 ~ 2^31-1
            return false;
        }
        return checkOverflow(number, digit);
    }







    public static void testStudent() {
        Student student1 = new Student("Jack");
        student1.takeTest(100);
        System.out.println(student1.getId());

        Student student2 = new Student("Tony");
        student2.takeTest(89);
        System.out.println(student2.getId());

        Student student3 = new Student("Mark");
        student3.takeTest(92);
        System.out.println(student3.getId());
    }

}
