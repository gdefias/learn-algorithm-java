package Algorithm.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/**
 * Created by Defias on 2017/10/7.

 和为零的子数组
 给定一个整数数组，找到和为零的子数组

 */

public class SubArraySum0 {
    public static void main(String[] args) {
        int[] nums = new int[] {-3, 1, 2, -3, 4};
        ArrayList<Integer> result = subarraySum(nums);
        System.out.println(result);
    }

    public static ArrayList<Integer> subarraySum(int[] nums) {
        int len = nums.length;

        ArrayList<Integer> ans = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];

            if (map.containsKey(sum)) {
                ans.add(map.get(sum) + 1);
                ans.add(i);
                return ans;
            }
            map.put(sum, i);
        }
        return ans;
    }
}
