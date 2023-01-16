package Algorithm.DFSAndBFS;

/**
 * Created with IntelliJ IDEA.
 * Description: 1～n整数中1出现的次数
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/

 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 例如，输入12，1～12这些整数中包含1的数字有1、10、11和12，1一共出现了5次。

 输入：n = 12
 输出：5

 1 <= n < 2^31
 */
public class CountDigitOne {

    public static void main(String[] args) {
        System.out.println(countDigitOne4(10));
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
            //dfs(pow-1): 从1到少一位的最大数中1的个数 【当len为4时 统计1~999中1的个数】
            //left: 从001开始每个数都可以与最高位1组成一个含有一个1的数 【1001~1234首位的1】 1:【1000首位的1】
            //dfs(left): 上一步中没有统计除首位外其他位本身含的1 【1001~1234除首位的1外，其他位的1】

        } else {
            return dfs(pow-1)*hight + dfs(left) + pow;
            //dfs(pow-1)*hight: 最高位分别为0~hight-1时统计低位的1
            //dfs(left): 最高位为hight时统计低位的1
            //pow: 最高位为1时，低位每个数都需要统计1次(0~999)
        }
    }

    // DFS
    public static int countDigitOne2(int n) {
        if(n<=0) {
            return 0;
        }

        int len = getLen(n);
        int hw = getHw(n);
        int pow = (int)Math.pow(10, len-1);
        int left = n % pow;

        if(hw == 1) {
            return countDigitOne2(pow-1) + countDigitOne2(left) + left + 1;
        } else {
            return countDigitOne2(pow-1)*hw + countDigitOne2(left) + pow;
        }
    }

    //获取位数
    public static int getLen(int n) {
        int len = 0;
        while(n>0) {
            len++;
            n = n / 10;
        }
        return len;
    }

    //获取最高位
    public static int getHw(int n) {
        while(n>=10) {
            n = n / 10;
        }
        return n;
    }



    //方法2：将 1 ~ n 的个位、十位、百位、...的 1 出现次数相加，即为 1 出现的总次数
    public static long countDigitOne3(long n) {  //1234
        long count = 0;
        long i = 1;
        long current=0, after=0, befor=0;

        while((n/i)>0) {           //1234
            current = (n/i)%10;  //4  3
            befor = n/(i*10);  //123  12
            after = n%i;  //0  4
            if(current==0) {
                count = count + befor*i;
            } else if(current==1) {
                count = count + befor*i + (after+1);
            } else if(current>1) {
                count = count + befor*i + i;
            }
            i = i*10;
        }
        return count;
    }


    //方法3：遍历累加每个数的1  时间复杂度太高
    public static int countDigitOne4(int n) {
        int count = 0;
        for(int i=1; i<=n; i++) {
            count = count + getOneNum(i);
        }
        return count;
    }

    public static int getOneNum(int k) {
        int count = 0;

        while(k>0) {
            int low = k%10;
            if(low == 1) {
                count++;
            }
            k = k/10;
        }

        return count;
    }
}
