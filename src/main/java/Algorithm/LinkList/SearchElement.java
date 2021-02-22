package Questions.LinkList;
import Lib.Base.*;

/**
 * Created by Defias on 2017/10/7.
 *
 * 在链表中找值为value的节点，如果没有的话，返回空
 */

public class SearchElement {

    public static ListNode findNode(ListNode head, int val) {
        ListNode p = head;

        while(p!=null  && p.val!=val) {
            p = p.next;
        }
        return p;
    }
}
