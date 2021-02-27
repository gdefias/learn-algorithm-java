package Algorithm.BackTracking;

import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description:  组合总和 III

 https://leetcode-cn.com/problems/combination-sum-iii

 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字

 说明：
 所有数字都是正整数
 解集不能包含重复的组合


 输入: k = 3, n = 7
 输出: [[1,2,4]]


 输入: k = 3, n = 9
 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {

    public static void main(String[] args) {

        System.out.println(combinationSum(-1,9));
    }

    //方法1
    public static List<List<Integer>> combinationSum(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int start = 1;
        int sum = 0;

        if(k<1 || k>9 || n<1 ) {  //剪枝
            return res;
        }

        backtrack(k, n, start, sum, path, res);

        return  res;
    }

    /**
     * 回溯 + 剪枝
     * */
    public static void backtrack(int k, int n, int start, int sum, List<Integer> path, List<List<Integer>> res) {
        if(sum>n) {     //剪枝
            return;
        }

        if(path.size() == k) {
            if(sum == n) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for(int i=start; i<=9; i++) {
            sum += i;
            path.add(i);

            backtrack(k, n, i+1, sum, path, res);

            sum -= i;
            path.remove(path.size()-1);
        }
    }


    //方法2：与方法1类似，只是没有专门保存sum，而是根据当前sum每次改变target值
    public static List<List<Integer>> combinationSum2(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int start = 1;

        if(k<1 || k>9 || n<1 ) {  //剪枝
            return res;
        }

        backtrack2(k, n, start, path, res);

        return  res;
    }

    /**
     * 回溯 + 剪枝
     * */
    public static void backtrack2(int k, int n, int start, List<Integer> path, List<List<Integer>> res) {
        if(k == 0 && n == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<=9; i++) {
            if(n-i < 0) {  //剪枝
                break;
            }
            path.add(i);

            backtrack2(k-1, n-i, i+1, path, res);

            path.remove(path.size()-1);
        }
    }
}
