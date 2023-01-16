package Algorithm.Math.Base;

import Lib.Object;

/**
 * Created by Defias on 2016/3/2.
 */

public class Base {

    public static void  main(String[] args) {

        testToUpperCase();

        testIntegrerOverflow();

        int result = reverse3bitNum(123);
        System.out.print(result + "\n");

        char a = '%';
        result = charToInteger(a);
        System.out.print(result + "\n");


        String str = "234234";
        System.out.println(Integer.valueOf(str));
        System.out.println(Integer.parseInt(str));
    }

    //字符串操作
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

    //测试字符串转大写
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

    //字符串转大写
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


    //整数越界问题
    public static void testIntegrerOverflow() {
        // integer overflow
        int number = 1922334455; // -2^31 ~ 2^31 - 1
        number = number * 5;  //乘以5 值反而变小 1021737683
        System.out.println(number);
    }


    //反转3位整数
    public static int reverse3bitNum(int Num) {
        int g,s,b;
        g = Num % 10;
        s = Num / 10 % 10;
        b = Num / 100;
        return g*100+s*10+b;
    }

    //将字符转换为一个整数
    public static int charToInteger(char character) {
        return (int)character;
    }


}
