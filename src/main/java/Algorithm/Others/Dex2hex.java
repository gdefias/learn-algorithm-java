package Algorithm.Others;

//十进制转十六进制
public class Dex2hex {

    public static void  main(String argv[]) {
        int n = 32;
        System.out.println("out: " + Dex2hex(n));
    }

    public static String Dex2hex(int n) {
        String result = "";
        int yu;
        char yuc;

        while(n > 0) {
            yu = n % 16;
            yuc = (yu >= 0 && yu <= 9)?(char)(yu+'0'):(char)(yu-10+'A');
            result = yuc + result;
            n = n / 16;
        }
        result = "0X" + result;
        return result;
    }
}
