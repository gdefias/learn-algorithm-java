package Algorithm.LinkList;
import java.util.NoSuchElementException;
import  static Lib.Base.*;
/**
 *
 * @author Defias
 * @date 2017/9/15
 *
 * 实现整数列表
 */


public class IntegerList implements IIntegerList {
    private ListNode head;

    public static void main(String[] args) {

        IntegerList integerList = mockIntegerList();
        integerList.print();
        System.out.println("\n------");

        System.out.println(integerList.size());

        System.out.println(integerList.get(2));

        integerList.set(0, 100);
        integerList.print();
        System.out.println("\n------");

        System.out.println(integerList.remove(3));
        integerList.print();
        System.out.println("\n------");

    }

    //制造一个整数列表
    public static IntegerList mockIntegerList() {
        IntegerList integerList = new IntegerList();
        integerList.add(0, 1);
        integerList.add(0, 2);
        integerList.add(0, 3);
        integerList.add(0, 4);
        integerList.add(0, 5);
        integerList.add(5, 0);

        return integerList;
    }


    //新增结点于列表指定位置之后，指定位置不存在时报错
    @Override
    public void add(int location, int val) {
        if (location < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (location == 0) {
            ListNode newHead = new ListNode(val);
            newHead.next = head;
            head = newHead;
        } else {
            ListNode curr = head;
            for (int i=0; i<location-1; ++i) {
                if(curr.next==null)
                    throw new NoSuchElementException();
                curr = curr.next;
            }

            ListNode newNode = new ListNode(val);
            newNode.next = curr.next;
            curr.next = newNode;
        }
    }

    //获取列表指定位置的结点值，指定位置不存在时报错
    @Override
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

    //更新列表指定位置的结点值，指定位置不存在时报错
    @Override
    public void set(int location, int newVal) {
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (location < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else if(location == 0) {
            head.val = newVal;
            return;
        }
        ListNode curr = head;
        for(int i=0; i<location; i++) {
            if(curr.next==null)
                throw new NoSuchElementException();
            curr = curr.next;
        }
        curr.val = newVal;
    }


    //删除列表指定位置的结点，指定位置不存在时报错，返回删除的结点值
    @Override
    public int remove(int location) {
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (location == 0) {
            int ret = head.val;
            head = head.next;
            return ret;
        } else {
            ListNode curr = head;
            //int i=0;
            //while(i<location-1) {
            //    curr = curr.next;
            //    ++i;
            //}
            for(int i=0; i<location-1; i++) {
                if(curr.next==null)
                    throw new NoSuchElementException();
                curr = curr.next;
            }
            int ret = curr.next.val;
            curr.next = curr.next.next;
            return ret;
        }
    }

    //列表大小
    @Override
    public int size() {
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }

    //打印列表值
    @Override
    public void print() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }
}
