package Questions.Array;
import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * @author: Defias
 * @date: 2020/12/19
 * @description: 旋转数组

https://leetcode-cn.com/problems/rotate-array/

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]

 */
public class Rotate {

    public static void main(String[] args) {
        int[] A = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27};
        rotate5(A,38);  //[17,18,19,20,21,22,23,24,25,26,27,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
        System.out.println(Arrays.stream(A).boxed().collect(Collectors.toList()));
    }


    //方法1：暴力 旋转k次，每次将数组旋转1个元素
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k%len;
        if(k==0) {
            return;
        }

        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                //下面这三行并非是交换
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
                System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            }
        }
    }

    //方法2：使用额外的数组 (i+k)%len
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
    public static void rotate4(int[] nums, int k) {
        int len = nums.length;
        k = k%len;
        if(k==0) {
            return;
        }

        int count = 0;
        for(int start=0; count<len; start++) {
            int cur = start;
            int curv = nums[cur];
            do {
                count++;
                int next = (cur+k)%len;
                int tmp = nums[next];
                nums[next] = curv;
                cur = next;
                curv = tmp;

            } while(cur != start);
        }
    }

    //方法5：递归
    public static void rotate5(int[] nums, int k) {

        recur(nums, k, 0, nums.length);
    }

    public static void recur(int[] nums, int k, int start, int len) {
        k = k%len;
        if(k==0) {
            return;
        }

        for (int i = 0; i < k; i++) {
            swap(nums, start+i, nums.length-k+i);
        }
        recur(nums, k, start+k, len-k);
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
