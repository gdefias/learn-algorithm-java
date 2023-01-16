package Algorithm.Array;

import java.util.ArrayDeque;

/**
 * @author: Felix
 * @date: 2023/1/3
 * @description: 拼接最大数

   https://leetcode.cn/problems/create-maximum-number/description/
   https://zhuanlan.zhihu.com/p/427568682

    给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字
    拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
    求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
    说明: 请尽可能地优化你算法的时间和空间复杂度。

    输入:
    nums1 = [3, 4, 6, 5]
    nums2 = [9, 1, 2, 5, 8, 3]
    k = 5
    输出:
    [9, 8, 6, 5, 3]


    输入:
    nums1 = [6, 7]
    nums2 = [6, 0, 4]
    k = 5
    输出:
    [6, 7, 6, 0, 4]

 */
public class MaxNumber {

    public static void main(String[] args) {
        MaxNumber O = new MaxNumber();
        int[] nums1 = new int[]{3, 4, 6, 5};
        int[] nums2 = new int[]{9, 1, 2, 5, 8, 3};
        int[] res = O.maxNumber(nums1, nums2, 5);

        for(int i=0; i<res.length; i++) {
            System.out.print(res[i] + " ");
        }

    }

    //贪心思想 + 单调栈
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] maxSubSeq = new int[k];

        int m = nums1.length;
        int n = nums2.length;

        int start = Math.max(0, k-n);  //数组nums1中最少能取start个数
        int end = Math.min(m, k);  //组nums1中最多能取end个数

        for (int i = start; i <= end; i++) {
            int[] subSeq1 = maxSubSequence1(nums1, i); //从第一个数组中拿出 i 个数组成的子序列
            int[] subSeq2 = maxSubSequence1(nums2, k - i); //从第二个数组中拿出k- i 个数组成的子序列
            int[] currMaxSubSeq = merge(subSeq1, subSeq2); // 合并两个最大子序列
            if (compare(currMaxSubSeq, 0, maxSubSeq, 0) > 0) {//如果当前的序列比当前最大序列还大，把当前序列拷贝到结果集中
                System.arraycopy(currMaxSubSeq, 0, maxSubSeq, 0, k);
            }
        }
        return maxSubSeq;//遍历完所有情况后得到最大子序列
    }

//    private int[] maxSubSequence(int[] nums, int k) {
//        int[] stack = new int[k];//因为要放入的元素是固定的，可以用数组代替栈，避免while循环
//        int top = -1; //用于表示栈顶元素
//        int remain = nums.length - k;
//        for (int i = 0; i < nums.length; i++) {
//            int num = nums[i];
//            while (top >= 0 && num > stack[top] && remain > 0) { //top
//                top--;//代替出栈
//                remain--;
//            }
//            if (top < k - 1) {
//                stack[++top] = num;//代替入栈
//            } else {
//                remain--;//如果栈已经满了，而当前元素又没有比栈顶大，舍弃当前元素
//            }
//        }
//        return stack;
//    }

    private int[] maxSubSequence1(int[] nums, int k) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int remain = nums.length - k; //remain用来判断剩下的元素是否还够，不够的话就不要出栈了
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && num > stack.peek() && remain > 0) {
                stack.pop();
                remain--;
            }
            if (stack.size() < k) {
                stack.push(num);
            } else {
                remain--;
            }
        }
        //把栈中的元素放入结果集中，栈顶的元素放在最后
        int[] res = new int[k];
        int index = k - 1;
        while (!stack.isEmpty()) {
            res[index--] = stack.pop();
        }
        return res;
    }

    private int[] merge(int[] subSeq1, int[] subSeq2) {
        int x = subSeq1.length;
        int y = subSeq2.length;
        if (x == 0) return subSeq2;
        if (y == 0) return subSeq1;
        int mergeLen = x + y;
        int[] merged = new int[mergeLen];
        int index1 = 0, index2 = 0;

        for (int i = 0; i < mergeLen; i++) {
            if (compare(subSeq1, index1, subSeq2, index2) > 0) {
                merged[i] = subSeq1[index1++];
            } else {
                merged[i] = subSeq2[index2++];
            }
        }
        return merged;
    }

    private int compare(int[] subSeq1, int index1,
                        int[] subSeq2, int index2) {
        int x = subSeq1.length;
        int y = subSeq2.length;
        while (index1 < x && index2 < y) {
            int diff = subSeq1[index1] - subSeq2[index2];
            if (diff != 0) return diff;//如果不相等时，返回diff（diff>0 说明subSeq1的大）
            index1++;//如果相等就看下一位
            index2++;
        }
        return (x - index1) - (y - index2); //循环结束意味着有一方已经遍历完，这时另一个没遍历完的直接合并
    }
}
