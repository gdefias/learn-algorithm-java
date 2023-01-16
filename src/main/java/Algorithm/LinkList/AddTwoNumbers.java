package Algorithm.LinkList;
import Lib.Base.*;

/**
 * Created by Defias on 2017/10/10.

 两数相加

 https://leetcode-cn.com/problems/add-two-numbers/

 给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字
 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和
 您可以假设除了数字 0 之外，这两个数都不会以 0开头

 示例：
 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807

 (2 -> 4) + (5 -> 6 -> 4)
 7 -> 0 -> 5

 */

public class AddTwoNumbers {

    //逐位相加，相加一位两个链表同步向后移动一位
    //若存在链表长度不够移就保持为null，作0算
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;  //进位
        while(l1!=null || l2!=null || carry!=0) {
            ListNode cur = new ListNode(0);
            int sum = ((l1==null)?0:l1.val) + ((l2==null)?0:l2.val) + carry;
            cur.val = sum%10;
            carry = sum/10;
            prev.next = cur;
            prev = cur;

            l1 = (l1==null)?null:l1.next;
            l2 = (l2==null)?null:l2.next;
        }
        return head.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        int carry = 0;

        while(l1!=null || l2!=null || carry!=0) {
            int sum = 0;
            if(l1==null && l2!=null) {
                sum = l2.val;
                l2 = l2.next;
            }

            if(l2==null && l1!=null) {
                sum = l1.val;
                l1 = l1.next;
            }

            if(l1!=null && l2!=null) {
                sum = l1.val + l2.val;
                l1 = l1.next;
                l2 = l2.next;
            }

            sum += carry;
            carry = sum/10;
            int val = sum%10;
            p.next = new ListNode(val);
            p = p.next;
        }

        p.next = null;

        return dummy.next;
    }
}
