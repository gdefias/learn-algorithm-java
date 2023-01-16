package Algorithm.LinkList;
import Lib.Base.*;
import Lib.Util;
/**
 * @author: Felix
 * @date: 2022/12/26
 * @description:

 删除排序链表中的重复元素 II

 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回已排序的链表

 */
public class DeleteDuplicates2 {

    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        head = dummy;

        while(true) {
            if(head.next==null || head.next.next==null) {
                break;
            }

            if(head.next.val!=head.next.next.val) {
                head = head.next;
            } else {
                ListNode p = head.next.next;
                do {
                    p = p.next;
                } while(p!=null && p.val == head.next.val);
                head.next = p;
            }
        }
        return dummy.next;
    }
}
