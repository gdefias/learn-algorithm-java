package Algorithm.DivideAndConquer;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Defias on 2020/07.
 * Description: 计算右侧小于当前元素的个数

 https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/

 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i]
 的元素的数量。

 示例:
    输入: [5,2,6,1]
    输出: [2,1,1,0]

 归并排序（分治思想） + 索引数组
 */
public class CountSmaller {
    int[] res;      //存放结果
    int[] indexnums;   //索引数组

    public static void main(String[] args) {
        CountSmaller solution = new CountSmaller();
        int[] nums = new int[] {2,0,1};
        List<Integer> res = solution.countSmaller(nums);
        System.out.println(res);
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums==null || nums.length==0) {
            return result;
        }

        res = new int[nums.length];
        int left = 0;
        int right = nums.length-1;

        //临时索引数组
        int[] indextmp = new int[nums.length];

        //索引数组
        indexnums = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            indexnums[i] = i;
        }


        megerSmaller(nums, left, right, indextmp);

        for(int i=0; i<res.length; i++) {
            result.add(i, res[i]);
        }
        return result;
    }

    public void megerSmaller(int[] nums, int left, int right, int[] indextmp) {
        if(left>=right) {
            return;
        }

        int mid = left+(right-left)/2;
        megerSmaller(nums, left, mid, indextmp);
        megerSmaller(nums, mid+1, right, indextmp);

        if(nums[indexnums[mid]] > nums[indexnums[mid+1]]) {
            megerCount(nums, left, mid, right, indextmp);
        }
    }

    public void megerCount(int[] nums, int left, int mid, int right, int[] indextmp) {
        int i = left;
        int j = mid+1;

        for(int k=left; k<=right; k++) {
            if(i>mid) {
                indextmp[k] = indexnums[j];
                j++;
            } else if(j>right) {
                indextmp[k] = indexnums[i];
                i++;
                res[indextmp[k]] += (right-mid);   //左半数组中取当前值之前已经取完值的右半数组中的值都比当前值小，总共right-mid个
            } else if(nums[indexnums[i]] <= nums[indexnums[j]]) {
                indextmp[k] = indexnums[i];
                i++;
                res[indextmp[k]] += (j-mid-1); //左半数组中取当前值之前未取完值的右半数组中已取的值都比当前值小，总共j-mid-1个
            } else {
                indextmp[k] = indexnums[j];
                j++;
            }
        }

        for(int k=left; k<=right; k++) {
            indexnums[k] = indextmp[k];
        }
    }
}