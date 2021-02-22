package Questions.LinkList;
import Lib.Base.*;
/**
 * Created by Defias on 2017/10/7.

 两两交换链表中的节点

 https://leetcode-cn.com/problems/swap-nodes-in-pairs/

 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表
 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换

 输入：head = [1,2,3,4]
 输出：[2,1,4,3]

 输入：head = []
 输出：[]

 输入：head = [1]
 输出：[1]


 链表中节点的数目在范围 [0, 100] 内
 0 <= Node.val <= 100
 */

public class SwapPairs {

    //方法1
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        head = dummy;
        while (head.next != null && head.next.next != null) {
            ListNode n1 = head.next, n2 = head.next.next;
            head.next = n2;
            n1.next = n2.next;
            n2.next = n1;

            head = head.next.next;
        }
        return dummy.next;
    }

    //方法2：递归
    public ListNode swapPairs2(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }

        ListNode hnext = head.next;

        head.next = swapPairs2(hnext.next);
        hnext.next = head;

        return hnext;
    }
}
