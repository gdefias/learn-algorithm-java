package Algorithm.BackTracking;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description:  组合总和

 https://leetcode-cn.com/problems/combination-sum/

 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合
 candidates中的数字可以无限制重复被选取

 说明：
 所有数字（包括target）都是正整数
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
        int[] candidates = new int[] {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates,target));
    }

    /**
     * 回溯
     * */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int start = 0;  //起始索引
        int sum = 0;  //累加和

        backstrace(candidates, target, start, sum, path, res);

        return  res;
    }


    public static void backstrace(int[] candidates, int target, int start, int sum, List<Integer> path, List<List<Integer>> res) {
        if(sum == target) {  //满足条件时新增结果
            res.add(new ArrayList<>(path));
            return;
        } else if(sum > target) {  //因为都是正整数，所以和已经大了，当前组合不再可能是满足条件的
            return;
        }

        for(int i=start; i<candidates.length; i++) {
            sum += candidates[i];
            path.add(candidates[i]);

            backstrace(candidates, target, i, sum, path, res);  // 关键点:不用i+1了而是用i，表示可以重复读取当前的数

            sum -= candidates[i];
            path.remove(path.size()-1);
        }
    }


    /**
     * 排序 + 回溯
    * */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int start = 0;  //起始索引

        Arrays.sort(candidates);  //排序  方便后面剪枝

        //没有专门保存和的sum变量，而是每次改变target值
        backstrace2(candidates, target, start, path, res);

        return  res;
    }

    public static void backstrace2(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if(target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<candidates.length; i++) {
            if(target-candidates[i] < 0) {  //剪枝  candidates已排序为升序，当前元素candidates[i]已经比target大了，后面元素的元素更不可能了，所以剪枝
                break;
            }

            path.add(candidates[i]);

            backstrace2(candidates, target-candidates[i], i, path, res);  // 关键点: 不用i+1了，表示可以重复读取当前的数

            path.remove(path.size()-1);
        }
    }
}
