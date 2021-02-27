package Algorithm.Sorts;

/**
 * Created by Jeff on 2016/4/25.
 *
 * 希尔排序（分组插入排序）
 * 在每次的排序的时候都把数组拆分成若干个序列，一个序列的相邻的元素索引相隔的固定的距离gap，每一轮对这些序列进行冒泡或者插入排序，
 * 然后再缩小gap得到新的序列，一一排序，直到gap为1
 *
 *
 * 时间复杂度: 很大程度上取决于它使用的间隔，在实际使用中，其时间复杂度仍然是一个悬而未决的问题，基本在O(n^2)和O(n^(4/3))之间
 * 空间复杂度：O(1)
 */
import static  Lib.Base.*;

public class ShellSort {
	public static void main(String[] args) {
		int[] A = new int[] {8, 59, 3, 542, 748, 10, 61, 214, 3, 3, 154, 61, 616};

		shellSort2(A);

		for(int n: A) {
			System.out.println(n);
		}
	}


	public static void shellSort1(int[] A) {
		if (A==null || A.length<2) {
			return;
		}

		int gap = A.length >> 1;   //初始间隔取A长度的一半，gap越大单个序列中数值个数越少，序列的个数等于grap
		while (gap > 0) {  //直到gap为1进行一次全局插入排序结束
			for(int i=0; i<gap; ++i) {  //对每个序列进行插入排序
				for(int j=i+gap; j<A.length; j+=gap) {  //插入排序
					for(int k=j; k>i; k-=gap) {
						if(A[k]<A[k-gap]) {
							swap(A, k, k-gap);
						}
					}
				}
			}
			gap >>= 1;  //减少gap为原来的一半
		}
	}


	public static void shellSort2(int[] A) {
		int N = A.length;
		int h = 1;
		while (h<N/3) {
			h = h*3+1;  //1、4、13、40、121、364、1093...
		}

		while (h>=1) {
			for(int i=h; i<N; i++) {
				//将a[i]插入到a[i-h], a[i-2*h], a[i-3*h] ...中
				for(int j=i; (j>=h) && (A[j]<A[j-h]); j-=h) {
					swap(A, j, j-h);
				}
			}
			h = h/3;
		}
	}
}
