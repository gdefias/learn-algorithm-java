package Algorithm.DP;

/**
 * Created by Defias on 2020/07.
 * Description: 正则表达式匹配 (hard)

    https://leetcode.cn/problems/regular-expression-matching/

     请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，
     匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。

     输入:
     s = "aa"
     p = "a*"
     输出: true
     解释:因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

     输入:
     s = "aa"
     p = "a"
     输出: false
     解释: "a" 无法匹配 "aa" 整个字符串。
 */


public class IsMatch {
    public static void main(String[] args) {
        IsMatch O = new IsMatch();
        String s = "aa";
        String p = "a*";
        System.out.println(O.isMatch(s, p));
    }

    /**
     * 方法1：DP
     * 思路：f[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配
     *
     * 考虑p的第j个字符与s的第i个字符的匹配情况
     * 1）如果 p 的第 j 个字符是一个小写字母，那么我们必须在 s 中匹配一个相同的小写字母
     * 若s[i]=p[j] 则：f[i][j] = f[i−1][j−1],
     * 若s[i]!=p[j] 则：f[i][j] = false
     *
     * 2）如果 p 的第 j 个字符是 .，那么 p[j] 一定成功匹配 s 中的任意一个小写字母
     *
     * 3）如果 p 的第 j 个字符是 *，那么就表示我们可以对 p 的第 j−1 个字符匹配任意自然数次
     * 在匹配 0 次的情况下有 f[i][j] = f[i][j−2]
     *
     * 字母 + 星号的组合在匹配的过程中，本质上只会有两种情况：
     * 匹配 s 末尾的一个字符，将该字符扔掉，而该组合还可以继续进行匹配；（匹配上也可以只匹配0次，匹配0次也就是将字母+星号组合扔掉不再匹配）
     * 不匹配字符，将该组合扔掉，不再进行匹配。
     *
     * 若s[i]=p[j−1] 则：f[i][j] = f[i−1][j] or f[i][j−2]
     * 若s[i]!=p[j−1] 则：f[i][j] = f[i][j−2]
     *
     * 时间复杂度：O(mn)，其中 m 和 n 分别是字符串 s 和 p 的长度。我们需要计算出所有的状态，并且每个状态在进行转移时的时间复杂度为O(1)。
     * 空间复杂度：O(mn)，即为存储所有状态使用的空间
     * */

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;  //f[0][0]代表s和p均为空字符串，f[1][1]代表s和p的第一个字符（即在s和p中下标为0的字符）


        //递推
        for(int i=0; i<=m; ++i) {
            for(int j=1; j<=n; ++j) {
                if (p.charAt(j-1) == '*') {  //p的第j个字符为*  第1个字符不可能是*，所以下面j-2并不会越界
                    if (i>0 && matches(s.charAt(i-1), p.charAt(j-2))) { //匹配s的第i个字符和p的第j-1个字符
                        f[i][j] = f[i-1][j] || f[i][j-2]; //p中*前面的字符在s中出现多次或者在s中只出现1次
                    } else {
                        f[i][j] = f[i][j-2];   //p中*前面的在s中字符出现0次
                    }
                } else {
                    if(i>0 && matches(s.charAt(i-1), p.charAt(j-1))) {
                        f[i][j] = f[i-1][j-1];
                    }
                }
            }
        }
        return f[m][n];
    }

    //matches(x,y)判断两个字符是否匹配 只有当y是. 或者 x和y本身相同时，这两个字符才会匹配
    public boolean matches(char x, char y) {
        if (y == '.') {
            return true;
        }
        return y == x;
    }
}
