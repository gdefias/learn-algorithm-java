package Algorithm.Array;
/**
 * Created by Defias on 2020/07.
 * Description:最大数

 https://leetcode-cn.com/problems/largest-number/

 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。

 输入：nums = [10,2]
 输出："210"

 输入: [3,30,34,5,9]
 输出: 9534330

 1 <= nums.length <= 100
 0 <= nums[i] <= 10^9
 */
public class LargestNumber {
    public static void main(String[] args) {
        LargestNumber O = new LargestNumber();
        int[] A = {3, 32, 21, 14, 225, 21};
        System.out.println(O.largestNumber(A));
    }

    public String largestNumber(int[] nums) {
        String[] cnums = new String[nums.length];
        StringBuilder res = new StringBuilder();

        for(int i=0; i<nums.length; i++) {
            cnums[i] = String.valueOf(nums[i]);
        }

        ksort(cnums, 0, cnums.length-1);


        for(int i=0; i<cnums.length; i++) {
            if(i==0 && cnums[i].equals("0")) {
                return "0";
            }
            res.append(cnums[i]);
        }


        return res.toString();
    }

    public static void ksort(String[] cnums, int left, int right) {
        if(left>=right) {
            return;
        }
        int provit = partition(cnums, left, right);

        ksort(cnums, left, provit-1);
        ksort(cnums, provit+1, right);
    }

    public static int partition(String[] cnums, int left, int right) {

        String provitnc = cnums[left];

        while(left<right) {
            while (left<right && comp(cnums[right], provitnc)<0) {
                right--;
            }
            cnums[left] = cnums[right];


            while (left<right && comp(cnums[left], provitnc)>=0) {
                left++;
            }
            cnums[right] = cnums[left];
        }

        cnums[left] = provitnc;
        return left;
    }


    public static int comp(String s1, String s2) {
        return (s1+s2).compareTo(s2+s1);
    }
}
