package Algorithm.Array;
import java.util.*;
/**
 * Created by Defias on 2016/4/25.

 删除有序数组中的重复项

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
		int res = deduplication(nums);
		System.out.println(res);
		for(int n:nums) {
			System.out.print(n+" ");
		}
	}

	// O(nlogn) time, O(1) extra space
	// 双指针  不保证重复的数放在尾部
	public static int deduplication(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int len = 0;  //标识已删除重复元素的长度（最后一个元素的索引）
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != nums[len]) { //去重
				nums[++len] = nums[i];
			}
		}
		return len + 1;
	}



	// O(n) time, O(n) space
	// 利用HashMap的key不会重复的特性 不保证重复的数放在尾部
	public static int deduplication2(int[] nums) {
		HashMap<Integer, Boolean> mp = new HashMap<Integer, Boolean>();
		for (int i=0; i<nums.length; ++i)
			mp.put(nums[i], true);

		int result = 0;
		for (Map.Entry<Integer, Boolean> entry : mp.entrySet())
			nums[result++] = entry.getKey();
		return result;
	}


    //姓名去重：给一串名字，将他们去重之后返回。两个名字重复是说在忽略大小写的情况下是一样的
    public static List<String> nameDeduplication(String[] names) {
        Set<String> nameSet =  new  HashSet();
        List<String> nameList = new ArrayList();

        for(String name: names)   {
            name = name.toLowerCase();
            if(nameSet.add(name)) {
                nameList.add(name);
            }
        }
        return nameList;
    }


}
