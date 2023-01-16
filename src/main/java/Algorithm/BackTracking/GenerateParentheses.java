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
        List<String> res = generateParenthesis2(3);
        System.out.println(res.size());
        for(String s: res) {
            System.out.println(s);
        }
    }

    //方法1：回溯
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        StringBuilder path = new StringBuilder();
        backtrack(ans, path, 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder path, int left, int right, int n) {
        if (path.length() == n*2) {
            ans.add(path.toString());
            return;
        }

        if (left < n) {
            path.append('(');
            backtrack(ans, path, left+1, right, n);
            path.deleteCharAt(path.length()-1);
        }

        if (right < left) {
            path.append(')');
            backtrack(ans, path, left, right+1, n);
            path.deleteCharAt(path.length() - 1);
        }
    }

    //方法2： DFS  递归
    public static List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<String>();

        //left： 左括号(数
        //right： 右括号(数
        dfs2(res, n, 0, 0, "");
        return res;
    }

    public static void dfs2(List<String> res, int n, int left, int right, String subres) {
        if(subres.length() == n*2) {
            res.add(subres);
            return;
        }

        if(left < n) {
            dfs2(res, n, left+1, right, subres+"(");
        }

        if(right < left) {  //递归过程中始终确保左括号数大于右括号数
            dfs2(res, n, left, right+1, subres+")");
        }
    }


    //方法3：DFS  递归  全遍历+过滤
    public static List<String> generateParenthesis3(int n) {
        List<String> res = new ArrayList<String>();
        dfs3(res, n, "");
        return res;
    }

    public static void dfs3(List<String> res, int n, String subres) {
        if(subres.length() == n*2) {
            if(isVerify(subres)) {  //对每个结果进行校验以过滤出符合条件的
                res.add(subres);
            }
            return;
        }

        dfs3(res, n, subres+"(");
        dfs3(res, n, subres+")");
    }

    //是否是有效的括号对
    public static boolean isVerify(String subres) {
        if(subres==null || subres.length()==0 || subres.charAt(0)!='(') {
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
