package Questions.String;

/**
 * Created by Defias on 2017/10/7.
 *
 * 字符串字母大小写转换
 */
public class LowerToUpper {

    public static void main(String[] args) {
        char c = 'h';
        System.out.println(lowercaseToUppercase(c));

        String s = "sdfSDSDF@%$VXSxcvdsfaDVZSV";
        System.out.println(lowercaseToUppercase2(s));
    }

    //将一个字符由小写字母转换为大写字母
    public static char lowercaseToUppercase(char character) {
        int i = 'a'-'A';
        return (char)(character - i);
    }

    //将一个字符串中的小写字母转换为大写字母。忽略其他不是字母的字符
    public static String lowercaseToUppercase2(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int index = 0; index < sb.length(); index++) {
            char c = sb.charAt(index);
            if (Character.isLowerCase(c)) {
                sb.setCharAt(index, Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }
}
