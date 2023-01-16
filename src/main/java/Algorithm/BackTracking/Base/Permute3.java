package Algorithm.BackTracking.Base;
import java.util.*;

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


public class Permute3 {

    public static void main(String[] args) {
        String s = "aab";
        String[] res = permutation(s);

        for(int i=0;i<res.length;i++){
            System.out.println(res[i]);
        }
    }

    /**
     * 回溯算法
     * */
    public static String[] permutation(String s) {
//        List<String> res = new LinkedList<String>();  //存放结果
        Set<String> res = new HashSet<String>();  //去重方式2: 使用HashSet存放结果确保不重复(无需排序)
        String path = "";  //存放已遍历到的结果
        boolean[] used = new boolean[s.length()];

        //字符串排序
//        char[] sc = s.toCharArray();
//        Arrays.sort(sc);
//        s = new String(sc);

        backstrace(s, path, used, res);

        return (String[])res.toArray(new String[res.size()]);
    }

    public static void backstrace(String s, String path, boolean[] used, Set<String> res) {
        if(path.length() == s.length()) {   //满足条件时输出并返回
            res.add(new String(path));
            return;
        }

        for(int i=0; i<s.length(); i++){
            if(used[i]) {
                continue;
            }

            //去重方式1(需要排序)
//            if(i>0 && s.charAt(i)==s.charAt(i-1) && !used[i-1]) {
//                continue;
//            }

            used[i] = true;
            backstrace(s, path+s.charAt(i), used, res);
            used[i] = false;
        }
    }
}
