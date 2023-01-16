package Algorithm.Array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Defias on 2017/9/27.

 https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/

 最小的k个数
 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

 输入：arr = [3,2,1], k = 2
 输出：[1,2] 或者 [2,1]

 0 <= k <= arr.length <= 10000
 0 <= arr[i] <= 10000
 */

//O(n)
public class GetLeastNumbers {

    public static void main(String[] args) {
        GetLeastNumbers O = new GetLeastNumbers();
        int[] A = {5, 4, 2, 3, 7, 8, 10 ,9, 1};
//        int[] A = {3, 2, 1};
        int[] B = O.getLeastNumbers3(A, 4);
        for(int b: B) {
            System.out.println(b);
        }
    }

    //方法1：快排思想  时间复杂度：O(N)
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k<1 || k>arr.length) {
            return new int[0];
        }

        return  helper(arr, 0, arr.length-1, k);
    }

    public int[] helper(int[] arr, int left, int right, int k) {
        int mid = partition(arr, left, right);
        if(mid==k-1) {
            return Arrays.copyOfRange(arr, 0, mid+1);
        } else if(mid>k-1) {
            return helper(arr, left, mid-1, k);
        } else {
            return helper(arr, mid+1, right, k);
        }
    }

    // 快排切分
    private int partition(int[] A, int left, int right) {
        int provit = A[left];  //选基准元素(第一个元素)
        int lo = left;

        for(int i=left+1; i<=right; i++) {
            if(A[i] < provit) {
                lo++;
                swap(A, lo, i);
            }
        }
        swap(A, lo, left);
        return lo;
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }


    //方法2：堆  时间复杂度：O(NlogK)
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k<1 || k>arr.length) {
            return new int[0];
        }
        int[] res = new int[k];

        PriorityQueue<Integer> maxQ = new PriorityQueue<>((v1, v2) -> {
            return v2-v1;
        });

        for(int i=0; i<arr.length; i++) {
            if(i<k) {
                maxQ.offer(arr[i]);
            } else {
                if(arr[i] < maxQ.peek()) {
                    maxQ.poll();
                    maxQ.offer(arr[i]);
                }
            }
        }

        int i = 0;
        for(int item: maxQ) {
            res[i] = item;
            i++;
        }
        return res;
    }


    //方法3：数据范围有限时直接计数排序 O(N)
    public int[] getLeastNumbers3(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k<1 || k>arr.length) {
            return new int[0];
        }
        int[] res = new int[k];
        int max = getMax(arr);
        int[] A = new int[max+1];

        for(int i=0; i<arr.length; i++) {
            A[arr[i]]++;
        }

        int total = 0;
        int index = 0;
        for(int i=0; i<A.length; i++) {
            int count = A[i];
            total += count;
            if(total > k) {
                count -= (total-k);
            }

            for(int j=0; j<count; j++) {
                res[index++] = i;
            }
        }

        return res;
    }

    public int getMax(int[] arr) {
        int max = arr[0];
        for(int i=1; i<arr.length; i++) {
            max = arr[i]>max?arr[i]:max;
        }
        return max;
    }

}
