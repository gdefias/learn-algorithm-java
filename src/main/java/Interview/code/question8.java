package Interview.code;

/**
 * @author: Defias
 * @date: 2021/3/12
 * @description:
 */
public class question8 {
    public static void  main(String[] args) {
        String s = "Aibee Applei";

        /*
        * 测试case：
        * null
        * ""
        * " "【空格】
        * " "【tab】
        * ''
        * "A"
        * "AAAA"
        * "a"
        * "aaaa"
        * "aaaaAAAA"
        * "aaaacAAAA"
        * "abcd"
        * " aabc"
        * " abc ab"
        * " abcab "
        * "Aibee Apple"
        * "+_@#$abcd+_@#$"
        * "qwertyuiopasdfghjklzxcvbnm"
        * "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm"
        *
        * 随机
        * */

        System.out.println(getIndex(s));

    }

    public static int getIndex(String s) {
        int[] code = new int[129];

        char[] cs = s.toCharArray();
        for(char c: cs) {
            code[c]++;
        }

        for(int i=0; i<cs.length; i++) {
            if(code[cs[i]] == 1) {
                return i;
            }
        }

        return -1;
    }
}
