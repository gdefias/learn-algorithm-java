package Algorithm.String;
/**
 * Created with IntelliJ IDEA.
 * Description: 反转字符串
 * User: Defias
 * Date: 2018-10

  https://leetcode-cn.com/problems/reverse-string/

 */
public class ReverseString {
    public static void main(String[] args) {
        ReverseString O = new ReverseString();
        String s = "abcdefghcb";
        char[] sc = s.toCharArray();
        O.reverseString(sc);

        System.out.print(new String(sc));
    }

    //方法1：双指针
    public void reverseString(char[] s) {
        int p = 0;
        int q = s.length-1;

        while(p < q) {
            swap(s, p, q);
            p++;
            q--;
        }
    }

    public void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}
