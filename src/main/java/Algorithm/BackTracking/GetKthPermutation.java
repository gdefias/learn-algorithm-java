package Algorithm.BackTracking;

/**
 * Created by Defias on 2020/07.
 * Description: 排列序列  第k个排列

 https://leetcode-cn.com/problems/permutation-sequence/

 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

 "123"
 "132"
 "213"
 "231"
 "312"
 "321"
 给定 n 和 k，返回第 k 个排列
 给定 n 的范围是 [1, 9]
 给定 k 的范围是[1,  n!]


 输入: n = 3, k = 3
 输出: "213"

 输入: n = 4, k = 9
 输出: "2314"
 */
public class GetKthPermutation {

    public static void main(String[] args) {
        System.out.println(getPermutation(4,9));
    }

    public static String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for(int i=0; i<n; i++) {
            nums[i] = i+1;
        }

        StringBuilder res = new StringBuilder();
        boolean[] visit = new boolean[n];
        int depth = 0;
        dfs(n, k, nums, depth, visit, res);

        return res.toString();
    }

    public static void dfs(int n, int k, int[] nums, int depth, boolean[] visit, StringBuilder res) {
        if(res.length()==n) {
            return;
        }

        //计算当前层的树枝上的叶子结点总数
        int cursum = factorial(n-depth-1);

        for(int i=0; i<n; i++) {
            if(visit[i]) {
                continue;
            }

            if(cursum < k) {
                k -= cursum;
                continue;
            }

            visit[i] = true;
            res.append(String.valueOf(nums[i]));

            dfs(n, k, nums, depth+1, visit, res);
            return;
        }
    }

    //阶乘
    public static int factorial(int n) {
        int res = 1;
        while(n>0) {
            res *= n--;
        }
        return res;
    }
}
