package Algorithm.Sorts;

/**
 * Created by Defias on 2020/06.
 * Description: 桶排序
 *
 * 平均或最佳情形时间复杂度：O(n)
 * 最坏情形（所有元素都落在同一个桶中）时间复杂度： 桶内数据的排序算法（也可递归调用桶排序）时间复杂度
 *
 * 空间复杂度也是O(n)
 * 稳定排序（前提是桶内排序是稳定的）
 */

import static  Lib.Base.*;
import java.util.ArrayList;
import java.util.List;

public class BucketSort {

    public static void main(String[] args) {
        int[] A = new int[] {2,12,3,4,6,23,3,45,67,89,7};

        int[] B = bucketSort(A);
        for(int n: B) {
            System.out.println(n);
        }
    }

    public static int[] bucketSort(int[] A) {
        if (A == null || A.length < 2) {
            return A;
        }
        int[] B = new int[A.length];

        //找出最大值
        int max = getMaxMin(A)[0];
        int min = getMaxMin(A)[1];

        helper(A, B, max, min);
        return B;
    }

    public static void helper(int[] A,  int[] B, int max, int min) {
        //定义桶
        int bucketsize = 3;  //桶大小，每个桶可以最多放bucketsize个不同元素（相同的元素可以放的个数不限）
        int Alen = max-min+1;

        //所需桶的数量
        int bucketnums = (Alen%bucketsize == 0)?(Alen/bucketsize):(Alen/bucketsize+1);

        List<Integer>[] buckets = new ArrayList[bucketnums];  //使用列表数组
        for (int i=0; i<bucketnums; i++) {
            buckets[i] = new ArrayList<>();
        }

        //入桶
        for (int value : A) {
            //确定放入哪个桶（相同的值会放在同一个桶中）
            int bucketnum = (value-min)/bucketsize;
            buckets[bucketnum].add(value);
        }

        //桶内排序
        for(List<Integer> bucket:buckets) {
            bucket.sort(null);
        }

        //倒出
        int cur = 0;
        for(List<Integer> bucket:buckets) {
            for(Integer value: bucket) {
                B[cur++] = value;
            }
        }
    }


    //一个桶内只放一个元素，桶内的元素不再需要排序，桶排序退化为计数排序
    public static int[] bucketSort2(int[] A) { // 要求A中数据>=0 （如果A中有数据小于0，解决方案：1、使用偏移量 2、使用HashTable）
        if (A == null || A.length < 2) {
            return A;
        }
        int[] B = new int[A.length]; //使用整型数组

        //找出最大值
        int max = getMaxMin(A)[0];

        helper2(A, B, max);
        return B;
    }

    public static void helper2(int[] A,  int[] B, int max) {
        //定义桶
        int[] buckets = new int[max+1];
        for (int i=0; i < max+1; ++i) {
            buckets[i] = 0;
        }

        //入桶
        for (int j=0; j<A.length; ++j) {
            buckets[A[j]]++;
        }

        //倒出
        for (int i=0, j=0; i<max+1; ++i) {
            while (buckets[i]-- != 0) {
                B[j] = i;
                ++j;
            }
        }
    }
}
