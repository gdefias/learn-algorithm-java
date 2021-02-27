package Algorithm.LinkList;
import Lib.Base.*;

/**
 * Created by Defias on 2017/10/7.

 给定单链表的头指针和一个结点指针 在O(1)时间删除链表结点

 */

public class DeleteNode2 {

    public static ListNode deleteNodeFromNode(ListNode root, ListNode p) {
        if(root==null || p==null) {
            return null;
        }

        if(p==root) {
            root = root.next;
            return root;
        }

        if(p.next==null) {
            ListNode q = root;
            while(q.next!=p) {
                q = q.next;
            }
            q.next=null;
            return root;
        }

        p.val = p.next.val;
        p.next = p.next.next;
        return root;
    }
}
