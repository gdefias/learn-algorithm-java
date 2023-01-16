package Algorithm.DP.SubSequence;
import java.util.ArrayList;

/**
 * @author: Defias
 * @date: 2021/3/14
 * @description: 最长递增子序列

    给定数组arr，设长度为n，输出arr的最长递增子序列。（如果有多个答案，请输出其中字典序最小的）

    输入
    [2,1,5,3,6,4,8,9,7]
    输出
    [1,3,4,8,9]


    输入
    [1,2,8,6,4]
    输出
    [1,2,4]
 */
public class LengthOfLIS2 {

    public static void main(String[] args) {
        int[] nums = new int[] {0,8,4,12,2};

        int[] res = lengthOfLIS2(nums);
        for(int i=0; i<res.length; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }

    //方法1：贪心 + 二分
    public static int[] lengthOfLIS2(int[] nums) {
        if(nums==null || nums.length==0) {
            return nums;
        }

        int[] d = new int[nums.length];
        int len = 0;
        d[len] = nums[0];

        ArrayList<Integer> res = new ArrayList<>();
        res.add(nums[0]);

        for(int i=1; i<nums.length; i++) {
            if(nums[i]>d[len]) {
                d[++len] = nums[i];
                res.add(nums[i]);
            } else {
                int index = binSearch(d, len, nums[i]);
                d[index] = nums[i];

                if(d[len] != res.get(res.size()-1)) {
                    for(int k=0; k<=len; k++) {
                        res.set(k, d[k]);
                    }
                }
            }
//            System.out.println(res);
        }

//        for(int i=0; i<d.length; i++) {
//            System.out.print(d[i] + " ");
//        }
//        System.out.println();

        return getArray(res);
    }


    //List转数组
    public static int[] getArray(ArrayList<Integer> res) {
        int[] result = new int[res.size()];

        for(int i=0; i<res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    //二分查找
    public static int binSearch(int[] d, int end, int target) {
        int i = 0;
        int j = end;

        while(i<=j) {

            int mid = i+(j-i)/2;
            if(d[mid]>=target) {
                if(mid==0 || d[mid-1]<target) {
                    return mid;
                } else {
                    j = mid-1;
                }
            } else {
                if(d[mid+1]>target) {
                    return mid+1;
                } else {
                    i = mid+1;
                }
            }
        }
        return 0;
    }
}
