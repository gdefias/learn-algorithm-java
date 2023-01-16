package Algorithm.String;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Felix
 * @date: 2022/12/28
 * @description:  字符串中的第一个唯一字符
 *
 * https://leetcode.cn/problems/first-unique-character-in-a-string/description/
 */
public class FirstUniqChar2 {

    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
    }


    //使用哈希表
    public static int firstUniqChar(String s) {
        int len = s.length();
        int index = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        while(index < len) {
            Character c = s.charAt(index);
            map.put(c, map.getOrDefault(c, 0)+1);
            index++;
        }

        index = 0;
        while(index < len) {
            Character c = s.charAt(index);
            if(map.get(c) == 1) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
