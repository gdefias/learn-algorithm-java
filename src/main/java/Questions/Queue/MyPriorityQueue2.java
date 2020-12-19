package Questions.Queue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Created by Defias on 2020/06.
 * Description:  优先队列     ----小顶堆实现（支持泛型）
 */
public class MyPriorityQueue2<Key> implements Iterable<Key> {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public static void main(String[] args) {
        MyPriorityQueue2<String> pq = new MyPriorityQueue2<String>();

        pq.insert("231");
        pq.insert("123");
        pq.insert("321");
        System.out.println(pq.delMin() + " ");
        System.out.println("(" + pq.size() + " left on pq)");
    }

    //各种构造器
    public MyPriorityQueue2(int initCapacity) {
        pq = (Key[])new Object[initCapacity + 1];
        n = 0;
    }

    public MyPriorityQueue2() {
        this(1);
    }

    public MyPriorityQueue2(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MyPriorityQueue2(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MyPriorityQueue2(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i+1] = keys[i];
        for (int k = n/2; k >= 1; k--)
            sink(k);
        assert isMinHeap();
    }

    //是否为空
    public boolean isEmpty() {
        return n == 0;
    }

    //优先队列大小
    public int size() {
        return n;
    }

    //获取最小值（堆顶元素）
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    //扩容
    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


    //插入元素
    public void insert(Key x) {
        if (n == pq.length - 1)
            resize(2 * pq.length);

        pq[++n] = x;  //索引从1开始
        swim(n);   //上浮操作
        assert isMinHeap();  //检查是否小顶堆
    }



    //删除元素
    public Key delMin() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        exch(1, n);  //交换堆顶与最后一个元素
        Key min = pq[n--];  //取出并调整大小
        sink(1);  //下沉操作
        pq[n+1] = null;         //避免野指针 更好的进行垃圾回收
        if ((n > 0) && (n == (pq.length - 1) / 4))  //扩容
            resize(pq.length  / 2);
        assert isMinHeap();
        return min;
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    //上浮
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    //下沉
    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1))
                j++;
            if (!greater(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/

    //比较大小
    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    //交换
    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    //是否是小顶堆
    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    private boolean isMinHeap(int k) {
        if (k > n)
            return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))
            return false;
        if (right <= n && greater(k, right))
            return false;
        return isMinHeap(left) && isMinHeap(right);
    }

    //迭代器
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    //迭代器实现
    private class HeapIterator implements Iterator<Key> {
        private MyPriorityQueue2<Key> copy;

        public HeapIterator() {
            if (comparator == null)
                copy = new MyPriorityQueue2<Key>(size());
            else
                copy = new MyPriorityQueue2<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()  {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return copy.delMin();
        }
    }

}