package Questions.Array;
import java.util.*;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Created by Defias on 2017/10/12.

 https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/

 滑动窗口的最大值
 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 输出: [3,3,5,5,6,7]

 解释:
 滑动窗口的位置                最大值
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

 可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小
 */

public class MaxSlidingWindow {

    public static void main(String[] args) {
        int[] A = {1,3,-1,-3,5,3,6,7};
        int[] res = maxSlidingWindow11(A, 3);
        for(int a: res) {
            System.out.print(a+" ");
        }
    }

    //方法1：暴力法 双重循环   O(nk)
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int winnum = n-k+1;      //滑动窗口的数量
        int[] res = new int[winnum];
        int index = 0;

        for(int i=0; i<winnum; i++) {
            int maxn = Integer.MIN_VALUE;

            for(int j=0; j<k; j++) {
                if(nums[j+i]>maxn) {
                    maxn = nums[j+i];
                }
            }
            res[index++] = maxn;
        }
        return res;
    }

    //对暴力法进行优化
    public static int[] maxSlidingWindow11(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int winnum = n-k+1;      //滑动窗口的数量
        int[] res = new int[winnum];

        for(int i=0; i<winnum; i++) {
            //如果丢弃的元素并不是上次窗口的最大值,只需要把上次窗口最大值和此次移动增加的新值比较，更新最大值即可
            if(i>0 && nums[i-1]!=res[i-1]) {
                if(nums[i+k-1]>res[i-1]) {
                    res[i] = nums[i+k-1];
                } else {
                    res[i] =  res[i-1];
                }
            } else {
                int maxn = Integer.MIN_VALUE;
                for(int j=0; j<k; j++) {
                    if(nums[j+i]>maxn) {
                        maxn = nums[j+i];
                    }
                }
                res[i] = maxn;
            }


        }
        return res;
    }


    //方法2：使用优先队列  大顶堆
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        if(k==1) {
            return nums;
        }

        int[] res = new int[nums.length-k+1];  //存放结果
        int index=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        //初始窗口
        int left = 0;
        int right = k-1;
        for(int i=0; i<k; i++) {
            pq.offer(nums[i]);
        }
        res[index++] = pq.peek();

        //滑动窗口
        for(int i=1; i<nums.length-k+1; i++) {
            pq.remove(nums[left]);
            pq.offer(nums[right+1]);
            res[index++] = pq.peek();
            left++;
            right++;
        }
        return res;
    }


    //方法3：使用双端队列 保持双端队列升序
    public static int[] maxSlidingWindow3(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));

        int right = -1;
        int left = -k;
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length-k+1];
        int index=0;

        //形成窗口
        for(int i=0; i<k; i++) {
            right++;
            left++;
            while(!deque.isEmpty() && nums[right] > deque.peekLast()) {
                deque.removeLast();
            }
            deque.addLast(nums[right]);
        }
        System.out.println(deque);
        res[index++] = deque.peekFirst();

        //滑动窗口
        for(int i=k; i<nums.length; i++) {
            right++;
            left++;
            if(nums[left-1] == deque.peekFirst()) {
                deque.removeFirst();
            }

            while(!deque.isEmpty() && nums[right] > deque.peekLast()) {
                deque.removeLast();
            }
            deque.addLast(nums[right]);
            System.out.println(deque);
            res[index++] = deque.peekFirst();
        }

        return res;
    }
}
