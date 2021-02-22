package Questions.Queue;
import java.util.NoSuchElementException;
import  static Lib.Base.*;
/**
 * @author yzh

  队列(先进先出)    ---链式实现
 */

public class MyQueue {
    private ListNode head;   //队列头
    private ListNode tail;   //队列尾


    public static void main(String[] args) {
        MyQueue queue = mockLinkedQueue();
        queue.print();
        System.out.println("\n------");

        System.out.println(queue.isEmpty());

        System.out.println(queue.peekHead());

        System.out.println("dequeued: " + queue.dequeue());
        queue.print();
        System.out.println("\n------");

        System.out.println("enqueue: " + queue.enqueue(6));
        queue.print();
        System.out.println("\n------");

        queue.clear();
        System.out.println(queue.isEmpty());
    }

    //制造一个队列
    public static MyQueue mockLinkedQueue() {
        MyQueue queue = new MyQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        return queue;
    }

    //入队 向队尾添加结点
    public int enqueue(int val) {
        ListNode newTail = new ListNode(val);

        if (isEmpty()) {
            head = tail = newTail;
        } else {
            tail.next = newTail;
            tail = newTail;
        }
        return val;
    }

    //出队  从队头取元素
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int ret = head.val;
        head = head.next;
        if(isEmpty()) {
            tail = null;
        }
        return ret;
    }

    //查看队列头的值
    public int peekHead() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.val;
    }

    //队列是否为空
    public boolean isEmpty() {
        return head == null;
    }

    //清空队列
    public void clear() {
        head = null;
        tail = null;
    }

    //打印队列
    public void print() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
    }

}
