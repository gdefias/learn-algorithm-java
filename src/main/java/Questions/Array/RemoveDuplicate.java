package Questions.Array;
import java.util.*;
/**
 * Created by Defias on 2016/4/25.
 *
 * Remove Duplicate Numbers in Collection.Questions.Array
 *
 *  Given nums = [1,3,1,4,4,2], you should:
 * 	Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
 * 	Return the number of unique integers in nums => 4.
 *
 */

public class RemoveDuplicate {
	public static void main(String[] arg) {
		//deduplication
		int[] nums = new int[] {1,3,1,4,4,2};
		Arrays.sort(nums);
		int res = deduplication2(nums);
		System.out.println(res);
		for(int n:nums) {
			System.out.print(n+" ");
		}
	}


	public static int deduplication1(int[] nums) {
		TreeSet<Integer> numsets = new TreeSet<Integer>();
		ArrayList<Integer> numlists = new ArrayList<Integer>();
		ArrayList<Boolean> isnums = new ArrayList<Boolean>();
		for(int i=0; i<nums.length; ++i) {
			numsets.add(nums[i]);
		}
		int result = numsets.size();
		for(int j=0; j<result; ++j) {
			isnums.add(false);
		}

		for(int n: numsets) {
			numlists.add(n);
		}

		for(int k: nums) {
			int index = numlists.subList(0,result).indexOf(k);
			if(isnums.get(index)) {
				numlists.add(k);
			} else {
				isnums.set(index, true);
			}
		}

		for(int i=0; i<numlists.size(); ++i) {
			nums[i] = numlists.get(i);
		}
		return result;
	}

	// O(nlogn) time, O(1) extra space  没有保证重复的数放在尾部
	public static int deduplication2(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		Arrays.sort(nums);  //1 1 2 3 4 4

		int len = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != nums[len]) { //去重
				nums[++len] = nums[i];
			}
		}
		return len + 1;
	}

	// O(n) time, O(n) space  利用HashMap的key不会重复的特性 没有保证重复的数放在尾部
	public static int deduplication3(int[] nums) {
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
