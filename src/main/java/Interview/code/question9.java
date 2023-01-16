package Interview.code;
import java.util.Random;
/**
 * @author: Defias
 * @date: 2021/3/17
 * @description:
 *
 * n个人  m块红包
 */

public class question9 {


    public static double[] slove(int n, double m) {
        double[] res = new double[n];

        double sum = 0;
        for(int i=0; i<n-1; i++) {
            double maxv = m - sum - 0.01*(n-i-1);
            double v = getCore(maxv);
            res[i] = v;
            sum += v;
        }

        res[n-1] = m - sum;
        return res;
    }

    public static double getCore(double k) {
        Random random = new Random();
        while(true) {
            double rom = random.nextDouble()*k;  //0.0~1.0 * k
            if(rom < k) {
                return rom;
            }
        }
    }

}

