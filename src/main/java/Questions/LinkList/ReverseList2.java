package Questions.LinkList;

import Lib.Base.ListNode;
import Lib.Util;
import java.util.Stack;

/**
 * Created by Defias on 2017/10/7.

 反转链表 II

 https://leetcode-cn.com/problems/reverse-linked-list-ii/

 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转
 说明:
 1 ≤ m ≤ n ≤ 链表长度

 示例:
 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 输出: 1->4->3->2->5->NULL

 */

public class ReverseList2 {
    public static ListNode successor = null; // 后驱节点

    public static void main(String[] args) {
        ListNode head = Util.createList();
        Util.print(head);

        ListNode node = reverseBetween(head,1,2);
        Util.print(node);
    }


    //头插法
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null || m>=n) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        ListNode q = dummy;
        ListNode p = dummy.next;

        for (int i=1; i<m; i++) {
            if (p.next == null) {
                return null;
            }
            p = p.next;
            q = q.next;
        }
        //循环结束后：p指向待反转的第一个节点的前一个节点；q指向待反转的第一个节点

        //q保持不动
        for(int i=m; i<n; i++) {
            ListNode next = p.next;
            p.next = p.next.next;

            next.next = q.next;
            q.next = next;
        }

        return dummy.next;
    }


    //递归
    public static ListNode reverseBetween2(ListNode head, int m, int n) {
        if(m == 1) {
            return reverseN(head, n);
        }

        ListNode next = head.next;
        ListNode reverse = reverseBetween2(next, m-1, n-1);
        head.next = reverse;

        return head;
    }

    //反转以head为起点的n个节点，返回新的头结点
    public static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第n+1个节点
            successor = head.next;
            return head;
        }
        //以head.next为起点，需要反转前 n-1 个节点
        ListNode last = reverseN(head.next, n-1);

        head.next.next = head;

        //在链表反转的递归实现中是直接把head.next设置为null，因为整个链表反转后原来的 head 变成了整个链表的最后一个节点
        //但现在head节点在递归反转之后不一定是最后一个节点了，所以要记录后驱successor（第 n + 1 个节点），反转之后将 head 连接上
        head.next = successor;

        return last;
    }
}
