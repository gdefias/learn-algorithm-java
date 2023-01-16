package Algorithm.Math.Base;

/**
 * Created with IntelliJ IDEA.
 * Description:   打印从1到最大的n位数
 * User: Defias
 * Date: 2018-10

 https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/

 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

 输入: n = 1
 输出: [1,2,3,4,5,6,7,8,9]

 用返回一个整数列表来代替打印
 n 为正整数
 */
public class PrintNumbers {

    public static void main(String[] args) {
        printNumbers(3);
    }

    public static int[] printNumbers(int n) {
        int[] res = new int[0];
        if(n<=0) {
            return res;
        }

        int maxn = 9;
        while(n>1) {
            maxn = maxn*10 + 9;
            n--;
        }

        res = new int[maxn];
        for(int i=1; i<=maxn; i++) {
            res[i-1] = i;
        }
        return res;
    }
}
