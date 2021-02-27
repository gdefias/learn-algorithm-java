package Algorithm.dfsAndbfs;

/**
 * Created with IntelliJ IDEA.
 * Description: 1～n整数中1出现的次数
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/

 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

 输入：n = 12
 输出：5

 1 <= n < 2^31
 */
public class CountDigitOne {

    public static void main(String[] args) {
        System.out.println(countDigitOne2(22));
    }

    //方法1：DFS
    public static int countDigitOne(int n) {
        return dfs(n);
    }

    public static int dfs(int n) {
        if(n<=0) {
            return 0;
        }

        String sn = String.valueOf(n);
        int hight = sn.charAt(0) - '0';     //1234        5234
        int len = sn.length(); //4
        int pow = (int)Math.pow(10, len-1);  //1000
        int left = n % pow;  //234

        if(hight==1) {
            return dfs(pow-1) + dfs(left) + left + 1;
        } else {
            return dfs(pow-1)*hight + dfs(left) + pow;
            //dfs(pow-1)*hight: 最高位分别为0~hight-1时统计低位的1
            //dfs(left): 最高位为hight时统计低位的1
            //pow: 最高位为1时，低位每个数都需要统计1次
        }
    }



    //将 1 ~ n 的个位、十位、百位、...的 1 出现次数相加，即为 1 出现的总次数
    public static long countDigitOne2(long n) {  //1234
        long count = 0;
        long i = 1;
        long current=0, after=0, befor=0;
        while((n/i)!=0) {           //1234
            current = (n/i)%10;  //4
            befor = n/(i*10);  //123
            after = n%i;  //0
            if(current==0) {
                count = count + befor*i;
            } else if(current==1) {
                count = count + befor*i + (after+1);
            } else if(current>1) {
                count = count  + befor*i + i;
            }
            i = i*10;
        }
        return count;
    }
}
