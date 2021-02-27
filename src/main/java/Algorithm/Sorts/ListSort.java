package Algorithm.Sorts;

/**
 * Created by Jeff on 2016/5/1.
 *
 * 链表排序
 */
import static  Lib.Base.*;

public class ListSort {

    public static void main(String[] args) {
        ListNode root = createList();
        printList(root);

        //quicksortList(root);    //链表快速排序
        //mergesortList(root);      //链表归并排序
        insertionSortList(root);  //链表插入排序

        printList(root);
    }

    //创建一个测试单链表
    public static ListNode createList() {
        ListNode root = new ListNode(1);
        ListNode p = root;

        for(int i=10; i>1; --i) {
            ListNode newnode = new ListNode(i);
            p.next = newnode;
            p = p.next;
        }

        ListNode newnode = new ListNode(4);
        p.next = newnode;
        p = p.next;

        return root;
    }

    //单链表快速排序
    public static ListNode quicksortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode provit = findMiddle(head);  //选择中间结点为基准结点

        ListNode leftDummy = new ListNode(0);
        ListNode leftTail = leftDummy;   //链表尾结点

        ListNode rightDummy = new ListNode(0);
        ListNode rightTail = rightDummy;

        ListNode middleDummy = new ListNode(0);
        ListNode middleTail = middleDummy;

        while (head != null) {   //从头开始遍历带排序链表head
            if (head.val < provit.val) {   //比基准结点值小的结点连成左链表
                leftTail.next = head;
                leftTail = head;
            } else if (head.val > provit.val) {  //比基准结点值大的结点连成右链表
                rightTail.next = head;
                rightTail = head;
            } else {   //与基准结点值相等的结点连成中间链表
                middleTail.next = head;
                middleTail = head;
            }
            head = head.next;
        }

        leftTail.next = null;
        middleTail.next = null;
        rightTail.next = null;

        ListNode left = quicksortList(leftDummy.next);
        ListNode right = quicksortList(rightDummy.next);
        ListNode middle = middleDummy.next;

        return concat(left, middle, right);
    }


    //合并左中右三个链表为一个链表
    private static ListNode concat(ListNode left, ListNode middle, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        tail.next = left;
        tail = getTail(tail);

        tail.next = middle;
        tail = getTail(tail);

        tail.next = right;
        return dummy.next;
    }

    //获取链表尾结点
    private static ListNode getTail(ListNode head) {
        if (head == null) {
            return null;
        }

        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    //获取链表中间的节点 O(nlgn)
    private static ListNode findMiddle(ListNode head) {
        if (head == null)
            return head;

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //单链表归并排序
    public static ListNode mergesortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //从链表中间分成左右两个链表
        ListNode middle = findMiddle(head);
        ListNode rightlist = middle.next;
        middle.next = null;
        ListNode leftlist = head;

        //递归排序左右链表
        leftlist = mergesortList(leftlist);
        rightlist = mergesortList(rightlist);

        return merge(leftlist, rightlist);
    }

    //归并左右两个有序链表为一个有序链表
    private static ListNode merge(ListNode leftlist, ListNode rightlist) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;  //已归并链表的尾结点

        while (leftlist != null && rightlist != null) {
            if (leftlist.val < rightlist.val) {
                tail.next = leftlist;
                leftlist = leftlist.next;
            } else {
                tail.next = rightlist;
                rightlist = rightlist.next;
            }
            tail = tail.next;
        }

        if (leftlist != null) {
            tail.next = leftlist;
        } else if(rightlist != null) {
            tail.next = rightlist;
        } else {
            tail.next = null;
        }

        return dummy.next;
    }


    //单链表插入排序
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        while (head != null && head.next != null) {
            if(head.val <= head.next.val) {
                head = head.next;
                continue;
            }

            ListNode pre = dummy;
            while (pre.next.val < head.next.val) {
                pre = pre.next;
            }

            ListNode curr = head.next;
            head.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
        }

        return dummy.next;
    }
}