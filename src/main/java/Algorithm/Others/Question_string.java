package Algorithm.Others;

/**
 * Created by Jeff on 2016/6/5.
 *
 * 编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节截取的字符串。但是要保证汉字不被截半个，如"我ABC"4，
 * 应该截为"我AB"，输入"我ABC汉DEF"，6，应该输出为"我ABC"而不是"我ABC+汉的半个"。
 *
 */

import java.io.*;

public class Question_string {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String orignal = "我ABC汉DEF";
        int n = 6;
        System.out.println(solution(orignal, n));

    }

    //判断一个字符是否中文字符
    public static boolean isChineseChar(char c) throws UnsupportedEncodingException {
        return String.valueOf(c).getBytes("GBK").length > 1;
    }

    //解决方案
    public static String solution(String orignal, int n) throws UnsupportedEncodingException {
        String result = "";
        if(orignal==null || orignal.length()==0 || n<=0) {
            return result;
        }
        int orgbklen = orignal.getBytes("GBK").length;
        if(n>=orgbklen) {
            return orignal;
        }

        StringBuffer resbuff = new StringBuffer();
        char c;
        int byi = 0; //已遍历的字节
        for(int i=0; i<n; i++) {
            c = orignal.charAt(i);
            if(isChineseChar(c)) {
                byi = byi + 2;
            } else {
                byi = byi + 1;
            }

            if(byi<=n) {
                resbuff.append(c);
            }
        }

        result = resbuff.toString();
        return result;
    }

}
