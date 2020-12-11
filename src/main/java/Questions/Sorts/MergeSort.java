package Questions.Sorts;

/**
 * Created by Jeff on 2016/3/27.
 *
 * 归并排序（分治思想）
 * 时间复杂度：O(nlgn)
 * 空间复杂度：O(n)
 *
 * 先局部有序，再整体有序
 * 稳定排序
 *
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] A = new int[] {12,334,545,6,78,34,233,444,5,23,54,73,3,2,12,46};

        Sort(A);

        for(int n: A) {
            System.out.println(n);
        }
    }

	public static void Sort(int[] A) {
		if(A==null || A.length<2) {
			return;
		}

		int left = 0;
		int right = A.length-1;

		int[] tmp = new int[A.length];
		helper(A, tmp, left, right);

	}

	public static void helper(int[] A, int[] tmp, int left, int right) {
		if(left>=right) {
			return;
		}

		int midder = left + (right-left)/2;
		helper(A, tmp, left, midder);
		helper(A, tmp, midder+1, right);

		merge(A, tmp, left, midder, right);
	}

	public static void merge(int[] A, int[] tmp, int left, int midder, int right) {
		int i = left;
		int j = midder+1;

		//将局部排好序的A转为整体排好序的tmp
		for(int k=left; k<=right; ++k) {
			if(i<=midder && (j>right || A[i]<A[j])) {  //j>right说明右半边的数取完了
				tmp[k] = A[i];
				++i;
			} else {
				tmp[k] = A[j];
				++j;
			}
		}

		//整体排好序的tmp拷贝到原数组A中
		for(int k=left; k<=right; ++k) {
			A[k] = tmp[k];
		}
	}
}
