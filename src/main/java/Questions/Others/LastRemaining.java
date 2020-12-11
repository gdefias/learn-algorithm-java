package Questions.Others;

/**
 * Created with IntelliJ IDEA.
 * Description: 圆圈中最后剩下的数字
 * User: Defias
 * Date: 2018-10
 *
 0,1,...n-1这n个数字排成一个圆圈⭕️，从数字0开始每次从这个圆圈里删除第m和数字。求出这个圆圈里剩下的最后一个数字
 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。


 输入: n = 5, m = 3
 输出: 3

 输入: n = 10, m = 17
 输出: 2

 1 <= n <= 10^5
 1 <= m <= 10^6
 */
public class LastRemaining {

    public int lastRemaining(int n, int m) {
        int ans = 0;
        for(int i=2; i<=n; i++) {
            ans = (ans+m)%i;
        }
        return ans;
    }
}
