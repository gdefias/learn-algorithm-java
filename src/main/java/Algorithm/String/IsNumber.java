package Questions.String;

/**
 * Created by Defias on 2020/06.
 * Description: 表示数值的字符串

 https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/

 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"0123"都表示数值，
 但"12e"、"1a3.14"、"1.2.3"、"+-5"、"-1E-16"及"12e+5.4"都不是。

 */
public class IsNumber {

    public static void main(String[] args) {
        IsNumber O = new IsNumber();
        String s = "    .12e23          ";
        if (O.isNumber(s)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }


    //方法1：暴力法
    public boolean isNumber(String s) {
        if(s==null || s.length()==0) {
            return false;
        }

        char[] cs = s.toCharArray();
        int start=0;

        boolean isE = false;
        boolean isD = false;
        boolean isEnterNum = false;

        char cur = cs[start];
        while((cur==' ')) {
            start++;
            if(start>=cs.length) {
                return false;
            }
            cur = cs[start];
        }

        if(cur=='+' || cur=='-') {
            start++;
            if(start>=cs.length) {
                return false;
            }
            cur = cs[start];
        }

        if(cur=='e') {
            return false;
        }

        while((cur=='.') || (cur=='e') || (isNumChar(cur))) {
            if(cur=='.') {
                if(isD || isE) {
                    return false;
                }
                isD = true;
                start++;
                if(start>=cs.length) {
                    if(!isEnterNum) {
                        return false;
                    } else {
                        return true;
                    }
                }
                cur = cs[start];
                if(cur==' ') {
                    if(isEnterNum) {
                        break;
                    } else {
                        return false;
                    }
                }

                if(cur=='e') {
                    if(isEnterNum) {
                        continue;
                    } else {
                        return false;
                    }
                }

                if(!isNumChar(cur)) {
                    return false;
                }
            }

            if(cur=='e') {
                if(isE) {
                    return false;
                }
                isE = true;

                start++;
                if(start>=cs.length) {
                    return false;
                }
                cur = cs[start];

                if(cur=='+' || cur=='-') {
                    start++;
                    if(start>=cs.length) {
                        return false;
                    }
                    cur = cs[start];
                }
                if(!isNumChar(cur)) {
                    return false;
                }
            }

            if(isNumChar(cur)) {
                isEnterNum = true;
                start++;
                if(start>=cs.length) {
                    return true;
                }
                cur = cs[start];
            }
        }

        while(cur==' ') {
            start++;
            if(start>=cs.length) {
                return true;
            }
            cur = cs[start];
        }

        return false;
    }

    public boolean isNumChar(char c) {
        return c >= '0' && c <= '9';
    }


    //方法2：有限状态自动机
}
