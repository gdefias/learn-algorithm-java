package Algorithm.String;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-10

 反转字符串

 https://leetcode-cn.com/problems/reverse-string/

 */
public class Reverse {
    public static void main(String[] args) {
        String s = "abcdefghcb";
        System.out.println(reverse(s));
    }

    //递归
    public static String reverse(String s) {
        if(s==null || s.length()<=1) {
            return s;
        }

        int start = 0;
        int end = s.length()-1;
        return helper(s, start, end);
    }

    public static String helper(String s, int start, int end) {
        if(start>=end) {
            return s.substring(start);
        }
        String part1 = s.substring(start,start+1);
        String part2 = helper(s, start+1, end);
        return part2+part1;
    }

}
