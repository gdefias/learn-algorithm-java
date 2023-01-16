package Algorithm.Math.Base;

/**
 * @author: Felix
 * @date: 2023/1/16
 * @description: 排列组合数
 */
public class PartitionCombineNum {

    public static void main(String[] args) {
        int n = 9;
        int m = 3;
        System.out.println(partitionNum(n, m));

        System.out.println(CombineNum(n, m));
    }


    //An^m = n * (n-1) * (n-2) * (n-3) * (n-m+1) = n!/(n-m)!
    public static int partitionNum(int n, int m) {

        int ans = 1;
        for(int i=0; i<m; i++) {
            ans *= n-i;
        }

        return ans;
    }


    //Cn^m = An^m/m! =  n!/(n-m)!m!
    public static int CombineNum(int n, int m) {
        int mj = 1;
        for(int i=0; i<m; i++) {
            mj *= m-i;
        }

        int ans = partitionNum(n, m);
        ans = ans / mj;

        return ans;
    }
}
