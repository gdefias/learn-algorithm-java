package Algorithm.Math;

/**
 * @author: Felix
 * @date: 2023/1/11
 * @description: 丑数 III

    https://leetcode.cn/problems/ugly-number-iii/description/

    给你四个整数：n 、a 、b 、c ，请你设计一个算法来找出第 n 个丑数。
    丑数是可以被 a 或 b 或 c 整除的 正整数 。(并非因子只包含a 或 b 或 c。跟丑数II不是一个概念)
 */
public class UglyNumber3 {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(1000000000, 2,217983653,336916467));
    }

    //错误？
    public static int nthUglyNumber(int n, int a, int b, int c) {
        int p1 = 1;
        int p2 = 1;
        int p3 = 1;

        int ans = 0;
        for(int i=1; i<=n; i++) {
            int num1 = a * p1;
            int num2 = b * p2;
            int num3 = c * p3;

            if(p2 == 9) {
                System.out.println(p2);
            }


            if(p3 == 5) {
                System.out.println(p3);
            }

            ans = Math.min(num1, Math.min(num2, num3));
            if(ans == num1) {
                p1++;
            }

            if(ans == num2) {
                p2++;
            }

            if(ans == num3) {
                p3++;
            }
        }

        return ans;
    }
}
