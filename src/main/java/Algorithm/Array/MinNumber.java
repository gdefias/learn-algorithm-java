package Questions.Array;

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

public class MinNumber {
    public static void main(String[] args) {
        MinNumber O = new MinNumber();
        int[] A = {3, 32, 21, 14, 225, 21};
        System.out.println(O.minNumber(A));
    }


    //定义比较规则，快速排序的思路
    public String minNumber(int[] nums) {
        if(nums==null || nums.length==0) {
            return "";
        }
        String[] numss = new String[nums.length];
        for(int i=0; i<nums.length; i++) {
            numss[i] = String.valueOf(nums[i]);
        }

        fastSort(numss, 0,  numss.length-1);
        StringBuilder res = new StringBuilder();
        for(int i=0; i<numss.length; i++) {
            res.append(numss[i]);
        }

        return res.toString();
    }

    public void fastSort(String[] numss, int left, int right) {
        if(left>=right) {
            return;
        }
        int prorit = partition(numss, left, right);

        fastSort(numss, left, prorit-1);
        fastSort(numss, prorit+1, right);
    }

    public int partition(String[] numss, int left, int right) {
        String provitstr = numss[left];

        while(left<right) {
            while(left<right && comp(numss[right],provitstr)>0) {
                right--;
            }
            numss[left] = numss[right];

            while(left<right && comp(numss[left],provitstr)<=0) {
                left++;
            }
            numss[right] = numss[left];
        }
        numss[left] = provitstr;
        return left;
    }

    public int comp(String s1, String s2) {
        return (s1+s2).compareTo(s2+s1);
    }
}
