package Questions.LinkList;
import static Lib.Base.*;
import Lib.Util;

/**
 * Created with IntelliJ IDEA.
 * Description: 合并两个排序链表
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/

 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

 输入：1->2->4, 1->3->4
 输出：1->1->2->3->4->4

 0 <= 链表长度 <= 1000
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        ListNode root1 = Util.makeLinkedList();
        ListNode root2 = Util.makeLinkedList2();

        ListNode root = mergeTwoLists2(root1, root2);
        Util.printLinkedList(root);
    }

    //方法1：归并排序的思路
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) {
            return l2;
        }

        if(l2==null) {
            return l1;
        }

        ListNode dummy = new ListNode(0);
        ListNode result = dummy;
        ListNode i = l1;
        ListNode j = l2;
        while(i!=null || j!=null) {
            if(j==null || (i!=null && i.val<=j.val)) {
                dummy.next = i;
                i = i.next;
            } else {
                dummy.next = j;
                j = j.next;
            }
            dummy = dummy.next;
        }
        return result.next;
    }

    //方法2：递归
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1==null) {
            return l2;
        }

        if(l2==null) {
            return l1;
        }

        if(l1.val<=l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
