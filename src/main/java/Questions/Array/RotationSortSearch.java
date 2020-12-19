package Questions.Array;

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
 */


public class RotationSortSearch {

    public static void main(String[] args) {
        int[] num = new int[] {1,1,1,1,1,2,1,1,1};
        System.out.println(search(num, 2));
    }


    //[1,1,1,1,1,2,1,1,1]
    public static int search(int[] A, int target) {
        if(A==null || A.length==0 || (A.length==1 && A[0]!=target)) {
            return -1;
        }

        int left = 0;
        int right = A.length-1;

        while(left<right) {
            int mid = left+(right-left)/2;
            if(A[mid]>A[left]) {
                if(A[mid]>=target && target>=A[left]) {
                    right = mid;
                } else {
                    left = mid+1;
                }

            } else if(A[mid]<A[left]) {
                if(A[mid]<target && target<A[left]) {
                    left = mid+1;
                } else {
                    right = mid;
                }

            } else {
                if(A[mid] != target) {
                    left++;
                } else {
                    right = mid;
                }
            }
        }

        return A[left]==target?left:-1;
    }
}
