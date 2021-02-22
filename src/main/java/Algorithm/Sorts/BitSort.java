package Questions.Sorts;

/**
 * Created by Defias on 2017/11/5.
 *
 * BitSortTest
 * 位图排序（算法珠玑开篇的例子）
 */


public class BitSort {
    private static final int BITSPERWORD = 32; // 整数位数
    private static final int SHIFT = 5;
    private static final int MASK = 0x1F; // 5位遮蔽 0B11111
    private static final int N = 10000000;
    //用int数组来模拟位数组，总计(1 + N / BITSPERWORD)*BITSPERWORD位，足以容纳N
    private static int[] a = new int[(1 + N / BITSPERWORD)];

    public static void main(String[] args) {
        bitsort(new int[] { 1, 100, 2, 10000, 9999, 4567, 78902 });
    }

    public static void bitsort(int[] array) {
        for (int i = 0; i < N; i++)
            clr(i); //位数组所有位清0
        for (int i = 0; i < array.length; i++)
            set(array[i]); //阶段2
        for (int i = 0; i < N; i++)
            if (test(i))
                System.out.println(i);
    }

    //置a[i>>SHIFT]的第(i & MASK)位为1，也就是位数组的第i位为1
    public static void set(int i) {
        a[i >> SHIFT] |= (1 << (i & MASK));
    }

    //置a[i>>SHIFT]的第(i & MASK)位为0,也就是位数组的第i位为0
    public static void clr(int i) {
        a[i >> SHIFT] &= ~(1 << (i & MASK));
    }

    //测试位数组的第i位是否为1
    public static boolean test(int i) {
        return (a[i >> SHIFT] & (1 << (i & MASK))) == (1 << (i & MASK));
    }
}
