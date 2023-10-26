package Algorithm.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Defias on 2017/10/8.

 颜色分类

 https://leetcode-cn.com/problems/sort-colors/

 给定一个包含红，白，蓝且长度为n的数组，将数组元素进行分类使相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序可以使用整数0，1 和 2 分
 别代表红，白，蓝

 进阶：
 你可以不使用代码库中的排序函数来解决这道题吗？
 你能想出一个仅使用常数空间的一趟扫描算法吗？
 

 示例 1：
 输入：nums = [2,0,2,1,1,0]
 输出：[0,0,1,1,2,2]

 示例 2：
 输入：nums = [2,0,1]
 输出：[0,1,2]
 */

public class Sort3Colors {

    public static void main(String[] args) {
        int[] nums = new int[] {2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }


    //方法1： 双指针
    public static void sortColors(int[] nums) {
        int len = nums.length;
        int p = -1;  //红球的右边界
        int q = len;  //白球的左边界

//        for(int i=0; i<q; i++) {
//            if(nums[i] == 0) {
//                p++;
//                swap(nums, p, i);
//            } else if(nums[i] == 2) {
//                q--;
//                swap(nums, q, i);
//                i--;
//            }
//        }

        int i = 0;
        while(i < q) {
            if(nums[i] == 0) {
                p++;
                swap(nums, p, i);
                i++;
            } else if(nums[i] == 2) {
                q--;
                swap(nums, q, i);
            } else {
                i++;
            }
        }
    }


    //方法2：单指针 对数组进行两次遍历
    public static void sortColors2(int[] nums) {
        int len = nums.length;
        int p = 0;
        for (int i=0; i<len; ++i) {  //第一趟排好红球
            if (nums[i] == 0) {
                swap(nums, p, i);
                ++p;
            }
        }

        for (int i=p; i<len; ++i) {  //第二趟接着第一趟的最后位置排好黄球
            if (nums[i] == 1) {
                swap(nums, p, i);
                ++p;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
