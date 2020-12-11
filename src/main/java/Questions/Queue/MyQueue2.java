package Questions.Queue;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Created by Defias on 2020/06.
 * Description:  队列 - 链式实现（支持泛型和迭代器）
 */
public class MyQueue2<Item> implements Iterable<Item> {
    private Node<Item> first;    // 队头
    private Node<Item> last;     // 队尾
    private int n;               // 队列大小: 队列中元素个数

    public static void main(String[] args) {
        MyQueue2<String> queue = new MyQueue2<String>();

        queue.enqueue("item1");
        queue.enqueue("item2");
        queue.enqueue("item3");

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println("(" + queue.size() + " left on queue)");
    }

    //链表结点
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    //初始化空队列
    public MyQueue2() {
        first = null;
        last  = null;
        n = 0;
    }


    //队列是否为空
    public boolean isEmpty() {
        return first == null;
    }

    //获取队列的大小/结点数
    public int size() {
        return n;
    }

    //查看队列头
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        return first.item;
    }


    //入队  向队尾添加新结点
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (!isEmpty())
            oldlast.next = last;
        else
            first = last;
        n++;
    }

    //出队 从队头取元素
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
            last = null;   //避免野指针
        return item;
    }


    //迭代器
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    //实现迭代器
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        //从队头开始迭代
        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
}