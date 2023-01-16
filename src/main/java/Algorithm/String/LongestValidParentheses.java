package Algorithm.String;

import java.util.Stack;

/**
 * @author: Felix
 * @date: 2022/12/28
 * @description: 最长有效括号

  https://leetcode.cn/problems/longest-valid-parentheses/description/

  栈题解
  https://leetcode.cn/problems/longest-valid-parentheses/solutions/922407/zhan-zui-jian-jie-yi-dong-de-dai-ma-cjav-xa7v/?q=%E6%A0%88&orderBy=most_relevant

 */
public class LongestValidParentheses {
    public static void main(String[] args) {
//        String s = "(()))((()((())";
        String s = "(()()";
//        String s = "(()()()(()))()";
        System.out.println(longestValidParentheses(s));
    }

    //方法1: 栈
    //思路: 用栈去判断序列的合法性。遍历整个字符串s，把所有的合法括号序列按照右括号来分类，对于每一个右括号，都去求一下以这个右括号
    //为右端点的最长的合法括号序列的左端点在什么位置。我们把每个右括号都枚举一遍之后，再取一个max，就是整个的最大长度
    public static int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<Integer>();
        int ans = 0;

        //start记录一个新的可能合法的子串的起始位置，初始设为0
        for(int i=0 ,start=0;i<s.length(); i++) {
            if( s.charAt(i) == '(') { //左括号时入栈
                st.add(i);  //栈里存的是下标
            } else {  //右括号时出栈 表示匹配上了左括号
                if(!st.isEmpty()) {
                    st.pop();
                    if(st.isEmpty()) {  //出栈后栈为空说明以当前右括号为右端点的合法括号序列的左端点为start 记一次最大长度
                        ans = Math.max(ans, i-start+1);
                    } else {
                        ans = Math.max(ans,i-(st.peek()+1)+1);  //出栈后栈不为空，说明以当前右括号为右端点的合法括号序列的左端点为当前栈顶元素的下一个元素(之前已经出栈了，下标为st.peek()+1)
                    }
                } else {
                    start = i + 1;  //遇到右括号)且当前栈为空，则当前的start开始的子串不再可能为合法子串了，下一个合法子串的起始位置可能是i+1
                }
            }
        }
        return ans;
    }

    //方法2: 贪心 不需要额外的空间
    public static int longestValidParentheses2(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;  //(括号数量
            } else {
                right++;  //)括号数量
            }

            if (left == right) {  //相等时为有效串  记一次最大长度
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {  //)括号数量超过(括号数量  无法继续构成有效串
                left = right = 0;
            }  //如果(括号数量超过)括号数量 则要继续往后看
        }

        //以上会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (() 这种时候最长有效括号是求不出来的
        //解决方法是从右往左遍历用类似的方法计算
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {  //从右往左时  （括号数量超过）括号数量  无法继续构成有效串
                left = right = 0;
            }
        }

        return maxlength;
    }

    //动态规划




}
