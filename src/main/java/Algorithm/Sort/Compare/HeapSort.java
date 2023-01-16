package Algorithm.Sort.Compare;

/**
 * Created by Defias on 2016/4/25.
 *
 * 堆排序 - 大顶堆
 *
 * 时间复杂度：O(nlgn)
 * 空间复杂度: O(1)
 *
 * 不稳定排序
 */
import static  Lib.Base.*;

public class HeapSort {
	public static void main(String[] args) {
		int[] A = new int[] {0, 12,3,4,6,23,3,445,67,89,7};

		for(int n: A) {
			System.out.println(n);
		}
		System.out.println("---");

		heapSort(A);

        for(int n: A) {
            System.out.println(n);
        }
	}

	//升序排序
	public static void heapSort(int[] A) {
//		buildMaxHeap(A);
		buildMaxHeap2(A);


//		for(int i=A.length-1; i>=1; --i) {
//			swap(A, 0, i);   //弹出大顶堆堆顶元素放在最后
//			rebuildMaxHeap(A, 0, i-1);  //除最后元素外将剩下的元素重新建造成一个大顶堆
//		}
    }

	//建大顶堆   O(nlgn)  上浮
	public static void buildMaxHeap(int[] A) {
		for(int i=1; i<A.length; ++i) {
			int child = i;
			int parent = getParent(child);

			while(child>0 && A[child]>A[parent]) {  //符合条件即需要进行调整
				swap(A, child, parent);
				child = parent;
				parent = getParent(child);
			}
		}
	}

	//调用堆调整来建大顶堆   O(nlgn)
	public static void buildMaxHeap2(int[] A) {
		//A中索引 0 ~ ⌊n/2⌋-1 的元素都是内部结点，⌊n/2⌋ ~ n-1 的元素都是叶子结点
		for(int i=A.length/2-1; i>=0; --i) {
			rebuildMaxHeap(A, i, A.length-1);
		}
	}

	//大顶堆维护（前提：parent结点的左右孩子为根的子树都已经是大顶堆） 下沉  O(lgn)
	public static void rebuildMaxHeap(int[] A, int parent, int last) {
		int leftchile = getChild(parent)[0];
		int rightchile = getChild(parent)[1];

		//从父、左孩子、右孩子 三者中找最大的那一个
		int largest = parent;
		if(leftchile<=last && A[leftchile] > A[largest]) {
			largest = leftchile;
		}

		if(rightchile<=last && A[rightchile] > A[largest]) {
			largest = rightchile;
		}

		if(largest != parent) {
			swap(A, parent, largest);
			rebuildMaxHeap(A, largest, last);
		}
	}

	public static void rebuildMaxHeap(int[] A, int parent) {
		int last = A.length-1;
		rebuildMaxHeap(A, parent, last);
	}


	//根据父结点索引获得左右孩子结点的索引(索引从0开始)
	public static int[] getChild(int parent) {
		int[] childs = new int[2];
		childs[0] = parent*2+1;
		childs[1] = parent*2+2;
		return childs;
	}

	//根据左孩子或右孩子结点索引获得父结点索引(索引从0开始)
	public static int getParent(int child) {
		if (child<=0) {
			return child;
		}
		return (child-1) >> 1;
	}
}
