package Algorithm.BackTracking.Base;
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

 思路
 https://leetcode-cn.com/problems/permutations-ii/solution/47-quan-pai-lie-iiche-di-li-jie-pai-lie-zhong-de-q/
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

        backstrace(nums, res, visited, path);

        return res;
    }

    public static void backstrace(int[] nums, List<List<Integer>> res, boolean[] visited, List<Integer> path) {
        if(path.size()==nums.length) {
            res.add(new ArrayList<>(path));
        }

        for(int i=0; i<nums.length; i++) {
            if(visited[i]) {
                continue;
            }

            /*
            数组去重
            !visited[i-1]： 树层上去重 效率更高
            visited[i-1]： 树枝上去重

            理解：
            nums[i-1]一定比nums[i]先被取值和判断。如果nums[i-1]被取值了，那vis[i-1]会被置1，只有当递归再回退到这一层时再将它置0
            当vis[i-1] == 1时，说明nums[i-1]和nums[i]分别属于两层递归中，也就是我们要用这两个数分别放在数组的两个位置，这时
            不需要去重。但是当vis[i-1] == 0时，说明nums[i-1]和nums[i]属于同一层递归中（只是for循环进入下一轮循环），也就是我们要
            用这两个数放在数组中的同一个位置上，这就是我们要去重的情况
            * */
            if(i>0 && nums[i]==nums[i-1] && !visited[i-1]) {
                System.out.println("i: " + i);
                continue;
            }

            visited[i] = true;
            path.add(nums[i]);
//            System.out.println("递归之前 => " + path);

            backstrace(nums, res, visited, path);

            path.remove(path.size()-1);
            visited[i] = false;
//            System.out.println("递归之后 => " + path);
        }

    }

}
