package Questions.String;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * Description: 第一个只出现一次的字符
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/

 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s只包含小写字母。
 s = "abaccdeff"
 返回 "b"

 s = ""
 返回 " "

 0 <= s 的长度 <= 50000
 */
public class FirstUniqChar {

    public static void main(String[] args) {
        String s = "abaccbdebff";
        System.out.println(firstUniqChar(s));
    }

    //方法1：使用哈希表
    public static char firstUniqChar(String s) {
        HashMap<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();

        for(char c: sc) {
            dic.put(c, !dic.containsValue(c));
        }

        for(char c: sc) {
            if(dic.get(c)) {
                return c;
            }
        }

        return ' ';
    }

    //方法2：使用有序哈希表（有序哈希表中的键值对是 按照插入顺序排序的）
    public static char firstUniqChar2(String s) {
        Map<Character, Boolean> map = new LinkedHashMap<>();
        char[] sc = s.toCharArray();

        for(int i=0; i<sc.length; i++) {
            map.put(sc[i], !map.containsKey(sc[i]));
        }

        for(Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if(entry.getValue()) {
                return entry.getKey();
            }
        }

        return ' ';
    }

    //方法3：使用数组作为哈希表记录次数
    public static char firstUniqChar3(String p) {
        int[] A = new int[256];
        for(int i=0; i<p.length(); i++) {
            A[p.charAt(i)]++;
        }

        for(int i=0; i<p.length(); i++) {
            int c = p.charAt(i);
            if(A[c]==1) {
                return (char)c;
            }
        }
        return '\0';
    }
}
