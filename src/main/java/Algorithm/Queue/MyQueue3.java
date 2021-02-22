package Questions.Queue;
import Lib.DListNode;
/**
 * Created by Jeff on 2016/5/1.

  队列
  双向链表实现
 */
public class MyQueue3 {
    public DListNode first, last;

    public static void main(String[] args) {
        MyQueue3 queue = new MyQueue3();
        queue.push_front(1);
        queue.push_back(2);
        System.out.println(queue.pop_back());
        System.out.println(queue.pop_back());
        queue.push_back(3);
        queue.push_back(4);
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
    }

    public MyQueue3() {
        first = last = null;
    }

    //往队头添加
    public void push_front(int item) {
        if (first == null) {
            last = new DListNode(item);
            first = last;
        } else {
            DListNode tmp = new DListNode(item);
            first.prev = tmp;
            tmp.next = first;
            first = first.prev;
        }
    }

    //往队尾添加
    public void push_back(int item) {
        if (last == null) {
            last = new DListNode(item);
            first = last;
        } else {
            DListNode tmp = new DListNode(item);
            last.next = tmp;
            tmp.prev = last;
            last = last.next;
        }
    }

    //从对头取出
    public int pop_front() {
        if (first != null) {
            int item = first.val;
            first = first.next;
            if (first != null)
                first.prev = null;
            else
                last = null;
            return item;
        }
        return -1;
    }

    //从队尾取出
    public int pop_back() {
        if (last != null) {
            int item = last.val;
            last = last.prev;
            if (last != null)
                last.next = null;
            else
                first = null;
            return item;
        }
        return -11;
    }
}

