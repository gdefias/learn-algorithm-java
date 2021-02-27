package Algorithm.LinkList;
import Lib.Base.*;

/**
 * Created by Defias on 2017/10/7.

 链表的中间结点

 https://leetcode-cn.com/problems/middle-of-the-linked-list/

 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 如果有两个中间结点，则返回第二个中间结点。


 输入：[1,2,3,4,5]
 输出：此列表中的结点 3 (序列化形式：[3,4,5])

 输入：[1,2,3,4,5,6]
 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 */
public class SearchMiddle {

    //快慢指针
    public static ListNode middleNode(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
