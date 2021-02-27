package Algorithm.Sorts;

/**
 * Created by Defias on 2016/4/25.
 *
 * 堆排序
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

		heapSort(A);

        for(int n: A) {
            System.out.println(n);
        }
	}


	//升序排序
	public static void heapSort(int[] A) {
		buildMaxHeap2(A);
		for(int i=A.length-1; i>=1; --i) {
			swap(A, 0, i);   //弹出大顶堆堆顶元素放在最后
			rebuildMaxHeap(A, 0, i-1);  //除最后元素外将剩下的元素重新建造成一个大顶堆
		}
    }

    //建大顶堆   O(nlgn)
    public static void buildMaxHeap(int[] A) {
		for(int i=1; i<A.length; ++i) {
			int child = i;
			int parent = getParent(child);

			while(child>0 && A[child]>A[parent]) {
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

	//大顶堆维护（前提：parent结点的左右孩子为根的子树都已经是大顶堆）  O(lgn)
	public static void rebuildMaxHeap(int[] A, int parent, int last) {
		int leftchile = getChild(parent)[0];
		int rightchile = getChild(parent)[1];

		int largest = parent;
		if(leftchile<=last && A[leftchile] > A[largest])
			largest = leftchile;

		if(rightchile<=last && A[rightchile] > A[largest])
			largest = rightchile;

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

	//降序排序
	public static void heapSort2(int[] A) {
		buildMinHeap(A);
		for(int i=A.length-1; i>=1; --i) {
			swap(A, 0, i);   //弹出小顶堆堆顶元素放在最后
			rebuildMinHeap(A, 0, i-1);  //除最后元素外将剩下的元素重新建造成一个小顶堆
		}
	}

	//建小顶堆
	public static void buildMinHeap(int[] A) {
		for(int i=1; i<A.length; ++i) {
			int child = i;
			int parent = getParent(child);

			while(child>0 && A[child]<A[parent]) {
				swap(A, child, parent);
				child = parent;
				parent = getParent(child);
			}
		}
	}


	//小顶堆维护（parent结点的左右孩子树都已经是小顶堆）
	public static void rebuildMinHeap(int[] A, int parent, int last) {
		int leftchile = getChild(parent)[0];
		int rightchile = getChild(parent)[1];

		int smallest = parent;
		if(leftchile<=last && A[leftchile] < A[smallest])
			smallest = leftchile;

		if(rightchile<=last && A[rightchile] < A[smallest])
			smallest = rightchile;

		if(smallest != parent) {
			swap(A, parent, smallest);
			rebuildMinHeap(A, smallest, last);
		}
	}

	public static void rebuildMinHeap(int[] A, int parent) {
		int last = A.length-1;
		rebuildMinHeap(A, parent, last);
	}

	public static void rebuildMinHeap2(int[] A, int parent) {
		int last = A.length-1;
		int leftchile = getChild(parent)[0];
		int rightchile = getChild(parent)[1];

		int smallest = parent;
		if(leftchile<=last && A[leftchile] < A[smallest])
			smallest = leftchile;

		if(rightchile<=last && A[rightchile] < A[smallest])
			smallest = rightchile;

		if(smallest != parent) {
			swap(A, parent, smallest);
			rebuildMinHeap(A, smallest, last);
		}
	}
}
