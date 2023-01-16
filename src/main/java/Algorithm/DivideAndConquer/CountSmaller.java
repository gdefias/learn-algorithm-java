package Algorithm.DivideAndConquer;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Defias on 2020/07.
 * Description: 计算右侧小于当前元素的个数

     https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/

     给定一个整数数组 nums，按要求返回一个新数组counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i]
     的元素的数量。

     示例:
        输入: [5,2,6,1]
        输出: [2,1,1,0]

     归并排序（分治思想） + 索引数组
 */
public class CountSmaller {

    public static void main(String[] args) {
        CountSmaller solution = new CountSmaller();
//        int[] nums = new int[] {2,3,7,9,0,1,4,6};  //排序后 0 1 2 3 4 6 7 9  //索引数组存的 4 5 0 1 6 7 2 3
        int[] nums = new int[] {5,2,6,1};
        List<Integer> res = solution.countSmaller(nums);
        System.out.println(res);
    }

    //方法1：分治  归并排序的思想
    //时间复杂度：同归并排序 O(NlogN)，数组的元素个数是N，递归执行分治法，时间复杂度是对数级别的，因此时间复杂度是O(NlogN)
    //空间复杂度：同归并排序 O(N)，需要3个数组，一个索引数组，一个临时索引数组用于索引数组的归并，还有一个结果数组，它们的长度都是N
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums==null || nums.length==0) {
            return result;
        }

        //存放结果 res的下标/索引为元素在原始数组中的索引，值为这个元素在原始数组中右边比它下的元素个数
        int[] res = new int[nums.length];

        //索引数组（下标数组）
        int[] indexnums = new int[nums.length];
        for(int i=0; i<nums.length; i++) {  //初始化
            indexnums[i] = i;
        }

        //临时索引数组
        int[] indextmp = new int[nums.length];

        int left = 0;
        int right = nums.length-1;

        //归并排序
        megerSmaller(nums, left, right, indexnums, indextmp, res);
        //排完序后，原始数组nums并不会变，排序结果是indexnums数组，存放的是排序后元素在原始数组中的索引
        //即：通过排序序号（从0开始，也即索引数组的下标/索引） 到索引数组indexnums中取值获得原始数组的索引  再通过取得的这个索引到原始数组中拿到值

        //拿结果
        for(int i=0; i<res.length; i++) {
            result.add(i, res[i]);
        }
        return result;
    }

    public void megerSmaller(int[] nums, int left, int right, int[] indexnums, int[] indextmp, int[] res) {
        if(left>=right) {
            return;
        }

        int mid = left+(right-left)/2; //这里left、right、mid操作的是索引数组，可以理解为索引数组的索引
        megerSmaller(nums, left, mid, indexnums, indextmp, res);
        megerSmaller(nums, mid+1, right, indexnums, indextmp, res);

        // 归并排序的优化，如果归并前，索引数组的两个部分已经构成整体有序了，则不需要进行归并
        if(nums[indexnums[mid]] <= nums[indexnums[mid+1]]) {
            return;
        }

        meger(nums, left, mid, right, indexnums, indextmp, res);
    }

    public void meger(int[] nums, int left, int mid, int right, int[] indexnums, int[] indextmp, int[] res) {
        int i = left;
        int j = mid+1;


        for(int k=left; k<=right; k++) {
            if(i<=mid && (j>right || nums[indexnums[i]] <= nums[indexnums[j]])) {
                indextmp[k] = indexnums[i];
                i++;
                res[indextmp[k]] += (j-mid-1);
                //当左边的数较小时，那当前右边的数的左侧（右边已经遍历过的）的数都比这个左边的数小，也就是这个左边的数的右边有j-mid-1个数比它小
                //即使右边的数先取完了，依然成立
            } else {
                indextmp[k] = indexnums[j];
                j++;
            }
        }

//        for(int k=left; k<=right; k++) {
//            if(i>mid) {
//                indextmp[k] = indexnums[j];
//                j++;
//            } else if(j>right) {
//                indextmp[k] = indexnums[i];
//                i++;
//                res[indextmp[k]] += (right-mid);
//                //当右边的数都取完了，那右边已经遍历过的所有数都要比当前这个左边的数小，也就是当前这个左边的数的右边有right-mid个数比它小
//                //此时j==right+1，所以(right-mid)==(j-mid-1)
//            } else if(nums[indexnums[i]] <= nums[indexnums[j]]) {
//                indextmp[k] = indexnums[i];
//                i++;
//                res[indextmp[k]] += (j-mid-1);
//                //当左边的数较小时，那当前右边的数的左侧（右边已经遍历过的）的数都比这个左边的数小，也就是这个左边的数的右边有j-mid-1个数比它小
//            } else {
//                indextmp[k] = indexnums[j];
//                j++;
//            }
//        }


        for(int k=left; k<=right; k++) {
            indexnums[k] = indextmp[k];
        }
    }
}