package Questions.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Defias on 2020/07.
 * Description: 子集 II

 https://leetcode-cn.com/problems/subsets-ii/

 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 说明：解集不能包含重复的子集。

 输入: [1,2,2]
 输出:
 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]

 去重：同一树层上不允许重复取，同一树枝上可以重复取
 */
public class SubSets2 {
    public static void main(String[] args) {
        int[] array = {1,2,2};
        //int[] array = {5,5,5,1,5};
        List<List<Integer>> res = subsetsWithDup(array);
        System.out.println(res);
    }

    //方法1：回溯
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0)
            return result;

        List<Integer> path = new ArrayList<>();
        int start = 0;
        boolean[] visited = new boolean[nums.length];

        Arrays.sort(nums);  //排序
        dfs(nums, result, path, start, visited);

        return result;
    }

    public static void dfs(int[] nums, List<List<Integer>> result, List<Integer> path, int start, boolean[] visited) {
        result.add(new ArrayList<>(path));
        //if(start == nums.length) {     //终止条件可以不加，因为下一层不满足for的循环条件还是退出了
        //    return;
        //}
        for (int i=start; i<nums.length; i++) {
            if(i>start && nums[i]==nums[i-1] && !visited[i]) {
                continue;
            }

            visited[i]=true;
            path.add(nums[i]);

            dfs(nums, result, path, i+1, visited);

            visited[i]=false;
            path.remove(path.size()-1);
        }
    }

    //方法2：类似递归
    public static List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());  //先加入一项空集合

        Arrays.sort(nums);

        int lastsize = 1;

        //在只有一项空集合的基础上逐步增加项
        for(int i=0; i<nums.length; i++) {
            int size = res.size();
            if(i>0 && nums[i]!=nums[i-1]) {
                lastsize = size;
            }
            for(int j=size-lastsize; j<size; j++) {
                List<Integer> tmplist = new ArrayList<Integer>(res.get(j));
                tmplist.add(nums[i]);
                res.add(tmplist);
            }
        }
        return res;
    }
}
