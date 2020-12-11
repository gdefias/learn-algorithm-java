package Questions.Array;

/**
 * Created by Defias on 2017/10/15.
 旋转数组中的最小数

 https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/

 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。

 输入：[3,4,5,1,2]
 输出：1
 */

public class MinArray {
    public static void main(String[] args) {
        int[] A = {1, 3, 5};
        System.out.println(minArray(A));
    }

    public static int minArray(int[] numbers) {
        //二分查找
        if(numbers==null || numbers.length==0) {
            return -1;
        }

        int low = 0;
        int hight = numbers.length-1;
        while(low<hight) {
            int mid = low + (hight-low)/2;
            if(numbers[mid] > numbers[hight]) {
                low = mid+1;
            } else if(numbers[mid] < numbers[hight]) {
                hight = mid;
            } else {
                hight--;
            }
        }

        return numbers[low];
    }
}
