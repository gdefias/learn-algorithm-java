package Interview.code;

/**
 * @author: Defias
 * @date: 2021/3/3
 * @description:
 *
 * V1.2.3.4
 * V1.2.3
 */
public class question2 {

    public static void main(String[] args) {
        String v1 = "V1.2.3.4";
        String v2 = "V1.2.3";

        System.out.println(compver(v1, v2));
    }

    public static int compver(String v1, String v2) {

        String[] vs1 = v1.substring(1).split("\\.");
        String[] vs2 = v2.substring(1).split("\\.");
        System.out.println(vs1.length);
        System.out.println(vs2.length);

        int i = 0;
        int j = 0;
        while(i<vs1.length && j<vs2.length) {
            if(Integer.valueOf(vs1[i]) == Integer.valueOf(vs2[j])) {
                i++;
                j++;
            }

            if(Integer.valueOf(vs1[i]) > Integer.valueOf(vs2[j])) {
                return 1;
            } else {
                return -1;
            }
        }

        if(i==vs1.length && j==vs2.length) {
            return 0;
        }

        if(i<vs1.length) {
            return 1;
        } else {
            return -1;
        }
    }

}
