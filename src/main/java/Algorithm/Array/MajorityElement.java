package Algorithm.Array;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description: 数组中出现次数超过一半的数字 （众数 Majority Element）
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/

 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 可以假设数组是非空的，并且给定的数组总是存在多数元素。

 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 输出: 2

 1 <= 数组长度 <= 50000
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] A = {1, 5, 4, 2, 1, 3, 7, 8, 10 ,9, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(majorityElement(A));
    }

    //方法1：摩尔投票法
    public static int majorityElement(int[] nums) {

        int x = 0; //众数
        int v = 0; //累计票数

        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            if(v==0) {
                x = num;
            }

            if(num == x) {
                v += 1;
            } else {
                v += -1;
            }
        }
        return x;
    }


    //方法2：计数
    public static int majorityElement1(int[] A) {
        if(A==null || A.length==0) {
            throw new ArithmeticException();
        }
        int max = maxV(A);
        int[] B = new int[max+1];
        for(int i=0; i<A.length-1; i++) {
            B[A[i]]++;
        }
        int maxindex = 0;
        max = B[0];
        for(int i=0; i<B.length-1; i++) {
            if(B[i]>max) {
                max = B[i];
                maxindex = i;
            }
        }
        return maxindex;
    }

    public static int maxV(int[] A) {
        int m = A[0];
        for(int a: A) {
            if(a>m) {
                m = a;
            }
        }
        return m;
    }


    //方法3: 排序
    public static int majorityElement3(int[] A) {
        Arrays.sort(A);
        return A[A.length/2];
    }



    //方法4：分治
    public static int majorityElement4(int[] A) {
        if(A==null||A.length==0) {
            return -1;
        }
        return helper(A, 0,  A.length-1);
    }

    public static int helper(int[] A, int left, int right) {
        if(left==right) {
            return A[left];
        }

        int mid = left+(right-left)/2;

        int leftval = helper(A, left, mid);
        int rightval = helper(A, mid+1, right);

        if(leftval==rightval) {
            return leftval;
        }

        int leftcount = 0;
        int rightcount = 0;
        for(int i=0; i<A.length; i++) {
            if(A[i]==leftval) {
                leftcount++;
            } else if(A[i]==rightval) {
                rightcount++;
            }
        }

        if(leftcount>=rightcount) {
            return leftval;
        } else {
            return rightval;
        }
    }


    //方法5：快排的思想
    public static int majorityElement5(int[] A) {
        if(A==null || A.length==0) {
            throw new ArithmeticException();
        }

        int start = 0;
        int end = A.length-1;
        int mid = A.length/2;
        int index = partiton(A, start, end);
        while(index != mid) {
            if(index>mid) {
                end = index-1;
                index = partiton(A, start, end);
            }
            if(index<mid) {
                start = index+1;
                index = partiton(A, start, end);
            }
        }
        return A[mid];
    }

    public static int partiton(int[] A, int left, int right) {
        int i = left;
        int j = right+1;
        int V = A[left];

        while(true) {
            while(A[++i]<V) {
                if (i==right)
                    break;
            }

            while(A[--j]>V) {
                if(j==left)
                    break;
            }

            if(i>=j) {
                break;
            }
            swap(A, i, j);
        }
        swap(A, left, j);
        return j;
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
