package Algorithm.Array;

/**
 * Created with IntelliJ IDEA.
 * Description: 调整数组顺序使奇数位于偶数前面
 * User: Defias
 * Date: 2018-10

 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分

 https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/

 输入：nums = [1,2,3,4]
 输出：[1,3,2,4]
 注：[3,1,2,4] 也是正确的答案之一。

 1 <= nums.length <= 50000
 1 <= nums[i] <= 10000
 */
public class Exchange {

    public static void main(String[] args) {
        int[] A = new int[] {1,2,3,4,5,6,7,8,9};
        exchange(A);
        for(int a:A) {
            System.out.println(a);
        }
    }

    public static int[] exchange(int[] nums) {
        if(nums==null || nums.length==0) {
            return nums;
        }

        int i = 0;
        int j = nums.length-1;

        while(i<j) {
            while((i<j) && (nums[i]&1)==1) {
                i++;
            }

            while((i<j) && (nums[j]&1)==0) {
                j--;
            }

            swap(nums, i, j);
            i++;
            j--;
        }
        return nums;
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
