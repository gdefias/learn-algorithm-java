package Algorithm.Array;
import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * @author: Defias
 * @date: 2020/12/19
 * @description: 旋转数组

https://leetcode-cn.com/problems/rotate-array/

给定一个数组，将数组中的元素向右移动k个位置，其中k是非负数。

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]

 */
public class RotationArray {

    public static void main(String[] args) {
        int[] A = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27};
        rotate(A,1);  //[17,18,19,20,21,22,23,24,25,26,27,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
        System.out.println(Arrays.stream(A).boxed().collect(Collectors.toList()));
    }


    //方法1：暴力 旋转k次，每次将数组旋转1个元素
    //时间复杂度：O(nk)  空间复杂度：O(1)
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k%len;
        if(k==0) {
            return;
        }

        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];  //前一个值 （最后一个数是第一个数的前一个值）
            for (int j=0; j<nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
//                System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            }
        }
    }

    //方法2：使用额外的数组 (i+k)%len
    //时间复杂度：O(n)  空间复杂度：O(n)
    public static void rotate2(int[] nums, int k) {
        int len = nums.length;
        k = k%len;
        if(k==0) {
            return;
        }

        int[] numsn = new int[len];
        for(int i=0; i<len; i++) {
            numsn[(i+k)%len] = nums[i];
        }

        for(int i=0; i<len; i++) {
            nums[i] = numsn[i];
        }
    }

    //方法3：反转
    //时间复杂度：O(n)  空间复杂度：O(1)
    public static void rotate3(int[] nums, int k) {
        int len = nums.length;
        k = k%len;
        if(k==0) {
            return;
        }
        helper(nums, 0, len-1);
        helper(nums,0,k-1);
        helper(nums, k, len-1);

    }

    public static void helper(int[] nums, int i, int j) {
        while(i<j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

    //方法4：循环交换
    //时间复杂度：O(n)  空间复杂度：O(1)
    public static void rotate4(int[] nums, int k) {
        int len = nums.length;
        k = k%len;
        if(k==0) {
            return;
        }

        int count = 0;
        for(int start=0; count<len; start++) {
            int p = start;  //起始位置
            int v = nums[p]; //起始位置的值

            do {
                count++;
                int next = (p+k)%len;
                int tmp = nums[next];
                nums[next] = v;
                p = next;
                v = tmp;

            } while(p != start);
            //当nums的总数为奇数时，回到起始位置时数组已遍历完，count==len
            //当nums的总数为偶数时，回到起始位置时数组不一定遍历完，需要改变起始位置然后继续
        }
    }
}
