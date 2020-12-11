package Questions.Others;

/**
 * Created by Defias on 2017/2/28.
 *
 * 实例：统计一个文本中单词出现的次数（不区分大小写），然后按照单词的字母顺序显示这些单词以及它们对应的出现次数
 *
 */

import java.util.*;
import java.util.regex.*;

public class TotalCountOfWord {
    public static void main(String[] args) {
        // Set text in a string
        String text = "Good morning. Have a good class. " +
                "Have a good visit. Have fun!";
        System.out.println(replaceBlank(text));

        // Create a TreeMap to hold words as key and count as value
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();

        String[] words = text.split("[ \n\t\r.,;:!?(){}]");  //提取单词
        for (int i = 0; i < words.length; i++) {
            String key = replaceBlank(words[i].toLowerCase());
            System.out.println(key);

            if (words[i].length() > 0) {  //排除空字符串
                if (map.get(key) == null) {
                    map.put(key, 1);
                }
                else {
                    int value = map.get(key).intValue();
                    value++;
                    map.put(key, value);
                }
            }
        }

        // Get all entries into a set
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        // Get key and value from each entry
        for (Map.Entry<String, Integer> entry: entrySet)
            System.out.println(entry.getValue() + "\t" + entry.getKey());
    }

    //去除字符串中的空格、回车、换行符、制表符
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
