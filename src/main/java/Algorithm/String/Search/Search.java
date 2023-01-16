package Algorithm.String.Search;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-10

 子字符串查找（字符串匹配）
 找出字符串中第一个匹配项的下标

 https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/

 对于一个给定的source字符串（长度为N）和一个target字符串（长度为M），在source字符串中找出target字符串出现的第一个位置(从0开始)
 如果不存在，则返回-1

 时间复杂度： O(n*m)   其中n和m分别为主串和模式串的长度

 输入：haystack = "sadbutsad", needle = "sad"
 输出：0
 解释："sad" 在下标 0 和 6 处匹配。第一个匹配项的下标是 0 ，所以返回 0 。

 输入：haystack = "leetcode", needle = "leeto"
 输出：-1
 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 */

public class Search {

    public static void main(String[] args) {
        String s = "sdfSDSDF@%$VXSxcvdsfaDVZdsafasdfsadfSV";
        String t = "faDVZ";
        //Strings t = "sdfSDSDF@%$VXSxcvdsfaDVZSV";
        System.out.println(strStr(s, t));
        System.out.println(strStr2(s, t));
    }

    //方法1：模拟 双循环 遍历
    public static int strStr(String source, String target) {
        if(source==null || target==null || source.length() < target.length()) {
            return -1;
        }

        //source串从i位置开始匹配 遍历不同的i位置
        for(int i=0; i<=source.length()-target.length(); i++) {
            int j;
            for(j=0; j<target.length(); j++) {
                if(source.charAt(j+i) != target.charAt(j)) {
                    break;
                }
            }
            if(j==target.length()) {
                return i;
            }
        }
        return -1;
    }

    //方法2：双指针
    public static int strStr2(String source, String target) {
        if(source==null || target==null || source.length() < target.length()) {
            return -1;
        }

        int i = 0;
        int j = 0;
        while(i<source.length() && j<target.length()) {
            if(source.charAt(i) == target.charAt(j)) {
                i++;
                j++;
            } else {
                i = i-j+1;  //source串从i位置开始匹配 遍历不同的i位置（i，j位置的字符不匹配时，i位置退回j个位置然后加1）
                j = 0;
            }
        }

        if(j == target.length()) {
            return i-j;
        } else {
            return -1;
        }
    }


}
