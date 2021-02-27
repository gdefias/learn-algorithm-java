package Algorithm.BackTracking;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Created by Defias on 2020/07.
 * Description: 子集

 https://leetcode-cn.com/problems/subsets/

 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）
 说明：解集不能包含重复的子集。

 输入: nums = [1,2,3]
 输出:
 [
   [3],
   [1],
   [2],
   [1,2,3],
   [1,3],
   [2,3],
   [1,2],
   []
 ]



 在树形结构中子集问题是要收集所有节点的结果，而组合问题是收集叶子节点的结果

 其实子集也是一种组合问题，因为它的集合是无序的，子集{1,2} 和 子集{2,1}是一样的。那么既然是无序，取过的元素不会重复取，写回溯算法的时候
 ，for就要从start开始，而不是从0开始！
 */
public class SubSets {
    public static void main(String[] args) {
        int[] array={1,2,3};
        List<List<Integer>> res = subsets(array);
        System.out.println(res);
    }

    //方法1：回溯
    //输出：[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0)
            return result;

        List<Integer> path = new ArrayList<>();
        int start = 0;

        dfs(nums, result, path, start);

        return result;
    }

    public static void dfs(int[] nums, List<List<Integer>> result, List<Integer> path, int start) {
        result.add(new ArrayList<>(path));
        //if(start == nums.length) {     //终止条件可以不加，因为下一层不满足for的循环条件还是退出了
        //    return;
        //}

        for (int i=start; i<nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, result, path, i+1);
            path.remove(path.size()-1);
        }
    }


    //方法2：递归
    //原理：如果A=subset([1,2])，那么：subset([1,2,3])= A + [A[i].add(3) for i = 1..len(A)]
    //输出： [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
    public static List<List<Integer>> subsets2(int[] nums) {
        List<Integer> numslist = Arrays.stream(nums).boxed().collect(Collectors.toList());

        List<List<Integer>> res = new LinkedList<>();
        helper(numslist, res);

        return res;
    }

    public static void helper(List<Integer> numslist, List<List<Integer>> res) {
        if(numslist.isEmpty()) {
            res.add(new ArrayList<>());
            return;
        }

        int lastnum = numslist.remove(numslist.size()-1);

        helper(numslist, res);

        int size = res.size();
        for(int i=0; i<size; i++) {
            List<Integer> tmplist = new ArrayList<Integer>();
            tmplist.addAll(res.get(i));
            tmplist.add(lastnum);
            res.add(tmplist);
        }
        return;
    }


    //方法3：类似递归
    public static List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());  //先加入一项空集合

        //在只有一项空集合的基础上逐步增加项
        for(int i=0; i<nums.length; i++) {
            int size = res.size();
            for(int j=0; j<size; j++) {
                List<Integer> tmplist = new ArrayList<Integer>(res.get(j));
                tmplist.add(nums[i]);
                res.add(tmplist);
            }
        }
        return res;
    }
}
