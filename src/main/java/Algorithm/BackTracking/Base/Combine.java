package Algorithm.BackTracking.Base;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 组合

 https://leetcode-cn.com/problems/combinations/

 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合

 输入:n = 4, k = 2
 输出:
 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 */
public class Combine {

    public static void main(String[] args) {
        System.out.println(combine(4,2));
    }

    //传统循环的解法：k层for循环
    //「如果n为100，k为50呢，那就50层for循环，是不是开始窒息！ 此时就会发现虽然想暴力搜索，但是用for循环嵌套连暴力都写不出来！」
    //「回溯法就用递归来解决嵌套层数的问题, 每一次的递归中嵌套一个for循环，那么递归就可以用于解决多层嵌套循环的问题了」

    //方法1：回溯
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();  //存放所有结果
        List<Integer> path = new ArrayList<>();    //存放单个结果

        int start = 1;   //记录本层递归的过程中，集合从哪里开始遍历（集合就是[1,...,n] ）

        backtrack(n, k, start, path, res);
//        backtrack2(n, k, start, path, res);
//        backtrack3(n, k, start, path, res);

        return res;
    }


    /**
     * 回溯
     *
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围（通过start变量）
     * n相当于树的宽度，k相当于树的深度
     * 每次搜索到了叶子节点，就找到了一个结果
     * */
    public static void backtrack(int n, int k, int start, List<Integer> path,  List<List<Integer>> res) {
        if(path.size()==k) {  //满足要求的结果
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<=n; i++) {
            path.add(i);   //往List的末尾添加
//            System.out.println("递归之前 => " + path);  //调试输出以比较剪枝与不剪枝打印的数量差别

            backtrack(n, k, i+1, path, res);

            path.remove(path.size()-1);  //从List的末尾移除
//            System.out.println("递归之后 => " + path);
        }
    }


    /**
     * 回溯 + 剪枝
     *
     * 剪枝原理
     * 当前层的递归是找到 k 个元素，即我们这个 for 循环添加的是 k 个元素中的第一个元素，然后再递归找剩下的 k - 1 个元素，如果我们添加
     * 的这第 1 个元素后，后面剩下的不足 k - 1 个元素，那么最终肯定是不满足条件的，那么其实就无需进行递归，可以根据这个条件进行剪枝，
     * 即每次都预留后面的元素 大于等于 k - 1 个元素
     *
     * 剪枝精髓是：for循环在寻找起点的时候要有一个范围，如果这个起点到集合终止之间的元素已经不够题目要求的k个元素了，就没有必要搜索了」
     *
     * 已经选择的元素个数：path.size();
     * 还需要的元素个数为: k - path.size();
     * 在集合n中至多要从该起始位置 : n - (k - path.size()) + 1，开始遍历
     * 举个例子，n = 4，k = 3， 目前已经选取的元素为0（path.size为0），n - (k - 0) + 1 即 4 - ( 3 - 0) + 1 = 2
     *
     * 理解剪枝：https://leetcode.cn/problems/combinations/solutions/13436/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
     * */
    public static void backtrack2(int n, int k, int start, List<Integer> path,  List<List<Integer>> res) {
        if(path.size()==k) {
            res.add(new ArrayList<>(path));
            return;
        }

        //搜索起点的上界 + 接下来要选择的元素个数-1 = n
        //接下来要选择的元素个数 = k-path.size()
        //搜索起点的上界 = n-(k-path.size())+1
        for(int i=start; i<=n-(k-path.size())+1; i++) {
            path.add(i);
            System.out.println("递归之前 => " + path);
            backtrack2(n, k, i+1, path, res);

            path.remove(path.size()-1);
            System.out.println("递归之后 => " + path);
        }
    }

    /**
     * 回溯 + 剪枝
     *
     * 直接用k来标识还要选择多少个元素，而不是使用path的size来判断是否得到一个结果
     * */
    public static void backtrack3(int n, int k, int start, List<Integer> path,  List<List<Integer>> res) {
        if(k==0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<=n-k+1; i++) {
            path.add(i);

            //每次找到一个元素后从剩余元素中找k-1组合
            backtrack3(n, k-1, i+1, path, res);

            path.remove(path.size()-1);
        }
    }


    //方法2：DP
    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n<=0 || k<=0 || k>n) {
            return res;
        }

        List<List<Integer>>[][] dp = new ArrayList[n+1][k+1];
        List<Integer> item = new ArrayList<>();
        item.add(1);
        res.add(new ArrayList<>(item));
        dp[1][1] = new ArrayList<>(res);

        for(int i=2; i<=n; i++) {
            item.set(0, i);
            res.add(new ArrayList<>(item));
            dp[i][1] = new ArrayList<>(res);
        }
        res.clear();
        item.clear();

        for(int i=2; i<=n; i++) {
            for(int j=2; j<=k; j++) {
                if(j>i) {
                    continue;
                }

                if(i-1>=j) {
                    res = new ArrayList<>(dp[i-1][j]);
                } else {
                    res = new ArrayList<>();
                }

                List<List<Integer>> additem = new ArrayList<>(dp[i-1][j-1]);
                for(List<Integer> aitem : additem) {
                    item = new ArrayList<>(aitem);
                    item.add(i);
                    res.add(new ArrayList<Integer>(item));
                }

                dp[i][j] = new ArrayList<>(res);
                res.clear();
            }
        }

        return dp[n][k];
    }
}
