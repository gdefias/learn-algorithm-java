package Questions.Bit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created with IntelliJ IDEA.
 * Description: 位向量
 * User: Defias
 * Date: 2018-11
 *
 * 有一组互异且小于N的正整数，使用位向量来进行升序排列
 */
public class BitVtoryTest {
    public static void main(String[] args) {
        int amount = 15;
        List<Integer> randoms = getRandoms(amount);
        System.out.println("排序前数组：");

        BitVetory bitVetory = new BitVetory(amount);
        for (Integer e : randoms) {
            System.out.print(e+",");
            bitVetory.set(e);
        }

        List<Integer> sortedArray = bitVetory.getSortedArray();
        System.out.println();
        System.out.println("排序后数组：");
        for (Integer e : sortedArray) {
            System.out.print(e+",");
        }
    }

    private static List<Integer> getRandoms(int amount) {
        Random random = new Random();

        List<Integer> randoms = new ArrayList<>();
        while(randoms.size() < (amount - 1)){
            int element = random.nextInt(amount - 1) + 1;//element ∈  [1,amount)
            if (!randoms.contains(element)) {
                randoms.add(element);
            }
        }

        return randoms;
    }
}
