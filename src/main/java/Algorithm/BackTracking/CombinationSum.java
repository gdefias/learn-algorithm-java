package Questions.BackTracking;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description:  组合总和

 https://leetcode-cn.com/problems/combination-sum/

 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合
 candidates 中的数字可以无限制重复被选取

 说明：
 所有数字（包括 target）都是正整数
 解集不能包含重复的组合

 输入: candidates = [2,3,6,7], target = 7,
 所求解集为:
 [
 [7],
 [2,2,3]
 ]

 输入: candidates = [2,3,5], target = 8,
 所求解集为:
 [
   [2,2,2,2],
   [2,3,3],
   [3,5]
 ]

 1 <= candidates.length <= 30
 1 <= candidates[i] <= 200
 candidate 中的每个元素都是独一无二的
 1 <= target <= 500


 相比组合问题
 本题没有数量要求，可以无限重复，但是有总和的限制，所以间接的也是有个数的限制
 */

public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = new int[] {2,3,5};
        int target = 8;
        System.out.println(combinationSum(candidates,target));
    }

    //方法1
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int start = 0;
        int sum = 0;

        Arrays.sort(candidates);  //排序

        backtrack(candidates, target, start, sum, path, res);

        return  res;
    }

    /**
     * 回溯 + 剪枝
     *
     * 剪枝条件：已选元素总和如果已经大于n（题中要求的和）了，那么往后遍历就没有意义了，直接剪掉
     *
     * 对总集合排序之后，如果下一层的sum（就是本层的 sum + candidates[i]）已经大于target，就可以结束本轮for循环的遍历
     *
     * */
    public static void backtrack(int[] candidates, int target, int start, int sum, List<Integer> path, List<List<Integer>> res) {
        //if(sum > target) {  //因为有剪枝，这个判断可以不要
        //    return;
        //}

        if(sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<candidates.length; i++) {
            if(sum+candidates[i] > target) {  //剪枝
                break;
            }

            sum += candidates[i];
            path.add(candidates[i]);

            backtrack(candidates, target, i, sum, path, res);  // 关键点:不用i+1了，表示可以重复读取当前的数

            sum -= candidates[i];
            path.remove(path.size()-1);
        }
    }

    //方法2：与方法1类似，只是没有专门保存sum，而是根据当前sum每次改变target值
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int start = 0;

        Arrays.sort(candidates);  //排序

        backtrack2(candidates, target, start, path, res);

        return  res;
    }

    public static void backtrack2(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if(target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<candidates.length; i++) {
            if(target-candidates[i] < 0) {  //剪枝
                break;
            }

            path.add(candidates[i]);

            backtrack2(candidates, target-candidates[i], i, path, res);  // 关键点:不用i+1了，表示可以重复读取当前的数

            path.remove(path.size()-1);
        }
    }

}
