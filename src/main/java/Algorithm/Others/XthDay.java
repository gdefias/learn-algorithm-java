package Algorithm.Others;

/**
 * @author: Defias
 * @date: 2021/2/22
 * @description: 给定一个时间，如20210601，算出这个时间是今年/当年的第几天
 */
public class XthDay {
    public static void main(String[] args) {
        String inputday = "20200301";
        System.out.println(getXthday(inputday));
    }

    public static int getXthday(String s) {
        int year = Integer.parseInt(s.substring(0,4));
        boolean isadd = (year%100 == 0)?(year%400 == 0):(year%4==0);

        int month = Integer.parseInt(s.substring(4,6));
        int day = Integer.parseInt(s.substring(6));
        int n = 0;

        for(int i=1; i<month; i++) {
            if(i==2) {
                n += (isadd?29:28);
            } else {
                if(i%2==0) {
                    n += 30;
                } else {
                    n += 31;
                }
            }
        }

        n += day;
        return n;
    }
}
