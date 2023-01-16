package Algorithm.Array;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author: Felix
 * @date: 2022/12/29
 * @description: 数据流中的中位数
 */
//方法2： 有序集合 + 双指针
//当累计添加的数的数量为奇数时，双指针left和right记录同一个元素
//当累计添加的数的数量为偶数时，双指针left和right分别记录构成中位数的两个元素

//时间复杂度：
//addNum:  O(logn)，其中n为累计添加的数的数量
//findMedian: O(1)
//空间复杂度：O(n) 主要为有序集合的开销

public class MedianFinder2 {
    TreeMap<Integer, Integer> map;  //默认升序
    int size;
    int left;
    int right;

    public static void main(String[] args) {
        MedianFinder2 mf = new MedianFinder2();
        mf.addNum(3);
        mf.addNum(3);
        mf.addNum(4);
        mf.addNum(4);
        System.out.println(mf.findMedian());
        mf.addNum(6);
        mf.addNum(7);
        System.out.println(mf.findMedian());
    }

    public MedianFinder2() {
        map = new TreeMap<>();
        size = 0;
    }

    public void addNum(int num) {
        size++;

        //放入有序集合
        map.put(num, map.getOrDefault(num, 0)+1);

        if(size%2 == 1) {
            int mid = (size+1)/2;
            int index = 0;
            for(int n: map.keySet()) {
                index += map.get(n);
                if(index >= mid) {
                    left = n;
                    right = n;
                    break;
                }
            }
        } else {
            int lefti = size/2;
            int righti = lefti+1;
            boolean lefted = false;
            int index = 0;
            for(int n: map.keySet()) {
                index += map.get(n);
                if(!lefted && index>=lefti) {
                    left = n;
                    lefted = true;
                }

                if(index >= righti) {
                    right = n;
                    break;
                }
            }
        }

    }

    public double findMedian() {
        if(size%2 == 1) {
            return left;
        } else {
            return (left+right)/2.0;
        }
    }
}
