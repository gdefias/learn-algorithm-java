package Questions.Array;
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
        sortColors3(nums);
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    //方法1： 双指针
    public static void sortColors1(int[] nums) {
        int k = -1;
        int i = 0;
        int j = nums.length-1;

        while(i<=j) {  //排白色和蓝色
            while(i<=j) {
                if(nums[i]==2) {
                    break;
                } else if(nums[i]==0) {     //将红色方在数组头部
                    if(k>=0) {
                        swap(nums, i, k);
                        k++;
                    }
                } else {
                    if(k<0) {
                        k = i;
                    }
                }
                i++;
            }

            while(i<=j && nums[j]==2) {
                j--;
            }

            if(i<=j) {
                swap(nums, i, j);
                j--;
            }
        }
    }


    //方法2：双指针 用指针p0来交换0，p1来交换1
    public static void sortColors2(int[] nums) {
        if (nums==null || nums.length<=2) {
            return;
        }
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                swap(nums, i, p1);
                ++p1;
            } else if (nums[i] == 0) {
                swap(nums, i, p0);
                if (p0 < p1) {
                    swap(nums, i, p1);
                }
                ++p0;
                ++p1;
            }
        }
    }


    //方法3：单指针 对数组进行两次遍历
    public static void sortColors3(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
