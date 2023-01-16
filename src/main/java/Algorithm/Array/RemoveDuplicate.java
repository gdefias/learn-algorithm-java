package Algorithm.Array;
import java.util.*;
/**
 * Created by Defias on 2016/4/25.

 删除有序数组中的重复项

 https://leetcode.cn/problems/remove-duplicates-from-sorted-array/

 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

 输入：nums = [1,1,2]
 输出：2, nums = [1,2]
 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。


 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 输出：5, nums = [0,1,2,3,4]
 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。

 0 <= nums.length <= 3 * 104
 -104 <= nums[i] <= 104
 nums 已按升序排列
 */

public class RemoveDuplicate {
	public static void main(String[] arg) {
		//int[] nums = new int[] {1,3,1,4,4,2};
		//Arrays.sort(nums);  //对于无序数组可以先排序

		int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
		int res = removeDuplicates(nums);
		System.out.println(res);
		for(int n:nums) {
			System.out.print(n+" ");
		}
	}

	// 方法1： 双指针
	// 时间复杂度：O(nlogn) 空间复杂度：O(1)
	public static int removeDuplicates(int[] nums) {
		if (nums==null || nums.length == 0) {
			return 0;
		}

		int lo = 0;  //已删除重复元素序列的最后一个元素的索引
		for (int i=1; i<nums.length; i++) {
			if (nums[i] != nums[lo]) { //去重
				lo++;
				nums[lo] = nums[i];
			}
		}
		return lo + 1;
	}

	// 双指针
	// 另一种写法
	public static int removeDuplicates1(int[] nums) {
		if(nums==null || nums.length<1) {
			return 0;
		}

		int ans = 0;
		int left = -1;
		int right = 0;
		while(right <= nums.length-1) {
			while(right < nums.length-1 && (nums[right] == nums[right+1])) {
				right++;
			}

			left++;
			swap(nums, left, right);
			ans++;
			right++;
		}

		return ans;
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}



	// 方法2： 利用HashMap的key不会重复的特性
	// 时间复杂度：O(n) 空间复杂度：O(n)
	public static int removeDuplicates2(int[] nums) {
		HashMap<Integer, Boolean> mp = new HashMap<Integer, Boolean>();
		for (int i=0; i<nums.length; ++i)
			mp.put(nums[i], true);

		int result = 0;
		for (Map.Entry<Integer, Boolean> entry : mp.entrySet())
			nums[result++] = entry.getKey();
		return result;
	}


}
