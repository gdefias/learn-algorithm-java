package Algorithm.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Defias
 * @date: 2020/12/18
 * @description:  字母与数字

 https://leetcode-cn.com/problems/find-longest-subarray-lcci/

 给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。

 返回该子数组，若存在多个最长子数组，返回左端点最小的。若不存在这样的数组，返回一个空数组。

 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]

 输入: ["A","A"]
 输出: []


 */
public class FindLongestSubarray {

    public static void main(String[] args) {
        String[] input = new String[] {"A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"};

        System.out.println(Arrays.asList(findLongestSubarray(input)));
    }

    //前缀和 + hashmap
    public static String[] findLongestSubarray(String[] array) {
        int start = 0;
        int end = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(sum, -1);

        for(int i=0; i<array.length; i++) {
            sum += Character.isDigit(array[i].charAt(0)) ? 1 : -1;
            //char curr = array[i].charAt(0);
            //if('0'<=curr && curr<='9') {
            //    sum++;
            //} else {
            //    sum--;
            //}

            if(!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                int index = map.get(sum);
                if((i-index) > (end-start)) {
                    start = index;
                    end = i;
                }
            }
        }

        return Arrays.copyOfRange(array, start+1, end+1);
    }
}
