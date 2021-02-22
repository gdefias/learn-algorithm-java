package Questions.Math;

/**
 * Created by Defias on 2017/10/10.
 *
 * 计算各位不相同的数字个数
 * https://leetcode.com/problems/count-numbers-with-unique-digits/description/
 */

public class NumbersWithUniqueDigits {
    public static int ans = 0;

    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits2(12));
    }

    public static int countNumbersWithUniqueDigits(int n) {
        if(n==0) {
            return 1;
        }

        int base = 9;
        int ans = 10;
        for(int i=2; i<=n&&i<=10; i++) {
            base = base * (9-i+2);
            ans = ans + base;
        }
        return ans;
    }

    //用递归
    public static int countNumbersWithUniqueDigits2(int n) {
        if(n<0)
            return 0;
        if(n==0)
            return 1;
        int k = 1;
        RecursionHelper(n, k);
        return ans;
    }

    public static void RecursionHelper(int n, int k) {
        if(n<k)
            return;
        else {
            if(k==1) {
                ans  += 10;
            } else if(k==2) {
                ans += 9*9;
            } else {
                int count=0;
                int base=9;
                for(int i=2; i<k&&base>=1; i++) {
                    count = 9 * (--base);
                }
                count = count * 9;
                ans += count;
            }
            RecursionHelper(n, k+1);
        }
    }
}
