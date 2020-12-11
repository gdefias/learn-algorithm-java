package Questions.Stack;

import java.util.Stack;

/**
 * Created by Defias on 2020/07.
 * Description: 有效的括号

 https://leetcode-cn.com/problems/valid-parentheses/

 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：
 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。

 输入: "()[]{}"
 输出: true

 输入: "([)]"
 输出: false

 输入: "{[]}"
 输出: true
 */

public class IsValid {

    public static boolean isValid(String s) {
        if(s==null || s.length()==0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)=='(' || s.charAt(i)=='{' || s.charAt(i)=='[') {
                stack.push(s.charAt(i));
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                char c = stack.pop();
                if(c=='(' && s.charAt(i)!=')') {
                    return false;
                }

                if(c=='{' && s.charAt(i)!='}') {
                    return false;
                }

                if(c=='[' && s.charAt(i)!=']') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
