package Questions.Others;

/**
 * Created by Defias on 2020/07.
 * Description: 构建乘积数组
 *
 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
 不能使用除法。

 输入: [1,2,3,4,5]
 输出: [120,60,40,30,24]

 所有元素乘积之和不会溢出 32 位整数
 a.length <= 100000
 */
public class ConstructArr {

    public int[] constructArr(int[] a) {
        if(a==null || a.length==0) {
            return a;
        }

        int[] b = new int[a.length];
        b[0]=1;

        for(int i=0; i<a.length-1; i++) {
            b[i+1] = b[i] * a[i];
        }

        int tmp = 1;
        for(int j=a.length-1; j>=1; j--) {
            tmp *= a[j];
            b[j-1] = b[j-1] * tmp;
        }

        return b;
    }
}
