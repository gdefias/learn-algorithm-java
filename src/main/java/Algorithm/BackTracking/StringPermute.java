package Questions.BackTracking;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Defias on 2020/07.
 * Description:  字符串全排列
 *
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 *
 * 输入一个字符串，打印出该字符串中字符的所有排列
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素
 *
 * 全排列 O(n!)
 *
 * n个不重复字符的全排列个数：n!
 * 输入：123
 * 输出：123 132 213 231 312 321
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * 1 <= s 的长度 <= 8
 */


public class StringPermute {

    public static void main(String[] args) {
        String s = "abc";
        String[] res = permutation1(s);

        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }

    /**
     * 无重复字符的字符串全排列
     * */
    public static String[] permutation(String s) {
        String result = "";  //辅助字符串，存放已遍历的结果
        List<String> res = new LinkedList<String>();

        boolean[] used = new boolean[s.length()];

        permutation(s, result, used, res);

        return (String[])res.toArray(new String[res.size()]);
    }

    public static void permutation(String s, String result, boolean[] used, List<String> res) {
        if(result.length()==s.length()) {   //满足条件时输出并返回
            res.add(new String(result));
            //System.out.println(result);
            return;
        }

        for(int i=0; i<s.length(); i++){
            if(used[i]) {
                continue;
            }
            used[i] = true;
            permutation(s, result+s.charAt(i), used, res);
            used[i] = false;
        }
    }


    /**
     * 允许重复字符的字符串全排列
     * */
    public static String[] permutation1(String s) {
        String result = "";  //辅助字符串，存放已遍历的结果
        HashSet<String> res = new HashSet<String>();  //使用hashset

        boolean[] used = new boolean[s.length()];

        permutation1(s, result, used, res);

        return (String[])res.toArray(new String[res.size()]);
    }

    public static void permutation1(String s, String result, boolean[] used, HashSet<String> res) {
        if(result.length()==s.length()) {   //满足条件时输出并返回
            res.add(new String(result));
            //System.out.println(result);
            return;
        }

        for(int i=0; i<s.length(); i++){
            if(used[i]) {
                continue;
            }
            used[i] = true;
            permutation1(s, result+s.charAt(i), used, res);
            used[i] = false;
        }
    }
}
