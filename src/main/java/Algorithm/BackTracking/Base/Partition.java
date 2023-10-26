package Algorithm.BackTracking.Base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Felix
 * @date: 2023/1/1
 * @description:  分割问题

 输入："abc"
 输出： [[a, b, c], [a, bc], [ab, c], [abc]]
 */

public class Partition {
    public static void main(String[] args) {
        String input = "abc";
        System.out.println(partition(input));
    }


    //回溯
    public static List<List<String>> partition(String input) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new LinkedList<>();
        int start = 0;
//        int len = input.length();
//        if(len<=0) {
//            return res;
//        }

        backstrace(input, start, path, res);

        return res;
    }

    public static void backstrace(String input, int start, List<String> path, List<List<String>> res) {
        if(start == input.length()) {  //输入字符串input使用完了就获得一个结果
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<=input.length()-1; i++) {
            String substr = input.substring(start, i+1);  //input取start到i的子串
            path.add(substr);
//            System.out.println("substr => " + substr);
//            System.out.println("递归之前 => " + path);

            backstrace(input, i+1, path, res);
            path.remove(path.size()-1);
//            System.out.println("递归之后 => " + path);
        }
    }

}
