package numslgorithm.numsrray;

/**
 * Created by Defias on 2017/10/7.

 搜索旋转数组
 https://leetcode-cn.com/problems/search-rotate-array-lcci/

 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元
 素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个

 示例1:
 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 输出: 8（元素5在该数组中的索引）

 示例2:
 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 输出：-1 （没有找到）

 arr 长度范围在[1, 1000000]之间

 提示：
 这道题与RotationSortSearch2很像，唯一的区别就是RotationSortSearch2题要求只要存在target就返回true，而这道题要 返回多个重复target
 中最靠前的那个
 */
public class RotationSortSearch3 {

    public static void main(String[] args) {
        int[] num = new int[] {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        System.out.println(search(num, 5));
    }

    //二分查找
    //与右边界值进行比较
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while(start < end) {
            int mid = start+(end-start)/2;
            if(target == nums[mid]) {
                end = mid;
                continue;
            }

            //当没找到时需要区分当前mid是在前面的递增区间还是后面的递增区间
            if(nums[mid] > nums[end]) {
                if(target > nums[mid] || target <= nums[end]) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            } else if(nums[mid] < nums[end]) {
                if(target < nums[mid] || target > nums[end]) {
                    end = mid-1;
                } else if(target < nums[end]) {
                    start = mid+1;
                } else { // target == nums[end]
                    if(nums[end] == nums[start]) {
                        return start;
                    } else {
                        start = mid+1;
                    }
                }
            } else {  //mid值与上界值相等时，上界减小
                end--;
            }
        }

        if(nums[start] == target) {
            return start;
        }
        return -1;
    }

    //二分查找
    //与左边界值进行比较
    public static int search_(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        while(start < end) {
            int mid = start+(end-start)/2;
            if(target == nums[mid]) {
                end = mid;
                continue;
            }

            if(nums[mid] > nums[start]) {
                if(target<nums[mid] && target>=nums[start]) {
                    end = mid-1;
                } else {
                    start = mid+1;
                }

            } else if(nums[mid] < nums[start]) {
                if(target>nums[mid] && target<nums[start]) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            } else {
                start++;
            }
        }

        if(nums[start] == target) {
            return start;
        }
        return -1;
    }


}
