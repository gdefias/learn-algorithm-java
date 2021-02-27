package Algorithm.Array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Defias on 2020/07.
 * Description:  数据流中的中位数

 https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/

 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
 那么中位数就是所有数值排序之后中间两个数的平均值。

 例如，
 [2,3,4] 的中位数是 3
 [2,3] 的中位数是 (2 + 3) / 2 = 2.5

 设计一个支持以下两种操作的数据结构：
 void addNum(int num) - 从数据流中添加一个整数到数据结构中
 double findMedian() - 返回目前所有元素的中位数


 输入：
 ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 [[],[1],[2],[],[3],[]]

 输出：
 [null,null,null,1.50000,null,2.00000]

 */

//小顶堆（存放一半或较多的一半）+大顶堆
public class MedianFinder {

    PriorityQueue<Integer> minqueue;
    PriorityQueue<Integer> maxqueue;

    public MedianFinder() {
        minqueue = new PriorityQueue<Integer>();
        maxqueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
    }

    public void addNum(int num) {
        int n = minqueue.size()+maxqueue.size();

        if(n!=0 && !minqueue.isEmpty() && num<minqueue.peek()) {
            maxqueue.offer(num);
            num = maxqueue.poll();
        }

        minqueue.offer(num);
        if(minqueue.size()-maxqueue.size()>1) {
            num = minqueue.poll();
            maxqueue.offer(num);
        }
    }

    public double findMedian() {
        int n = minqueue.size()+maxqueue.size();
        if((n&1)==1) {
            return minqueue.peek();
        }

        return (minqueue.peek()+maxqueue.peek())/2.0;

    }
}
