package Algorithm.Search;

/**
 * Created by Defias on 2017/10/7.
 *
 * 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 *
 * 查找区间：给定一个包含n个整数的排序数组，找出给定目标值target的起始和结束位置。如果目标值不在数组中，则返回[-1, -1]
 * 给出[5, 7, 7, 8, 8, 10]和目标值target=8,返回[3, 4]
 */

public class BinarySearchRange {
    public static void main(String[] args) {
        int[] nums = new int[] {5, 7, 7, 8, 8, 10};
        int[] res = searchRange2(nums, 8);
        System.out.println(res[0] + " " + res[1]);
    }

    public static int[] searchRange(int[] A, int target) {
        int[] result = new int[] {-1, -1};
        if(A==null || A.length==0) {
            return result;
        }

        int index1 = Algorithm.Search.BinarySearchFrist.binarySearch(A, target);
        if(index1 == -1) {
            return result;
        } else {
            result[0] = index1;
        }

        int index2 = Algorithm.Search.BinarySearchLast.binarySearch(A, target);
        result[1] = index2;
        return result;
    }


    public static int[] searchRange2(int[] A, int target) {
        int[] result = new int[] {-1, -1};
        if(A==null || A.length==0) {
            return result;
        }

        int index1 = Algorithm.Search.BinarySearchFrist.binarySearch(A, target);
        if(index1 == -1) {
            return result;
        } else {
            result[0] = index1;
        }

        int index2 = Algorithm.Search.BinarySearchLast.binarySearch(A, target);
        result[1] = index2;
        return result;
    }

}
