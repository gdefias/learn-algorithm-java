package Algorithm.LinkList;
import Lib.Base.*;
import Lib.Util;

/**
 * Created by Jeff on 2016/5/1.

 删除链表中倒数第n个节点

 https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/

 给定一个链表，删除链表中倒数第n个节点，返回链表的头节点

 给定一个链表: 1->2->3->4->5, 和 n = 2.
 当删除了倒数第二个节点后，链表变为 1->2->3->5.

 说明：给定的 n保证是有效的
 进阶：你能尝试使用一趟扫描实现吗？

 */
public class DeleteNthFromEnd {
    public static void main(String[] args) {
        ListNode mylist = Util.createList();
        Util.print(mylist);

        removeNthFromEnd(mylist, 2);
        Util.print(mylist);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        head = dummy;
        ListNode p = dummy;

        for(int i=0; i<n; i++) {
            p = p.next;
        }

        while(p.next!=null) {
            p = p.next;
            head = head.next;
        }

        head.next = head.next.next;
        return dummy.next;
    }


    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head==null || n <= 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preDelete = dummy;  //待删结点的前一个结点

        for (int i = 0; i < n; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }
        while (head != null) {  //双指针
            head = head.next;
            preDelete = preDelete.next;
        }
        preDelete.next = preDelete.next.next;
        return dummy.next;
    }




}

