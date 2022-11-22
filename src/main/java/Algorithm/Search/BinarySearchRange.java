package Algorithm.Search;
/**
 * Created by Defias on 2017/10/7.
 *
 *
 * 查找区间：给定一个包含n个整数的排序数组，找出给定目标值target的起始和结束位置。如果目标值不在数组中，则返回[-1, -1]
 * 给出[5, 7, 7, 8, 8, 10]和目标值target=8,返回[3, 4]
 */

public class BinarySearchRange {
    public static void main(String[] args) {
        int[] nums = new int[] {5, 7, 7, 8, 8, 10};
        System.out.println(searchRange(nums, 8));
    }

    public static int[] searchRange(int[] A, int target) {
        int[] result = new int[] {-1, -1};
        if(A==null || A.length==0) {
            return result;
        }

        //find x
        int start = 0;
        int end = A.length - 1;
        int mid;
        while(start < end - 1) {
            mid = start + (end - start) / 2;
            if(A[mid] == target) {
                end = mid;
            } else if(A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if(A[start] == target) {
            result[0] = start;
        } else if(A[end] == target) {
            result[0] = end;
        } else {
            result[0] = -1;
        }

        //find y
        start = 0;
        end = A.length - 1;

        while(start < end - 1) {
            mid = start + (end - start) / 2;
            if(A[mid] == target) {
                start = mid;
            } else if(A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if(A[end] == target) {
            result[1] = end;
        } else if(A[start] == target) {
            result[1] = start;
        } else {
            result[1] = -1;
        }
        return result;
    }
}
