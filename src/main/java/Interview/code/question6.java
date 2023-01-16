package Interview.code;

/**
 * @author: Defias
 * @date: 2021/3/11
 * @description:
 */
public class question6 {

    public static void main(String[] args) {
        System.out.println(maxDiff(123456));
    }

    public static int maxDiff(int num) {
        int a=0, b=0;
        int len = String.valueOf(num).length();
        int[] numw = new int[len];
        int index = len-1;

        int p = num;
        while(p!=0) {
            numw[index] = p%10;
            index--;
            p = p/10;
        }
        int[] numw2 = numw.clone();

        int i=0;
        for(; i<len; i++) {
            if(numw[i]!=9) {
                break;
            }
        }

        if(i!=len) {
            int target = numw[i];
            for(; i<len; i++) {
                if(numw[i]==target) {
                    numw[i] = 9;
                }
            }
        }

        for(int j=0; j<len; j++) {
            a = a*10 + numw[j];
        }

        System.out.println(a);

        if(numw2[0]!=1) {
            int target = numw2[0];
            for(i=0; i<len; i++) {
                if(numw2[i]==target) {
                    numw2[i] = 1;
                }
            }
        } else {
            i = 1;
            for(; i<len; i++) {
                if(numw2[i]!=0) {
                    break;
                }
            }

            if(i!=len) {
                int target = numw2[i];
                for(; i<len; i++) {
                    if(numw2[i]==target) {
                        numw2[i] = 0;
                    }
                }
            }
        }

        for(int j=0; j<len; j++) {
            b = b*10 + numw2[j];
        }

        System.out.println(b);

        return a - b;
    }
}
