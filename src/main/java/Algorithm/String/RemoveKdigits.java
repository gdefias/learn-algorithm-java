package Algorithm.String;

import java.util.ArrayDeque;

/**
 * @author: Felix
 * @date: 2023/1/3
 * @description: 移掉 K 位数字

  https://leetcode.cn/problems/remove-k-digits/description/

  给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字

    输入：num = "1432219", k = 3
    输出："1219"
    解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。

    输入：num = "10200", k = 1
    输出："200"
    解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。


   拼接最大数 MaxNumber 的前置题

 */
public class RemoveKdigits {
    public static void main(String[] args) {
        RemoveKdigits O = new RemoveKdigits();
        String num = "1432219"; //10200
        int k = 3;
        System.out.println(O.removeKdigits(num, k));
    }


    //方法1: 贪心 + 单调栈
    //思路: 维护栈底到栈顶递增序列
    public String removeKdigits(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }
        int n = num.length();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        stack.push(num.charAt(0));
        for(int i=1; i<n; i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && k>0 && c<stack.peek()) {
                stack.pop();
                k--;  //一共删除k次，出栈后这个字符就丢弃了，记一次删除
            }

            //两个条件都不满足时说明是前导0，直接丢弃这个0字符
            if(!stack.isEmpty() || c!='0') { //if (!(stack.isEmpty() && c == '0')) {
                stack.push(c);
            }
        }

        //删除次数不够k次继续删除   此时的栈一定是顺序（栈底到栈顶递增）
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }

        //栈中剩下的字符就是删完指定次数后最小的（从栈底到栈顶）
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0,stack.pop());
        }

        return sb.length()==0 ? "0" : sb.toString();
    }
}
