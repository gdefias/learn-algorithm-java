package Algorithm.Sort.Compare;

import static Algorithm.Sort.Compare.HeapSort.getChild;
import static Algorithm.Sort.Compare.HeapSort.getParent;
import static Lib.Base.swap;

/**
 * @author: Felix
 * @date: 2022/12/24
 * @description:
 *
 * 堆排序 - 小顶堆
 *
 * 时间复杂度：O(nlgn)
 * 空间复杂度: O(1)
 *
 * 不稳定排序
 */
public class HeapSort2 {
    public static void main(String[] args) {
        int[] A = new int[] {0, 12,3,4,6,23,3,445,67,89,7};

        heapSort(A);

        for(int n: A) {
            System.out.println(n);
        }
    }

    //降序排序
    public static void heapSort(int[] A) {
//        buildMinHeap(A);
		buildMinHeap2(A);
        for(int i=A.length-1; i>=1; --i) {
            swap(A, 0, i);   //弹出小顶堆堆顶元素放在最后
            rebuildMinHeap(A, 0, i-1);  //除最后元素外将剩下的元素重新建造成一个小顶堆
        }
    }

    //建小顶堆  上浮
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

	//调用堆调整来建小顶堆   O(nlgn)
	public static void buildMinHeap2(int[] A) {
		//A中索引 0 ~ ⌊n/2⌋-1 的元素都是内部结点，⌊n/2⌋ ~ n-1 的元素都是叶子结点
		for(int i=A.length/2-1; i>=0; --i) {
			rebuildMinHeap(A, i, A.length-1);
		}
	}


	//小顶堆维护（parent结点的左右孩子树都已经是小顶堆）  下沉
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
