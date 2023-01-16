package Algorithm.Array;
import java.util.TreeMap;

/**
 * @author: Felix
 * @date: 2022/12/29
 * @description: 数据流中的中位数
 *
 *  进阶
 *  如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 *    可以使用建立长度为 101 的桶，每个桶分别统计每个数的出现次数，同时记录数据流中总的元素数量，每次查找中位数时，先计算出中
 *    位数是第几位，从前往后扫描所有的桶得到答案。这种做法相比于对顶堆做法，计算量上没有优势，更多的是空间上的优化
 *
 *  如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *    和上一问解法类似，对于 1% 采用哨兵机制进行解决即可，在常规的最小桶和最大桶两侧分别维护一个有序序列，即建立一个代表负无穷和正
 *    无穷的桶

 */

//桶（数组--有限数量的桶、有序序列--无限数量的桶）
//时间复杂度：
//addNum： 最好O(1)（数据流中的数足够均匀地分布在[0，100]内）
//         最差O(logn)（数据流中的数分布在[0，100]之外）
//findMedian： O(n)

public class MedianFinder3 {
    TreeMap<Integer, Integer> head = new TreeMap<>();
    TreeMap<Integer, Integer> tail = new TreeMap<>();
    int[] arr = new int[101];
    int a, b, c;  //分别记录head、arr、tail三个集合中的元素数量

    public static void main(String[] args) {
        MedianFinder3 mf = new MedianFinder3();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }


    public void addNum(int num) {
        if (num >= 0 && num <= 100) {
            arr[num]++;  //计数
            b++;
        } else if (num < 0) {
            head.put(num, head.getOrDefault(num, 0) + 1); //计数
            a++;
        } else if (num > 100) {
            tail.put(num, tail.getOrDefault(num, 0) + 1);
            c++;
        }
    }

    public double findMedian() {
        int size = a + b + c;
        if (size % 2 == 0)
            return (find(size / 2) + find(size / 2 + 1)) / 2.0;
        return find(size / 2 + 1);
    }

    //寻找第n个数
    int find(int n) {
        if (n <= a) {
            for(int num : head.keySet()) {
                n -= head.get(num);
                if (n <= 0)
                    return num;
            }
        } else if (n <= a + b) {
            n -= a;
            for(int i = 0; i <= 100; i++) {
                n -= arr[i];
                if (n <= 0)
                    return i;
            }
        } else {
            n -= a + b;
            for(int num : tail.keySet()) {
                n -= tail.get(num);
                if (n <= 0)
                    return num;
            }
        }
        return -1; // never
    }
}
