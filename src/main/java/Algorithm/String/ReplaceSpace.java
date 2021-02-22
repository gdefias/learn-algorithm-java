package Questions.String;
/**
 * Created by Defias on 2017/10/15.
 *
 * 替换空格
 *
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 *
 * 实现一个函数，把字符串中的每个空格替换成"%20"
 * 如：输入"We are happy." -> "We%20are%20happy."
 *
 * 0 <= s 的长度 <= 10000
 */

public class ReplaceSpace {
    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace(s));
    }

    public static String replaceSpace(String s) {
        if(s==null || s.length() == 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            char a = s.charAt(i);
            if(a == ' ') {
                sb.append("%20");
            } else {
                sb.append(a);
            }
        }

        return sb.toString();
    }
}
