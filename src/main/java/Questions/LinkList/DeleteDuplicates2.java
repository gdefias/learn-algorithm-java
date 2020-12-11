package Questions.LinkList;
import Lib.Base.*;
import java.util.HashSet;

/**
 * @author: Defias
 * @date: 2020/12/3
 * @description: 删除链表中的重复元素（链表无序）
 */

public class DeleteDuplicates2 {

    public static ListNode removeDuplicates(ListNode head) {
        HashSet<Integer> set = new HashSet<Integer>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null) {
            if (set.contains(head.next.val)) {
                head.next = head.next.next;
            } else {
                set.add(head.next.val);
                head = head.next;
            }
        }
        return dummy.next;
    }
}
