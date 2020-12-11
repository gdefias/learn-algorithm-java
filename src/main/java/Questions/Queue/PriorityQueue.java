package Questions.Queue;
import java.util.NoSuchElementException;
import static Questions.Sorts.HeapSort.*;
import static Lib.Base.*;
/**
 * Created by Defias on 2020/06.
 * Description:  优先队列    ----大顶堆实现
 *
 */
public class PriorityQueue {
    private int[] pq;  //堆
    private int n;  //元素个数

    public static void main(String[] args) {

        int[] keys = {12, 23, 345, 1122, 0, 23, 1, 1, 100, 101};
        PriorityQueue maxpq = new PriorityQueue(keys);

        System.out.println(maxpq.max());

        System.out.println(maxpq.delMax());
        System.out.println(maxpq.size());
        System.out.println(maxpq.max());
        System.out.println("--------------");

        maxpq.insert(2000);
        System.out.println(maxpq.size());
        System.out.println(maxpq.max());
        System.out.println("--------------");

        maxpq.insert(2001);
        System.out.println(maxpq.size());
        System.out.println(maxpq.max());
        System.out.println("--------------");

    }


    public PriorityQueue(int initCapacity) {
        pq = new int[initCapacity];
        n = 0;
    }

    public PriorityQueue() {
        this(10);
    }

    public PriorityQueue(int[] keys) {
        pq = new int[keys.length];
        n = keys.length;
        for(int i=0; i<keys.length; ++i) {
            pq[i] = keys[i];
        }

        buildMaxHeap(pq);
    }


    //是否为空
    public boolean isEmpty() {
        return n == 0;
    }

    //优先队列元素个数
    public int size() {
        return n;
    }

    //获得最大元素
    public int max() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        return pq[0];
    }

    //获得最大元素后删除
    public int delMax() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        int res = pq[0];   //堆顶元素
        swap(pq, 0, n-1);  //交换堆顶元素与最后一个元素
        n = n-1;  //元素个数减少
        sinkMaxHeap(pq, 0);  //下沉调整
        return res;
    }

    //插入新元素
    public void insert(int x) {
        if (n == pq.length)
            resize();
        pq[n++] = x;   //插入到当前堆后面
        swimMaxHeap(pq, n-1);    //上浮调整
    }


    //大顶堆上浮操作: 向上调整指定位置元素到合适位置
    private void swimMaxHeap(int[] A, int k) {
        int  parent = getParent(k);

        while (k>0 && (A[k]>A[parent])) {
            swap(A, k, parent);
            k = parent;
            parent = getParent(k);
        }
    }

    //大顶堆下沉操作: 向下调整指定元素到合适位置
    private void sinkMaxHeap(int[] A, int k) {

        int leftchild = getChild(k)[0];
        while (leftchild <= n-1) {  //确保要下沉的第k个元素至少有1个孩子元素

            int p = leftchild;  //p指向较大的孩子
            int rightchild = getChild(k)[1];

            if((rightchild <= n-1) && (A[rightchild] > A[leftchild])) {
                p = rightchild;
            }

            if (A[k] >= A[p])
                break;       //无需调整
            swap(A, k, p);   //需要调整
            leftchild = 2*p;   //新的左孩子
        }
    }

    //扩容队列大小
    private void resize() {
        resize(2);
    }

    private void resize(int times) {
        assert times > 1;
        int  capacity  = times * n;
        int[] temp = new int[capacity];
        for (int i=0; i<n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


}
