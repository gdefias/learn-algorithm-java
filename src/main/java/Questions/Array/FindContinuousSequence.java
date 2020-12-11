package Questions.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 和为s的连续正数序列
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/

 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

 输入：target = 9
 输出：[[2,3,4],[4,5]]

 1 <= target <= 10^5
 */
public class FindContinuousSequence {

    public static void main(String[] args) {
        int[][] result = findContinuousSequence(15);
    }

    public static int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();

        int left = 1;
        int right = 2;

        int sum = left+right;
        while(left<right && right<=(target+1)/2) {
            if(sum==target) {
                int[] res = new int[right-left+1];
                int first = left;
                for(int i=0; i<res.length; i++) {
                    res[i] = first;
                    first++;
                }
                result.add(res);
                sum -=left;
                left++;
                right++;
                sum += right;
            } else if(sum<target) {
                right++;
                sum += right;
            } else {
                sum -=left;
                left++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

}
