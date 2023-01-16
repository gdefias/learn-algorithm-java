package Algorithm.Math;

/**
 * Created by Jeff on 2016/5/1.

 方程的根

 给一个方程: ax2 + bx + c = 0. 求根。
    如果方程有两个根，就返回一个包含两个根的数组/列表
    如果方程只有一个根，就返回一个包含一个跟的数组/列表
    如果方程没有根，就返回一个空数组/列表
 *
 */
public class RootOfEquation {
    public static void  main(String[] args) {

    }


    public static double[] rootOfEquation(double a, double b, double c) {
        double[] result;
        double derta;
        double tmp;
        derta = java.lang.Math.pow(b,2) - 4*a*c;
        if(derta==0) {
            result = new double[1];
            result[0] = (-1 * b) / (2 * a);
            return result;
        }
        else if(derta>0) {
            result = new double[2];
            result[0] = ((-1 * b) + java.lang.Math.sqrt(derta)) / (2 * a);
            result[1] = ((-1 * b) - java.lang.Math.sqrt(derta)) / (2 * a);
            if(result[0] > result[1]) {
                tmp = result[0];
                result[0] = result[1];
                result[1] = tmp;
            }
            return result;
        } else {
            result = new double[0];
            return result;
        }
    }



}
