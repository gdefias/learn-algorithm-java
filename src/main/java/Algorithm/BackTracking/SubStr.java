package Algorithm.BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Defias
 * @date: 2020/12/2
 * @description: 分割回文串

 https://leetcode-cn.com/problems/palindrome-partitioning/

给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
返回 s 所有可能的分割方案。

输入: "aab"
输出:
[
["aa","b"],
["a","a","b"]
]

 */
public class SubStr {
    public static void main(String[] args) {
        String input = "aab";
        System.out.println(partition(input));
    }

    public static List<List<String>> partition(String input) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new LinkedList<>();
        int start = 0;
        int len = input.length();
        if(len<=0) {
            return res;
        }

        backstrace(input, len, start, path, res);

        return res;
    }

    public static void backstrace(String input, int len, int start, List<String> path, List<List<String>> res) {
        if(start>=len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<len; i++) {
            if(!isPalindrome(input, start, i)) {
                continue;
            }

            String substr = input.substring(start, i+1);
            path.add(substr);
            backstrace(input, len, i+1, path, res);
            path.remove(path.size()-1);
        }
    }

    public static boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
