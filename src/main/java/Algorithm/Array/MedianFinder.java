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
 [2,3,4]的中位数是 3
 [2,3] 的中位数是 (2 + 3) / 2 = 2.5

 设计一个支持以下两种操作的数据结构：
 void addNum(int num) - 从数据流中添加一个整数到数据结构中
 double findMedian() - 返回目前所有元素的中位数


 输入：
 ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 [[],[1],[2],[],[3],[]]

 输出：
 [null,null,null,1.50000,null,2.00000]

 提示:
 -105 <= num <= 105
 在调用 findMedian 之前，数据结构中至少有一个元素
 最多 5 * 104 次调用 addNum 和 findMedian

 */

//方法1： 优先队列/堆
//大顶堆  记录小于或等于中位数的数 （存放一半或较多的一半）
//小顶堆  记录大于中位数的数  （存放另一半）
//   尾--maxQ--头    头--minQ--尾
//     [3   5]        [6  10]
//确保大顶堆中最大元素（堆顶元素）始终小于或等于 小顶堆中最小元素（堆顶元素）


//时间复杂度：
//addNum:  O(logn)，其中n为累计添加的数的数量
//findMedian: O(1)
//空间复杂度：O(n) 主要为优先队列的开销

public class MedianFinder {

    PriorityQueue<Integer> minqueue;
    PriorityQueue<Integer> maxqueue;

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }


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
        //优先往大顶堆里面放
        if(!maxqueue.isEmpty() && num > maxqueue.peek()) {
            minqueue.offer(num);
            num = minqueue.poll();
        }
        maxqueue.offer(num);

        //放多了就调整到小顶堆中去
        if(maxqueue.size() > minqueue.size() + 1) {
            num = maxqueue.poll();
            minqueue.offer(num);
        }
    }

    public double findMedian() {
        int n = minqueue.size() + maxqueue.size();
        if((n&1)==1) { //总数为奇数
            return maxqueue.peek();  //大顶堆堆顶元素
        }

        //总数为偶数  两个堆顶元素的平均值
        return (minqueue.peek()+maxqueue.peek())/2.0;
    }
}
