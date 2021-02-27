package Algorithm.BackTracking;
import java.util.*;
/**
 * Created by Defias on 2017/10/14.

 括号生成

 https://leetcode-cn.com/problems/generate-parentheses/submissions/

 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合

 输入：n = 3
 输出：[
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        List<String> res = Solution(3);
        System.out.println(res.size());
        for(String s: res) {
            System.out.println(s);
        }
    }

    //方法1：回溯 剪枝 DFS
    public static List<String> Solution(int n) {
        List<String> res = new ArrayList<String>();
        SolutionHelper(res, n, 0, 0, "");
        return res;
    }

    public static void SolutionHelper(List<String> res, int n, int left, int right, String subres) {
        if(subres.length()==n*2) {
            res.add(subres);
            return;
        }

        if(left<n) {
            SolutionHelper(res, n, left+1, right, subres+"(");
        }

        if(right<left) {
            SolutionHelper(res, n, left, right+1, subres+")");
        }
    }


    //方法2：回溯  全遍历 过滤
    public static List<String> Solution2(int n) {
        List<String> res = new ArrayList<String>();
        Solution2Helper(res, n,  0, "");
        return res;
    }

    public static void Solution2Helper(List<String> res, int n, int level, String subres) {
        if(level==n*2) {
            if(isVerify(subres))
                res.add(subres);
            return;
        }
        Solution2Helper(res, n,  level+1, subres+"(");
        Solution2Helper(res, n,  level+1, subres+")");
    }

    //是否是有效的括号对
    public static boolean isVerify(String subres) {
        if(subres==null||subres.length()==0||subres.charAt(0)!='(') {
            return false;
        }

        int left=1;
        int right=0;
        for(int i=1; i<subres.length(); i++) {
            if(subres.charAt(i)=='(') {
                left++;
            }
            if(subres.charAt(i)==')') {
                right++;
            }

            //任何时候左括号的累计数量都要大于或等于右括号的累计数量
            if(right>left) {
                return false;
            }
        }
        return left==right? true: false;
    }
}
