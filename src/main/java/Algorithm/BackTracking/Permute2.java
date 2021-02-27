package Algorithm.BackTracking;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description:  数组全排列 II

 https://leetcode-cn.com/problems/permutations-ii/

 给定一个可包含重复数字的序列，返回所有不重复的全排列

 输入: [1,1,2]
 输出:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 */

public class Permute2 {
    public static void main(String[] args) {
        int[] array={1,1,2};
        List<List<Integer>> res = permuteUnique(array);
        System.out.println(res);
    }

    //回溯算法 O(n∗n!)
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new LinkedList<>();
        Arrays.sort(nums);  //排序

        backtrack(nums, res, visited, path);

        return res;
    }

    public static void backtrack(int[] nums, List<List<Integer>> res, boolean[] visited, List<Integer> path) {
        if(path.size()==nums.length) {
            res.add(new ArrayList<>(path));
        }

        for(int i=0; i<nums.length; i++) {
            if(visited[i]) {
                continue;
            }

            //数组去重
            //!visited[i-1]： 树层上去重 效率更高
            //visited[i-1]： 树枝上去重
            if(i>0 && nums[i]==nums[i-1] && !visited[i-1]) {
                System.out.println("i: " + i);
                continue;
            }

            visited[i] = true;
            path.add(nums[i]);

            backtrack(nums, res, visited, path);

            path.remove(path.size()-1);
            visited[i] = false;
        }

    }

}
