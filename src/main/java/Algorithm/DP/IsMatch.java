package Questions.DP;

/**
 * Created by Defias on 2020/07.
 * Description: 正则表达式匹配 (hard)

 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，
 匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。

 输入:
 s = "aa"
 p = "a*"
 输出: true
 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

 输入:
 s = "aa"
 p = "a"
 输出: false
 解释: "a" 无法匹配 "aa" 整个字符串。
 */


public class IsMatch {
    public static void main(String[] args) {
        IsMatch O = new IsMatch();
        String s = "aaa";
        String p = "ab*ac*a";
        System.out.println(O.isMatch(s, p));
    }

    public boolean isMatch(String s, String p) {
        if(s==null || p==null) {
            return false;
        }
        int slen = s.length();
        int plen = p.length();

        boolean[][] dp = new boolean[slen+1][plen+1];
        dp[0][0] = true;

        for(int j=1; j<=plen; j++) {
            if( p.charAt(j-1)=='*' && dp[0][j-2]) {
                dp[0][j] = true;
            }
        }

        for(int i=1; i<=slen; i++) {
            for (int j=1; j<=plen; j++) {
                char sc = s.charAt(i-1);
                char pc = p.charAt(j-1);

                if(pc == sc) {
                    dp[i][j] = dp[i-1][j-1];
                } else  {
                    if(pc=='.') {
                        dp[i][j] = dp[i-1][j-1];
                    } else if(pc=='*') {
                        if(j-2>=0) {
                            if(p.charAt(j-2)==sc || p.charAt(j-2)=='.') {
                                dp[i][j] = dp[i-1][j] || dp[i][j-1];  //取多个字符 || 取1个字符
                            }
                            dp[i][j] = dp[i][j] || dp[i][j-2];  // || 取0个字符
                            //例子：s:aab,p:aabb*,虽然j-2和i-1相等，但是dp[i][j-2]已经匹配了，直接删去j-1和j-2即可
                        }
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[slen][plen];
    }
}
