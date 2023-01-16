package Algorithm.Bit;

/**
 * Created by Defias on 2017/10/15.

 二进制中1的个数

 https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/

 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。
 例如，把 9表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

 输入：00000000000000000000000000001011
 输出：3
 */

public class HammingWeight {
    public static void main(String[] args) {
        int num = -1;
        System.out.println(num);
        System.out.println(hammingWeight(num));
    }

    public static int hammingWeight(int n) {
        int count = 0;

        while(n != 0) {
            if((n&1) == 1) {
                count++;
            }
            n >>>= 1;
        }

        return count;
    }

    public static int hammingWeight2(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }


    public static int hammingWeight3(int num) {
        int count = 0;
        int flag = 1;
        while(flag!=0) {
            if((num&flag)!=0) {
                count++;
            }
            flag = flag<<1;
            System.out.println(flag);
        }
        return count;
    }
}
