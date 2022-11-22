package Interview.code1;
import Lib.In;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Defias
 * @date: 2021/3/12
 * @description:
 *
 * int[] A int[] B int[] C
 *
 * a + b + c = n
 *
 */
public class question7 {

    public static int getCount(int[] A, int[] B, int[] C, int n) {
        int[] A1 = getArray(A);
        int[] B1 = getArray(B);
        Set C1 = getSet(C);

        int count = 0;
        for(int i=0; i<A1.length; i++) {
            for(int j=0; j<B1.length; j++) {
                int target = n-A1[i]-B1[j];
                if(C1.contains(target)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int[] getArray(int[] nums) {
        Set set = getSet(nums);

        int[] res = new int[set.size()];
        int index = 0;
        for(Object o: set) {
            res[index++] = (Integer) o;
        }

        return res;
    }

    public static Set getSet(int[] nums) {
        Set set = new HashSet();
        for(int i=0; i<nums.length; i++) {
            set.add(nums[i]);
        }
        return set;
    }

}
