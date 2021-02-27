package Algorithm.Others;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description: 扑克牌中的顺子
 * User: Defias
 * Date: 2018-10
 *
 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

 输入: [1,2,3,4,5]
 输出: True


 数组长度为 5
 数组的数取值为 [0, 13] .
 */

public class IsStraight {

    public static void main(String[] args) {

    }

    public static boolean isStraight(int[] nums) {
        if(nums==null || nums.length!=5) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        int min = 14;
        int max = -1;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]==0) {
                continue;
            }
            if(nums[i]<min) {
                min = nums[i];
            }

            if(nums[i]>max) {
                max = nums[i];
            }

            if(set.contains(nums[i])) {
                return false;
            }
            set.add(nums[i]);
        }

        return (max-min)<5;

    }



    public static boolean isStraight2(int[] A) {
        if(A==null||A.length!=5) {
            throw new ArithmeticException();
        }

        Arrays.sort(A);

        int numberZero = 0;
        int numberGap = 0;

        for(int i=0; i<A.length; i++) {
            if(A[i]==0)
                numberZero++;
        }

        int small = numberZero;
        int big = small+1;
        while(big<A.length) {
            if(A[big] == A[small]) {
                return false;
            }

            numberGap += A[big]-A[small]-1;
            small = big;
            big++;
        }

        return (numberGap<=numberZero)?true:false;
    }
}
