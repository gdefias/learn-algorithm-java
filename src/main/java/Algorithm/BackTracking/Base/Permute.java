package Algorithm.BackTracking.Base;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Defias on 2016/6/5

 全排列
 https://leetcode-cn.com/problems/permutations/

 数组全排列
 给定一个 没有重复 数字的序列，返回其所有可能的全排列

 示例：
 输入: [1,2,3]
 输出: [
           [1,2,3],
           [1,3,2],
           [2,1,3],
           [2,3,1],
           [3,1,2],
           [3,2,1]
        ]
 */


public class Permute {

    public static void main(String[] args) {
        int[] array={1,2,3,4};

        List<List<Integer>> res = permute(array);
        System.out.println(res);
    }

    //回溯算法
    //时间复杂度：O(n∗n!)  空间复杂度：O(n)
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();  //存放所有排列结果
        boolean[] visited = new boolean[nums.length];  //标识已经遍历的数的索引

//        List<Integer> path = new LinkedList<>(); //记录已经遍历的数
        Deque<Integer> path = new ArrayDeque<>();  //也可以使用双端队列Deque（相当于栈） 在同一端进和出

        backstrace(nums, res, visited, path);

        return res;
    }

    public static void backstrace(int[] nums, List<List<Integer>> res, boolean[] visited,  Deque<Integer> path) {
        if(path.size()==nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(visited[i]) {
                continue;
            }


            visited[i] = true;
//            path.add(nums[i]);
            path.addLast(nums[i]);
//            System.out.println("递归之前 => " + path);

            backstrace(nums, res, visited, path);

            //回溯算法的精髓  状态重置  代码在形式上和递归之前是对称的
//            path.remove(path.size()-1);
            path.removeLast();
            visited[i] = false;
//            System.out.println("递归之后 => " + path);
        }

    }


    //版本2：对回溯算法的空间优化  节省visited数组
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();  //存放所有排列结果

        List<Integer> path =  new ArrayList<>();
        for(int num: nums) {
            path.add(num);
        }

        int first = 0;  //从左往右填到第first个位置 当first==n时说明已经填完了n个位置
        backstrace2(path, res, 0);
        return res;
    }

    public static void backstrace2(List<Integer> path, List<List<Integer>> res, int first) {
        if(first==path.size()) {  //所有的数都填完了，形成了1个排列，加入结果中
            res.add(new ArrayList<>(path));
            return;
        }

        //已经填到第first个位置，那么nums[]数组中[0,first−1]是已填过的数的集合，[first,n−1] 是待填的数的集合
        for(int i=first; i<path.size(); i++) {
            Collections.swap(path, first, i);

            backstrace2(path, res, first+1);

            Collections.swap(path, first, i);  //交换回来
        }
    }
}
