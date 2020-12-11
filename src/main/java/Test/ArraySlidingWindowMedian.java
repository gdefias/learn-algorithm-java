package Test;
import java.util.Collections;
import java.util.PriorityQueue;
/**
 * Created by Defias on 2017/10/12.
 *
 * Sliding Window Median
 * 滑动窗口中位数
 *
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 5.
 * -3 -1 1  5 3     3 6 7
 * maxheap  minheap
 *
 * https://leetcode.com/problems/sliding-window-median/description/
 *
 */


public class ArraySlidingWindowMedian {
    static PriorityQueue<Double> minHeap = new PriorityQueue<>();
    static PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) {
        int[] A = {1,3,-1,-3,5,3,6,7};
        for(double d: medianSlidingWindow(A, 3)) {
            System.out.print(d+" ");
        }
    }


    public static double[] medianSlidingWindow(int[] nums, int k) {
        if(nums==null || nums.length==0 || nums.length<k)
            return new double[0];

        double[] result = new double[nums.length-k+1];
        int resindex = 0;
        for(int i=0; i<nums.length; i++) {
            if(i>=k) {
                remove(nums[i-k]);
            }
            add((double)nums[i]);
            if(i>=k-1) {
                result[resindex++] = getMedian();
            }
        }
        return result;
    }

    public static void add(double a) {
        maxHeap.add(a);
        minHeap.add(maxHeap.poll());
        if(maxHeap.size()<minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public static double getMedian() {
        if(minHeap.size()==maxHeap.size())
            return (minHeap.peek()+maxHeap.peek())/2.0;
        return maxHeap.peek();
    }

    public static void remove(double a) {
        if(a <= getMedian()) {
            maxHeap.remove(a);
        } else {
            minHeap.remove(a);
        }

        if(minHeap.size()>maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }

        if(maxHeap.size()>minHeap.size()+1) {
            minHeap.add(maxHeap.poll());
        }
    }
}
