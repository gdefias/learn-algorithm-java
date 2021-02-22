package Questions.String;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-10

 子字符串查找（字符串匹配）


 对于一个给定的source字符串（长度为N）和一个target字符串（长度为M），在source字符串中找出target字符串出现的第一个位置(从0开始)
 如果不存在，则返回-1

 时间复杂度： O(n*m)   其中n和m分别为主串和模式串的长度
 */

public class Search {

    public static void main(String[] args) {
        String s = "sdfSDSDF@%$VXSxcvdsfaDVZSV";
        String t = "faDVZ";
        //Strings t = "sdfSDSDF@%$VXSxcvdsfaDVZSV";
        System.out.println(strStr(s, t));
        System.out.println(strStr2(s, t));
    }

    public static int strStr(String source, String target) {
        if(source==null || target==null) {
            return -1;
        }

        for(int i=0; i<=source.length()-target.length(); i++) {
            int j;
            for(j=0; j<target.length(); j++) {
                if(source.charAt(j+i) != target.charAt(j)) {
                    break;
                }
            }
            if(j==target.length()) {
                return i;
            }
        }
        return -1;
    }

    public static int strStr2(String source, String target) {
        int n = source.length();
        int m = target.length();

        int i = 0;
        int j = 0;
        while(i<n && j<m) {
            if(source.charAt(i) == target.charAt(j)) {
                i++;
                j++;
            } else {
                i = i-j+1;
                j = 0;
            }
        }

        if(j==m) {
            return i-j;
        } else {
            return -1;
        }
    }


}
