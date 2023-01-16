package Algorithm.String;

import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description:  翻转单词顺序

 https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/

 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变，标点符号当成普通字母一样处理。
 例如输入字符串"I am a student."，则输出"student. a am I"

 输入: "the sky is blue"
 输出: "blue is sky the"

 无空格字符构成一个单词。
 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
public class ReverseWords {
    public static void main(String[] args) {
        ReverseWords O = new ReverseWords();
        String s = "I am a student.";
        System.out.println(O.reverseWords3(s));
    }

    //方法1：双指针 标示句子中的单词边界，从后向前逐个单词进行反转
    public String reverseWords(String s) {
        if(s==null) {
            return s;
        }

        StringBuilder res = new StringBuilder();
        int i = s.length()-1;
        int j = s.length()-1;

        while(i>=0) {
            while(i>=0 && s.charAt(i)==' ') {
                i--;
            }
            j = i;

            while(i>=0 && s.charAt(i)!=' ') {
                i--;
            }
            res.append(s.substring(i+1, j+1) + " ");
        }

        return res.toString().trim();
    }

    //方法2：使用语言特性
    public String reverseWords2(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();

        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);


    }

    //方法3：栈 或 双端队列
    public String reverseWords3(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

//        Stack<String> stack = new Stack<String>();
        Deque<String> deque = new ArrayDeque<String>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
//                stack.push(word.toString());  //栈
                deque.offerFirst(word.toString());  //双端队列
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
//        stack.push(word.toString());
        deque.offerFirst(word.toString());

//        word.setLength(0);
//        while(!stack.isEmpty()) {
//            word.append(stack.pop() + " ");
//        }
//        return word.toString().substring(0, word.length()-1);
        return String.join(" ", deque);
    }


}
