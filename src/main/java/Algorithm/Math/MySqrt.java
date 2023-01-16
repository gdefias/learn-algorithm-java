package Algorithm.Math;

/**
 * Created by Defias on 2017/10/15.

 x 的平方根

 https://leetcode.cn/problems/sqrtx/

 实现 int sqrt(int x) 函数。
 计算并返回 x 的平方根，其中 x 是非负整数。
 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去

 输入: 8
 输出: 2
 说明: 8 的平方根是 2.82842...,
 由于返回类型是整数，小数部分将被舍去。

 */
import static java.lang.Math.abs;

public class MySqrt {

    public static void main(String[] args) {
        System.out.println(mySqrt(4)); //2147395599
    }

    //方法1：二分查找法
    //时间复杂度：O(logN)
    public static int mySqrt(int x) {
        int left = 0;
        int right = x;
        int res = -1;

        while(left <= right) {
            int mid = left + (right-left)/2;
            long r = (long)mid * (long)mid;  //一定要先转成long类型后再相乘，否则两个大int相乘会超出int的范围而得到一个越界的负数
            if(r <= x) {
                res = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return res;
    }

    //方法1进阶：若要求返回浮点数
    //时间复杂度：O(logN)
    public static double mySqrt_(double x) {
        double low=0, hight=x;
        double mid = (low+hight)/2;
        double premid;

        do {
            if(mid * mid > x) {
                hight = mid;
            } else {
                low = mid;
            }
            premid = mid;
            mid = (low+hight)/2;
        } while (Math.abs(mid-premid) > 0.0000001);
        return mid;
    }


    //方法2：牛顿迭代法 逐个查找
    //时间复杂度：O(N)
    public static int mySqrt2(int x) {
        long r = x;
        while (r*r > x)
            r = (r + x/r) / 2;
        return (int)r;
    }
}
