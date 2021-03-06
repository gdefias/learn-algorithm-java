/**
 * Created by Defias on 2016/6/5.
 *
 * 32位整型能表示的范围：
 * 对于无符号整数： 0 ~ 2^32 即：0~4294967296
 * 对于有符号整数： -2^31 ~ 2^31 即：-2147483648~2147483648
 *
 * 位运算
 *
 * <<左移n位相当于除以2^n    右边补0
 *
 * >>右移n位相当于乘以2^n    左边补符号位值
 *
 * >>>无符号右移，忽略符号位，空位都以0补齐      左边补0
 *
 * 移位运算仅作用于32位整型和64位长整型，若在整型数上移动32位，无论是否带符号位以及移动方向，均为本身（移动的位数是mod32/mod64的结果）
 * 对于32位整型：
 * 35>>1 = 35>>33         35>>32 = 35>>0 = 35      -5>>34 = -5>>2      -5>>>34 = -5>>>2
 *  -5<<34 = -5<<2        5<<-2 = 5<<30
 *
 * 对于64位整型：
 * 35<<1 = 35<<65         35<<64 = 35<<0 = 35
 *
 *
 */
package Algorithm.Bit;

public class JavaBit1 {

    public static void main(String[] args) {
        int num = 1;
        //System.out.println(num<<32);
        //System.out.println(num);
        //
        //num = num<<31;
        //System.out.println(num);
        //
        //num = num<<1;
        //System.out.println(num);

//        //原始数二进制
//        //int number = Integer.MAX_VALUE;
//        int number = (2 << 24);
//        printInfo(number);
//
//        //左移一位
//        number = number << 1;
//        printInfo(number);
//
//        //右移一位
//        number = number >> 1;
//        printInfo(number);

        testBit();

    }


    public static void printInfo(int num) {
        System.out.println("-5 << 2: " + String.valueOf(-5 << 2));
        /*
              11111111 11111111 11111111 11111011
          <<2
          ----------------------------------------
              11111111 11111111 11111111 11101100 （补码）
              11111111 11111111 11111111 11101011  （反码）
              10000000 00000000 00000000 00010100 （原码 即：-20）
        */
        System.out.println(Integer.toBinaryString(num));     //输出一个int的二进制数
    }

    /**
     * 位运算
     */
    public static void testBit() {
        /*
              0（不是正数也不是负数）：   00000000 00000000 00000000 00000000 （补码）
              -2147483648(Integer.MIN_VALUE 人为规定)：   10000000 00000000 00000000 00000000 （补码） --避免出现正0和负0两个0编码

              00000000 00000000 00000000 00000001 （补码）
              00000000 00000000 00000000 00000001  （反码）
              00000000 00000000 00000000 00000001 （原码 即： 1）

              00000000 00000000 00000000 00001001 （补码）
              00000000 00000000 00000000 00001001  （反码）
              00000000 00000000 00000000 00001001 （原码 即： 9）

              01000000 00000000 00000000 00000000 （补码）
              01000000 00000000 00000000 00000000 （反码）
              01000000 00000000 00000000 00000000 （原码 即： 1073741824）

              01111111 11111111 11111111 11111111 （补码）
              01111111 11111111 11111111 11111111  （反码）
              01111111 11111111 11111111 11111111 （原码 即： 2147483647）


              11111111 11111111 11111111 11111111 （补码）
              11111111 11111111 11111111 11111110  （反码）
              10000000 00000000 00000000 00000001 （原码： -1）

              11000000 00000000 00000000 00000000 （补码）
              10111111 11111111 11111111 11111111  （反码）
              11000000 00000000 00000000 00000000 （原码 即： -1073741824）

              10000000 00000000 00000000 00000001 （补码）
              10000000 00000000 00000000 00000000  （反码）
              11111111 11111111 11111111 11111111 （原码 即： -2147483647）

        * */

        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(100));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(-2147483647));

        short s = -1;
        System.out.println(Integer.toBinaryString(s));
        s >>>= 10;
        System.out.println(Integer.toBinaryString(s));


        System.out.println("5&9: " + String.valueOf(5&9));
        /*
            00000101（省略前面24个0）
          & 00001001
          -----------
            00000001
        */

        System.out.println("5|9: " + String.valueOf(5|9));
        /*
            00000101
          & 00001001
          -----------
            00001101
        */

        System.out.println("~-5: " + String.valueOf(~-5));
        /*
            10000000 00000000 00000000 00000101 （-5的原码：最高位表示符号位，0为正1为负）
            11111111 11111111 11111111 11111010  （-5的反码：符号位不变，其余位求反）
            11111111 11111111 11111111 11111011  （-5的补码：反码+1  负数在计算机中是以补码存在的）
          ~
          ------------------------------------------------------------------------------
            00000000 00000000 00000000 00000100 （即：4 正数的原码反码补码都相同）
        */

        System.out.println("5^9: " + String.valueOf(5^9)); //异或
        /*
            00000101
          ^ 00001001
          -----------
            00001100
        */

        System.out.println("-5^9: " + String.valueOf(-5^9));
        /*
           11111111 11111111 11111111 11111011
         ^ 00000000 00000000 00000000 00001001
         ----------------------------------------
           11111111 11111111 11111111 11110010 （补码）
           11111111 11111111 11111111 11110001 （反码）
           10000000 00000000 00000000 00001110 （原码  即：-14）
        */

        System.out.println("5 << 2: " + String.valueOf(5 << 2)); //左移位：将运算数的二进制码整体左移指定位数，左移后右边空出来的位以0来填充 （左移n位相当于乘以2^n）
        /*
              00000000 00000000 00000000 00000101
          <<2
          ----------------------------------------
              00000000 00000000 00000000 00010100 （20）
        */

        System.out.println("-5 << 2: " + String.valueOf(-5 << 2));
        /*
              11111111 11111111 11111111 11111011
          <<2
          ----------------------------------------
              11111111 11111111 11111111 11101100 （补码）
              11111111 11111111 11111111 11101011  （反码）
              10000000 00000000 00000000 00010100 （原码 即：-20）
        */


        //左移位运算由于符号位参与向左移位   正数或负数 向左移位后的结果 可能是正也可能是负
        System.out.println("-1073741839 << 1: " + String.valueOf(-1073741839 << 1));
        /*
              11000000 00000000 00000000 00001111  （原码  即：-(2^30+15)）
              10111111 11111111 11111111 11110000   （反码）
              10111111 11111111 11111111 11110001  （补码）
          <<1
          ----------------------------------------
              01111111 11111111 11111111 11100010 （补码）
              01111111 11111111 11111111 11100010  （反码）
              01111111 11111111 11111111 11100010 （原码 即：2147483618）
        */




        System.out.println("5 >> 2: " + String.valueOf(5 >> 2));  //右移位：把第一个操作数的二进制码右移指定位数后，左边空出来的位以原来的符号位来填充（右移n位相当于除以2^n）
        /*
              00000000 00000000 00000000 00000101
          >>2
          ----------------------------------------
              00000000 00000000 00000000 00000001 （1）
        */

        System.out.println("-5 >> 2: " + String.valueOf(-5 >> 2));  //负数右移
        /*
              11111111 11111111 11111111 11111011
          >>2
          ----------------------------------------
              11111111 11111111 11111111 11111110 （补码）
              11111111 11111111 11111111 11111101  （反码）
              10000000 00000000 00000000 00000010 （原码 即：-2）
        */

        System.out.println("16 >>> 2: " + String.valueOf(16 >>> 2)); //无符号右移位：把第一个操作数的二进制码右移指定位数后，左边空出来的位以0来填充


        System.out.println("-5 >>> 2: " + String.valueOf(-5 >>> 2));
        /*
              11111111 11111111 11111111 11111011
          >>2
          ----------------------------------------
              00111111 11111111 11111111 11111110 （正数 即：1073741822）
        */


        // 5>>>32 = -5>>>0
        System.out.println("-5 >>> 32: " + String.valueOf(-5 >>> 32));
        System.out.println("-5 >>> 0: " + String.valueOf(-5 >>> 0));

       // 5<<-2 = 5<<30
        System.out.println("5 << -2: " + String.valueOf(5 << -2));
        System.out.println("5 << 30: " + String.valueOf(5 << 30));

        System.out.println("1 << 5: " + String.valueOf(2 >> 5));
    }
}
