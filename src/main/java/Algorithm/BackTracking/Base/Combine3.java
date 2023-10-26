package Algorithm.BackTracking.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Felix
 * @date: 2023/6/5
 * @description:

数组全组合
给定一个 没有重复 数字的序列，返回其所有可能的组合

示例：
输入: [1,2,3]
输出: [
[1],
[2],
[3],
[1,2],
[1,3],
[2,3],
[1,2,3]
]
 */
public class Combine3 {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        System.out.println(combine(nums));
    }


    //回溯
    public static List<List<Integer>> combine(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();  //存放所有结果

        for(int k=0; k<=nums.length; k++) {
            List<List<Integer>> res = combine(nums, k);
            for(List<Integer> item: res) {
                result.add(item);
            }
        }

        return result;
    }


    public static List<List<Integer>> combine(int[] nums, int k) {
        List<List<Integer>> res = new ArrayList<>();  //存放所有结果
        List<Integer> path = new ArrayList<>();    //存放单个结果

        int start = 0;   //记录本层递归的过程中，集合从哪个索引开始遍历 [0 ~ nums.length-1]

        backtrack(nums, k, start, path, res);

        return res;
    }


    /**
     * 回溯 + 剪枝
     * */
    public static void backtrack(int[] nums, int k, int start, List<Integer> path,  List<List<Integer>> res) {
        if(path.size() == k) {  //满足要求的结果
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<=nums.length-k+path.size(); i++) {  //i<=nums.length-1
            path.add(nums[i]);   //往List的末尾添加

            backtrack(nums, k, i+1, path, res);

            path.remove(path.size()-1);  //从List的末尾移除
        }
    }
}
