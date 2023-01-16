package Algorithm.Sort.NotCompare;

/**
 * Created by Jeff on 2016/4/25.
 *
 * 基数排序
 *
 * 时间复杂度: O(n)   （d为常数且k=O(n)时）
 *           Θ(d(n+k)) （n表示待排序列的规模，d表示待排序列的最大位数，k表示每一位数有k个可能的取值）
 * 稳定排序
 * 非比较排序
 *
 * 基本思路：也称为基于关键字的排序，例如针对数值排序，个位、十位、百位就是关键字。针对日期数据的排序：年、月、日、时、分、秒就是关
 * 键字。「基数排序」用到了「计数排序」

 */

import static  Lib.Base.*;

public class RadixSort {
    public static void main(String[] args) {
        int A[] = {8, 59, 3, 542, 748, 10, 61, 214, 3, 3, 154, 61, 616};  //需要为正整数
        boolean isdebug = true;

        System.out.printf("before sort:");
        for (int i=0; i<A.length; i++)
            System.out.print(A[i] + " ");

        radixSort(A, isdebug);	//基数排序

        System.out.printf("\nafter  sort:");
        for (int i=0; i<A.length; i++)
            System.out.print(A[i] + " ");

    }

    /*
     * 基数排序
     */
    public static void radixSort(int[] A, boolean isdebug) {
        if(A==null || A.length<2) {
            return;
        }

        int exp;	//指示按哪一位进行排序（按个位进行排序时：exp=1；按十位进行排序时：exp=10；...）
        int max = getMaxMin(A)[0];

        //从个位开始依次到最高位为止分别进行计数排序
        for (exp=1; max/exp>0; exp*=10) {
            helper(A, exp);

            if(isdebug) {
                System.out.println("\nexp=" + exp + ":");
                for(int i=0; i<A.length; ++i) {
                    System.out.print(A[i] + " ");
                }
                System.out.println("\n-----");
            }
        }
    }


    //按位进行计数排序（也即: 一个桶放一个元素的桶排序）  基数为：10（十进制数）
    private static void helper(int[] A, int exp) {
        int radix = 10;
        int[] output = new int[A.length];
        int[] buckets = new int[radix];  //10个桶，每个桶存0-9中的1个数
        for(int i=0; i<radix; i++) {  //初始化
            buckets[i] = 0;
        }

        //入桶：对各数指定位的数进行计数
        for (int i=0; i<A.length; i++) {
            buckets[(A[i]/exp)%10]++;
        }


        for (int i=1; i<radix; i++) {
            buckets[i] += buckets[i-1];
        }

        //倒出
        for (int i=A.length-1; i>=0; i--) {
            output[buckets[(A[i]/exp)%10]-1] = A[i];    //因为output的索B从0开始存，所以需要减1
            buckets[(A[i]/exp)%10]--;
        }

        //将排序好的数据赋值给原数组
        for (int i=0; i<output.length; i++) {
            A[i] = output[i];
        }
    }


}