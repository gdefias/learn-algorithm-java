package Questions.LinkList;
import Lib.Base.*;

/**
 * Created by Defias on 2017/10/7.

 交换链表中两个结点

 Given 1->2->3->4->null and v1 = 2, v2 = 4.
 Return 1->4->3->2->null.
 */

public class SwapNode {

    public static ListNode swapNodes(ListNode head, int v1, int v2) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode pv1=null, pv2=null, tmp1, tmp2;

        while((pv1==null || pv2==null) && (head.next != null)){
            if(head.next.val==v1 && pv1==null) {
                pv1 = head;
            }
            if(head.next.val==v2 && pv2==null) {
                pv2 = head;
            }
            head = head.next;
        }

        if(pv1==null || pv2==null || pv1==pv2) {
            return dummy.next;
        }

        if(pv2.next==pv1) {
            tmp1 = pv2;
            pv2 = pv1;
            pv1 = tmp1;
        }

        if(pv1.next==pv2) {
            pv1.next = pv2.next;
            tmp1 = pv2.next.next;
            pv2.next.next = pv2;
            pv2.next = tmp1;
        } else {
            tmp1 = pv1.next;
            pv1.next = pv2.next;
            tmp2 = pv2.next.next;
            pv2.next.next = tmp1.next;
            pv2.next = tmp1;
            tmp1.next = tmp2;
        }
        return dummy.next;
    }
}
