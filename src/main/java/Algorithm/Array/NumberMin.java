package Algorithm.Array;

/**
 * Created with IntelliJ IDEA.
 * Description: 把数组排成最小的数
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/

 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个

 输入: [3,30,34,5,9]
 输出: "3033459"

 0 < nums.length <= 100
 */

//定义比较规则  字符串排序

public class NumberMin {
    public static void main(String[] args) {
        NumberMin O = new NumberMin();
        int[] A = {3,30,34,5,9};
        System.out.println(O.minNumber(A));
    }

    public String minNumber(int[] nums) {
        if(nums==null || nums.length==0) {
            return "";
        }
        String[] numss = new String[nums.length];
        for(int i=0; i<nums.length; i++) {
            numss[i] = String.valueOf(nums[i]);
        }

        ksort(numss, 0,  numss.length-1);   //升序

        StringBuilder res = new StringBuilder();
        for(int i=0; i<numss.length; i++) {
            res.append(numss[i]);
        }

        return res.toString();
    }

    public void ksort(String[] numss, int left, int right) {
        if(left>=right) {
            return;
        }
        int mid = partition(numss, left, right);

        ksort(numss, left, mid-1);
        ksort(numss, mid+1, right);
    }

    public int partition(String[] numss, int left, int right) {
        String privot = numss[left];
        int lo = left;

        for(int i=left+1; i<=right; i++) {
            if(comp(numss[i], privot) < 0) {
                lo++;
                swap(numss, lo, i);
            }
        }
        swap(numss, lo, left);

        return lo;
    }

    public static void swap(String[] snums, int i,  int j) {
        String tmp = snums[i];
        snums[i] = snums[j];
        snums[j] = tmp;
    }

    public int comp(String s1, String s2) {
        return (s1+s2).compareTo(s2+s1);
    }
}
