package Algorithm.String;

/**
 * Created with IntelliJ IDEA.
 * Description:  旋转字符串
 * User: Defias
 * Date: 2018-10

   https://leetcode.cn/problems/rotate-string/

 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。

 输入: s = "abcde", goal = "cdeab"
 输出: true

 输入: s = "abcde", goal = "abced"
 输出: false

 提示:
 1 <= s.length, goal.length <= 100
 s 和 goal 由小写英文字母组成

 */
public class IsRotation {

    public static void main(String[] args) {
        IsRotation O = new IsRotation();
        String s = "bbbacddceeb";
        String goal = "ceebbbbacdd";
        System.out.println(O.rotateString(s, goal));
    }


    //方法1：模拟   s分别旋转0~s.length()-1位
    public boolean rotateString(String s, String goal) {
        if(s==null || goal==null || s.length()!=goal.length()) {
            return false;
        }

        int len = s.length();
        int p = 0;
        for(int i=0; i<len; i++) {
            p = 0;
            int j = i;
            for(; j<i+len; j++) {
                if(s.charAt(j%len) != goal.charAt(p++)) {
                    break;
                }
            }

            if(j == i+len) {
                return true;
            }
        }

        return false;

    }


    //方法2：搜索子字符串
    public boolean rotateString2(String s, String goal) {
        if(s==null || goal==null || s.length()!=goal.length()) {
            return false;
        }

        String s2s = goal + goal;
        if(s2s.contains(s)) {
            return true;
        }

        return false;
    }

}
