package Algorithm.String;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-10
 *
 * 判断两个字符串是否互为旋转
 */
public class IsRotation {
    public static void main(String[] args) {
        String s1 = "abcdef";
        String s2 = "defabc";
        System.out.println(isRotation(s1, s2));
    }

    public static boolean isRotation(String s1, String s2) {
        if(s1==null||s2==null||s2.length()!=s2.length()) {
            return false;
        }

        String s2s = s2 + s2;
        if(s2s.contains(s1)) {
            return true;
        }

        return false;
    }


}
