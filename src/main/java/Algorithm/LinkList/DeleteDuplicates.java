package Algorithm.LinkList;
import Lib.Base.*;
import Lib.Util;

/**
 * Created by Defias on 2017/10/7.

 删除排序链表中的重复元素

 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/

 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次

 示例 1:
 输入: 1->1->2
 输出: 1->2

 示例 2:
 输入: 1->1->2->3->3
 输出: 1->2->3
 */
public class DeleteDuplicates {
    public static void main(String[] args) {
        ListNode mylist = Util.createList();
    }

    //方法1：直接法
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }

        ListNode p = head;
        while(p.next!=null) {
            if(p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }

    //方法2：快慢指针
    public static ListNode deleteDuplicates2(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null) {

            if(fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }



}
