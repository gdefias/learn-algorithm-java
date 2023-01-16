package Algorithm.Array;

import java.util.*;

/**
 * @author: Felix
 * @date: 2023/1/3
 * @description: 前 K 个高频元素

   https://leetcode.cn/problems/top-k-frequent-elements/

   https://leetcode.cn/problems/g5c51o/

    给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

    输入: nums = [1,1,1,2,2,3], k = 2
    输出: [1,2]

    输入: nums = [1], k = 1
    输出: [1]


    提示：
    1 <= nums.length <= 105
    k 的取值范围是 [1, 数组中不相同的元素的个数]
    题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的


    进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */
public class TopKFrequent {

    public static void main(String[] args) {
        TopKFrequent O = new TopKFrequent();
        int[] nums = new int[] {1,1,1,2,2,3};
        int[] res = O.topKFrequent2(nums, 2);

        for(int i=0; i<res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    //方法1：堆  维护k个元素的小顶堆
    //时间复杂度：O(Nlogk)  空间复杂度：O(N)
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }

        Queue<int[]> minQ = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for(Integer num: map.keySet()) {
            int[] numi = new int[2];
            numi[0] = num;
            numi[1] = map.get(num);

            if(minQ.size() < k) {
                minQ.offer(numi);
            } else {
                if(minQ.peek()[1] < numi[1]) {
                    minQ.poll();
                    minQ.offer(numi);
                }
            }
        }

        int index = 0;
        while(!minQ.isEmpty()) {
            res[index++] = minQ.poll()[0];
        }

        return res;
    }


    //方法2：基于快排  分治思想
    //时间复杂度：O(N^2)  空间复杂度：O(N)
    public int[] topKFrequent2(int[] nums, int k) {
        int[] res = new int[k];
        int index = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }

        List<int[]> values = new ArrayList<>();
        for(Integer num: map.keySet()) {
            int[] numi = new int[2];
            numi[0] = num;
            numi[1] = map.get(num);
            values.add(numi);
        }


        ksort(values, 0, values.size()-1, k, res, index);

        return res;
    }

    public void ksort(List<int[]> values, int left, int right, int k, int[] res, int index) {
        int mid = partition(values, left, right);
        if(mid-left+1 == k) {
            for(int i=left; i<=mid; i++) {
                res[index++] = values.get(i)[0];
            }
        } else if(mid-left+1 > k) {
            ksort(values, left, mid-1, k, res, index);
        } else {
            for(int i=left; i<=mid; i++) {
                res[index++] = values.get(i)[0];
            }
            ksort(values, mid+1, right, k-(mid-left+1), res, index);
        }

    }

    public int partition(List<int[]> values, int left, int right) {
        int pivot = values.get(left)[1];
        int lo = left;
        for(int i=left+1; i<=right; i++) {
            if(values.get(i)[1] >= pivot) {
                lo++;
                Collections.swap(values, lo, i);
            }
        }
        Collections.swap(values, lo, left);

        return lo;
    }


}
