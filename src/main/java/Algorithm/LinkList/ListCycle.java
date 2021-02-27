package Algorithm.LinkList;
import Lib.Base.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Defias on 2017/10/14.

 环形链表

 https://leetcode-cn.com/problems/linked-list-cycle/

 给定一个链表，判断链表中是否有环。
 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

 */

public class ListCycle {

    //快慢指针 相邻起点
    public boolean hasCycle(ListNode head) {
        if(head==null || head.next==null)
            return false;
        ListNode walker = head;
        ListNode faster = head.next;
        while (walker != faster) {
            if(faster.next==null || faster.next.next==null) {
                return false;
            }
            walker = walker.next;
            faster = faster.next.next;
        }
        return true;
    }


    //快慢指针 相同起点
    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode walker = head;
        ListNode faster = head;
        while (true) {
            if(faster.next==null || faster.next.next==null) {
                return false;
            }
            walker = walker.next;
            faster = faster.next.next;
            if(walker==faster) {
                return true;
            }
        }
    }

    //使用hash表
    public boolean hasCycle3(ListNode head) {
        ListNode p = head;
        Set<ListNode> set = new HashSet<>();
        while(p!=null) {
            if(set.contains(p)) {
                return true;
            }

            set.add(p);
            p = p.next;
        }

        return false;
    }
}
