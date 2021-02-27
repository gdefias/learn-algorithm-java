package Algorithm.Queue;
import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * Description:  数据流中的第 K 大元素       ---使用优先队列实现
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/

 设计一个找到数据流中第 k 大元素的类（class）。

 注意是排序后的第 k 大元素，不是第 k 个不同的元素???
 解读：
 题目第k大的元素含义是“将数字从左到右降序排列后，从左边开始数的第k个数”，另外，题目要求每add一个元素“后”，就返回当前的第k大的元素

 请实现 KthLargest 类：
 KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象
 int add(int val) 返回当前数据流中第 k 大的元素

 输入：
 ["KthLargest", "add", "add", "add", "add", "add"]
 [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 输出：
 [null, 4, 5, 5, 8, 8]

 解释：
 KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 kthLargest.add(3);   // return 4
 kthLargest.add(5);   // return 5
 kthLargest.add(10);  // return 5
 kthLargest.add(9);   // return 8
 kthLargest.add(4);   // return 8


 1 <= k <= 104
 0 <= nums.length <= 104
 -104 <= nums[i] <= 104
 -104 <= val <= 104
 最多调用 add 方法 104 次
 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 */

public class KthLargest {
    PriorityQueue<Integer> q;
    int limit;

    public static void main(String[] args) {
        int[] A = {4, 5, 8, 2};
        int k = 3;
        KthLargest O = new KthLargest(k, A);
        System.out.println(O.q.peek());
        System.out.println(O.add(3));
        System.out.println(O.add(5));
        System.out.println(O.add(10));

        System.out.println(O.q.peek());
        System.out.println(O.add(9));
        System.out.println(O.add(4));

        System.out.println(O.q.peek());
    }


    public KthLargest(int k, int[] a) {
        this.limit = k;
        q = new PriorityQueue<Integer>(k);
        for (int n : a) {
            add(n);
        }
    }

    public int add(int n) {
        if (q.size() < limit) {
            q.offer(n);
        } else if (q.peek() < n) {
            q.poll();
            q.offer(n);
        }

        return q.peek();
    }
}
