package Questions.Bit;
import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * Description: 位向量
 * User: Defias
 * Date: 2018-11
 *
 *
 * 使用位逻辑运算(如与、或、移位)来实现位向量
 */
public class BitVetory {
    private int n;  //位数
    private int[] bitArray;  //int数组
    private static final int BIT_LENGTH = 32;  //默认使用int类型
    private static int P;  //第几个int
    private static int Q;  //int中第几位

    /**
     * 初始化位向量
     * @param n
     */
    public BitVetory(int n) {
        this.n = n;
        bitArray = new int[(n-1)/BIT_LENGTH + 1];
        init();
    }

    /**
     * 初始化操作
     */
    public void init(){
        for (int i = 0; i < n; i++) {
            clr(i);
        }
    }

    /**
     * 获取排序后的数组
     * @return
     */
    public List<Integer> getSortedArray(){
        List<Integer> sortedArray = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (get(i) == 1) {
                sortedArray.add(i);
            }
        }
        return sortedArray;
    }
    /**
     * 置位操作
     * @param i
     */
    public void set(int i){
        P = i / BIT_LENGTH;
        Q = i % BIT_LENGTH;

        bitArray[P] |= 1 << Q;
    }

    /**
     * 置零操作
     * @param i
     */
    public void clr(int i){
        P = i / BIT_LENGTH;
        Q = i % BIT_LENGTH;

        bitArray[P] &= ~(1 << Q);
    }

    /**
     * 读取操作
     * @param i
     * @return
     */
    public int get(int i){
        P = i / BIT_LENGTH;
        Q = i % BIT_LENGTH;

        return Integer.bitCount(bitArray[P] & (1 << Q));
    }
}
