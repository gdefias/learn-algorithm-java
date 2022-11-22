package Algorithm.Array;
import java.util.*;

/**
 * Created by Defias on 2016/3/23.
 两数之和

 给一个整数数组，找到两个数使得他们的和等于一个给定的数target，每个数只能用一次

 需要返回这两个数的下标, 并且第一个下标小于第二个下标
 */

class TwoSum2 {
    public static int[] indexnums;

	public static void  main(String[] args) {
		int[] numbers = new int[] {-3,2,3,4,5,6,7,8,1};
		int target = 7;
		int[] result = twoSum2(numbers, target);
		System.out.println(result[0]);
		System.out.println(result[1]);
	}

    //使用hashmap
    public static int[] twoSum(int[] nums, int target) {
        if(nums==null) {
            return null;
        }
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            int ftarget = target-nums[i];
            if(hm.containsKey(ftarget)) {
                int[] result = {hm.get(ftarget), i};
                return result;
            } else {
                hm.put(nums[i], i);
            }
        }
        return null;
    }


    //索引数组 + 排序 + 双指针
    public static int[] twoSum2(int[] nums, int target) {
        if(nums==null) {
            return null;
        }

        indexnums = new int[nums.length];
        int[] indextmp = new int[nums.length];

        for(int i=0; i<nums.length; i++) {
            indexnums[i] = i;
        }

        //for(int index: indexnums) {
        //    System.out.print(index + " ");
        //}
        //System.out.println();

        indexsort(nums, indextmp, 0, nums.length-1);

        //for(int num: nums) {
        //    System.out.print(num + " ");
        //}
        //System.out.println();
        //
        //
        //for(int index: indexnums) {
        //    System.out.print(index + " ");
        //}
        //System.out.println();

        int start = 0;
        int end = nums.length-1;
        while(start < end) {
            int currentsum = nums[indexnums[start]]+nums[indexnums[end]];
            if(currentsum==target) {
                return new int[] {indexnums[start], indexnums[end]};
            } else if(currentsum>target) {
                end--;
            } else {
                start++;
            }
        }
        return null;
    }

    public static void indexsort(int[] nums, int[] indextmp, int left, int right) {
        if(left>=right) {
            return;
        }
        int mid = left + (right-left)/2;

        indexsort(nums, indextmp, left, mid);
        indexsort(nums, indextmp, mid+1, right);

        indexmerge(nums, indextmp, left, mid, right);
    }

    public static void indexmerge(int[] nums, int[] indextmp, int left, int mid, int right) {
        int i = left;
        int j = mid+1;

        for(int k=left; k<=right; k++) {
            if((j>right) || (i<=mid) && nums[indexnums[i]]<=nums[indexnums[j]]) {
                indextmp[k] = indexnums[i];
                i++;
            } else {
                indextmp[k] = indexnums[j];
                j++;
            }
        }

        for(int k=left; k<=right; k++) {
            indexnums[k] = indextmp[k];
        }

        return;
    }
}