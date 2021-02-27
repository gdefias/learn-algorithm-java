package Algorithm.LinkList;
import Lib.Base.*;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Defias on 2017/10/14.
 *
 * 环形链表 II

 https://leetcode-cn.com/problems/linked-list-cycle-ii/

 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 说明：不允许修改给定的链表。

 进阶：
 你是否可以不用额外空间解决此题？
 */

public class ListCycleII {

    //快慢指针
    //当发现slow与fast相遇时,再额外使用一个指针ptr,起始它指向链表头部；随后，它和slow每次向后移动一个位置。最终，它们会在入环点相遇
    public ListNode detectCycle(ListNode head) {
        if(head==null)
            return null;

        ListNode walker = head;
        ListNode faster = head;
        while (walker.next!=null && faster.next.next!=null) {
            walker = walker.next;
            faster = faster.next.next;
            if(walker==faster) {  //相遇后从头结点与相遇点结点同步走，再次相遇时的相遇点即为环的入口结点
                faster = head;
                while(walker!=faster) {
                    faster = faster.next;
                    walker = walker.next;
                }
                return faster;
            }
        }
        return null;
    }

    //使用hash表
    public ListNode detectCycle2(ListNode head) {
        ListNode p = head;
        Set<ListNode> set = new HashSet<>();
        while(p!=null) {
            if(set.contains(p)) {
                return p;
            }

            set.add(p);
            p = p.next;
        }
        return null;
    }
}
