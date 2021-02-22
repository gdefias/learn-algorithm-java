package Questions.Bit;
/**
 * Created with IntelliJ IDEA.
 * Description: 不用加减乘除做加法
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/

 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号

 输入: a = 1, b = 1
 输出: 2

 a, b 均可能是负数或 0
 结果不会溢出 32 位整数
 */

public class Add {
    public static void main(String[] args) {
        System.out.println(add(899, 111));
    }

    /**
     主要利用异或运算来完成
     异或运算有一个别名叫做：不进位加法
     那么a ^ b就是a和b相加之后，该进位的地方不进位的结果
     然后下面考虑哪些地方要进位，自然是a和b里都是1的地方
     a & b就是a和b里都是1的那些位置，a & b << 1 就是进位之后的结果
     所以：a + b = (a ^ b) + (a & b << 1)
     可以知道，这个过程是在模拟加法的运算过程，进位不可能一直持续，所以b最终会变为0
     因此重复做上述操作就可以求得a + b的值
     */

    public static int add(int a, int b) {
        if(b==0) {
            return a;
        }
        int c = (a&b)<<1;   //进位
        a = a^b;  //无进位加法
        return add(a, c);
    }

    public static int add2(int a, int b) {
        while(b!=0) {
            int c = (a&b)<<1;
            a = a^b;
            b = c;
        }
        return a;
    }
}
