package Algorithm.String;

/**
 * Created with IntelliJ IDEA.
 * Description:    左旋转字符串
 * User: Defias Date: 2018-10

 https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/

 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义个函数实现字符串左旋操作的功能。
 比如输入字符串"abcdefg"和数字2，该函数将返回左旋转2位得到的结果"cdefgab"

 输入: s = "abcdefg", k = 2 输出: "cdefgab"

 1 <= k < s.length <= 10000
 */
public class ReverseLeftWords {

    public static void main(String[] args) {
        reverseLeftWords3("abcdefg", 4);

    }

    //方法1：字符串切片
    public static void reverseLeftWords(String s, int n) {
        String res = s.substring(n, s.length()) + s.substring(0, n);
        System.out.println(res);
    }


    //方法2：StringBuilder + 逐字符遍历
    public static void reverseLeftWords2(String s, int n) {
        StringBuilder sb = new StringBuilder();

        for(int i=n; i<(s.length()+n); i++) {
            sb.append(s.charAt(i%s.length()));
        }
        String res = sb.toString();
        System.out.println(res);
    }


    //方法3：对称交换3次
    public static void reverseLeftWords3(String s, int n) {
        if(n<0 || n>=s.length()) {
            return;
        }

        char[] sc = s.toCharArray();
        helper(sc, 0, n-1);
        helper(sc, n, sc.length-1);
        helper(sc, 0, sc.length-1);

        String result = new String(sc);
        System.out.println(result);
    }

    public static void helper(char[] s, int start, int end) {
        if(s.length<=1 || start<0 || end>=s.length) {
            return;
        }

        int i = start;
        int j = end;
        while(i<j) {
            exchc(s, i++, j--);
        }
    }

    public static void exchc(char[] A, int left, int right) {
        char temp = A[left];
        A[left] = A[right];
        A[right] = temp;
    }
}
