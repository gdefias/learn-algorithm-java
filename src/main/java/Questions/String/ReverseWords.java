package Questions.String;

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

    //双指针标示句子中的单词边界，逐个单词进行反转
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
}
