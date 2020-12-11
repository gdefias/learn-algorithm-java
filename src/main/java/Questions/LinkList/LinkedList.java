package Questions.LinkList;
import java.util.NoSuchElementException;
import static  Lib.Base.*;
/**
 * Created by Defias on 2017/9/15.
 *
 * 单链表
 */

//单链表类
public class LinkedList {

    private ListNode head;


    public static void main(String[] args) {
        LinkedList linkedList = mockLinkedList();
        linkedList.print();
        System.out.println("\n------");

        System.out.println(linkedList.get(0));
        linkedList.remove(0);
        System.out.println(linkedList.contains(2));
        linkedList.print();
        linkedList.removeByVal(4);
        System.out.println();
        linkedList.print();
    }


    //制造一颗单链表  2 - 1 - 3 - 4 - 10
    public static LinkedList mockLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(0, 10);
        linkedList.add(0, 1);
        linkedList.add(0, 2);
        linkedList.add(2, 3);
        linkedList.add(3, 4);
        return linkedList;
    }

    //获取指定位置的元素值
    public int get(int location) {
        if (location < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        ListNode curr = head;
        for (int i=0; i<location; ++i) {
            curr = curr.next;
            if (curr == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return curr.val;
    }

    //指定位置插入元素结点
    public void add(int location, int val) {
        if (location < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        ListNode newNode = new ListNode(val);

        if (location == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            ListNode curr = head;
            for (int i=0; i <location-1; ++i) {
                curr = curr.next;
            }
            newNode.next = curr.next;
            curr.next = newNode;
        }
    }

    //删除指定位置的结点
    public void remove(int location) {
        if (head == null) {
            throw new NoSuchElementException();
        }

        if (location == 0) {
            head = head.next;
        } else {
            ListNode curr = head;
            for(int i=0; i<location-1; i++) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
        }
    }

    //删除指定元素结点
    public void removeByVal(int val) {
        ListNode curr = head;
        int n=0;
        while (curr != null) {
            if (curr.val == val) {
                remove(n);
                return;
            }
            n++;
            curr = curr.next;
        }
        return;
    }

    //单链表中是否包含target元素
    public boolean contains(int target) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == target) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    //打印单链表
    public void print() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
    }

}
