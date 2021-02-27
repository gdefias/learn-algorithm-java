package Algorithm.BackTracking;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description:   组合总和 II

 https://leetcode-cn.com/problems/combination-sum-ii/

 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合
 candidates 中的每个数字在每个组合中只能使用一次

 说明：
 所有数字（包括目标数）都是正整数
 解集不能包含重复的组合


 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 所求解集为:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]

 输入: candidates = [2,5,2,1,2], target = 5,
 所求解集为:
 [
   [1,2,2],
   [5]
 ]
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        int[] candidates = new int[] {10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(combinationSum2(candidates,target));
    }

    //方法1
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int start = 0;
        int sum = 0;
        boolean[] visited = new boolean[candidates.length];

        Arrays.sort(candidates);
        backtrack(candidates, target, start, sum, visited, path, res);

        return  res;
    }

    /**
     * 回溯 + 剪枝
     *
     * 去重：要去重的是“同一树层上的使用过”
     * 「如果candidates[i] == candidates[i - 1] 并且 used[i - 1] == false，就说明：前一个树枝，使用了candidates[i - 1]，也就是
     * 说同一树层使用过candidates[i - 1]」
     *
     * */
    public static void backtrack(int[] candidates, int target, int start, int sum, boolean[] visited,
                                 List<Integer> path, List<List<Integer>> res) {
        if(sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<candidates.length; i++) {
            if(sum+candidates[i] > target) {  //剪枝
                break;
            }

            //去重：要对同一树层使用过的元素进行跳过
            if(i>0 && candidates[i]==candidates[i-1] && !visited[i-1]) {
                continue;
            }

            visited[i] = true;
            sum += candidates[i];
            path.add(candidates[i]);

            backtrack(candidates, target, i+1, sum, visited, path, res);  //这里是i+1，每个数字在每个组合中只能使用一次

            visited[i] = false;
            sum -= candidates[i];
            path.remove(path.size()-1);
        }
    }


    //方法2：与方法1类似，只是没有专门保存sum，而是根据当前sum每次改变target值
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int start = 0;
        boolean[] visited = new boolean[candidates.length];

        Arrays.sort(candidates);
        backtrack2(candidates, target, start, visited, path, res);

        return  res;
    }

    public static void backtrack2(int[] candidates, int target, int start, boolean[] visited,
                                  List<Integer> path, List<List<Integer>> res) {
        if(target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<candidates.length; i++) {
            if(target-candidates[i] < 0) {  //剪枝
                break;
            }

            //去重：要对同一树层使用过的元素进行跳过
            if(i>0 && candidates[i]==candidates[i-1] && !visited[i-1]) {
                continue;
            }

            visited[i] = true;
            path.add(candidates[i]);

            backtrack2(candidates, target-candidates[i], i+1, visited, path, res);

            visited[i] = false;
            path.remove(path.size()-1);
        }
    }
}
