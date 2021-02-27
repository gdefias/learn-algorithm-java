package Algorithm.Bit;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-11
 *
 * 有1千万个随机数，随机数的范围在1到1亿之间。现在要求写出一种算法，将1到1亿之间没有在随机数中的数求出来
 */
public class FindRandom {

    public static void main(String[] args) {
        Sloution();
    }

    public static void Sloution() {
        Random random = new Random();

        List<Integer> list = new ArrayList<>();
        for(int i=0; i<10000000; i++) {
            list.add(random.nextInt(100000000));
        }

       // System.out.println("产生的随机数有");
       // for(int i=0; i<list.size(); i++) {
       //     System.out.println(list.get(i));
       // }

        //List存入BitSet
        BitSet bitSet = new BitSet(100000000);
        for(int i=0; i<10000000; i++) {
            bitSet.set(list.get(i));
        }

        System.out.println("0~1亿不在上述随机数中有");
        for (int i = 0; i < 100; i++) {
            if(!bitSet.get(i)) {
                System.out.println(i);
            }
        }
    }
}
