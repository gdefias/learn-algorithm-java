package Questions.Bit;

import static Lib.Print.print;
/**
 * Created by Defias on 2017/10/15.
 *
 *  Bitwise operation
 *  位运算
 *
 *
 *  x << k  x乘以2的k次幂
 *  x >> k  x除以2的k次幂
 *
 *  x & 1 == 1 or 0    判断奇偶
 *  x = x & (x-1)     清零最低位的1
 *  (x & (x-1)) == 0  检测x是否是2的幂(x为2的幂时，一定只有一位为1，然后请零掉了就一定是0)
 *
 *  x & (n-1)          即：x%n x除以n的模 ???
 *  x & (~0 << n)      将x的最右边的n位清零, ~0就是一串1
 *  (x >> n) & 1       获取x的第n位值（0或者1）
 *  x & (1 << (n-1))   获取x的第n位的幂值
 *  x | (1 << n)       仅将第n位 置为1
 *  x &（~(1 << n)）    仅将第n位 置为0
 *  x &（(1 << n)-1）   将x最高位至第n位（含）清零
 *  x & （~（(1<<（n+1)）-1））   将第n位至第0位（含）清零
 *
 *  x & (1 << (k-1))  检测x倒数第k位是否为1
 *  x ^ (1 << (k-1))  将x倒数第k位转置(0变1，1变0)
 *  x & -x  取出x最右边的1 (-x的计算：补码 将x的各位取反再加1) -> 结果保留最右边为1的那一位为1，其余位为0
 *  ~x & (x+1)  取出x最右边的0 -> 结果保留最右边为0的那一位为1，其余位为0
 *
 *
 */
public class JavaBit2 {

    public static void main(String[] args) {

        //test1();
        test2();
    }

    public static void test0() {
        int finals = 0b101101000;  //二进制
        System.out.println(finals);

        int a = 1^2;
        System.out.println(a);
    }

    public static void test2() {
        int x = 0b0101_1100;
        System.out.println(x);
        System.out.println("x: " + x + " -x: " + (-x));
        print(Long.toBinaryString(x));
        print(Long.toBinaryString(-x));
        print(Long.toBinaryString(x & -x));
    }


    public static void test1() {
        int i = -1;
        print(Integer.toBinaryString(i));
        i >>>= 10;
        print(Integer.toBinaryString(i));
        long l = -1;
        print(Long.toBinaryString(l));
        l >>>= 10;
        print(Long.toBinaryString(l));
        short s = -1;
        print(Integer.toBinaryString(s));
        s >>>= 10;
        print(Integer.toBinaryString(s));
        byte b = -1;
        print(Integer.toBinaryString(b));
        b >>>= 10;
        print(Integer.toBinaryString(b));
        b = -1;
        print(Integer.toBinaryString(b));
        print(Integer.toBinaryString(b>>>10));
    }

}
