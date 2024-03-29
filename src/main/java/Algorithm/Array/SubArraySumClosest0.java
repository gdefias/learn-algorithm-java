package Algorithm.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
/**
 * Created by Defias on 2017/10/7.

 最接近零的子数组和

 给定一个整数数组，找到一个和最接近于零的子数组，返回第一个和最右一个指数
 你的代码应该返回满足要求的子数组的起始位置和结束位置
 */

public class SubArraySumClosest0 {

    public static void main(String[] args) {
        int[] A = new int[] {1,2,3,-1,-2, 1, 2, -4,-5,1,5,2,-9};

        int[] res = subarraySumClosest(A);
        System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
    }

    static class Pair {
        int sum;
        int index;
        public Pair(int s, int i) {
            sum = s;
            index = i;
        }
    }

    public static int[] subarraySumClosest(int[] nums) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }

        int len = nums.length;
        if(len == 1) {
            res[0] = res[1] = 0;
            return res;
        }
        Pair[] sums = new Pair[len+1];
        int prev = 0;
        sums[0] = new Pair(0, 0);
        for (int i = 1; i <= len; i++) {
            sums[i] = new Pair(prev + nums[i-1], i);
            prev = sums[i].sum;
        }
        Arrays.sort(sums, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.sum - b.sum;
            }
        });

        for(int k=0; k<sums.length; k++) {
            System.out.println("sum:" + sums[k].sum + ", index:" + sums[k].index);
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) {
            if (ans > sums[i].sum - sums[i-1].sum) {
                ans = sums[i].sum - sums[i-1].sum;
                int[] temp = new int[]{sums[i].index - 1, sums[i-1].index - 1};
                Arrays.sort(temp);
                res[0] = temp[0] + 1;
                res[1] = temp[1];
            }
        }
        return res;
    }
}
