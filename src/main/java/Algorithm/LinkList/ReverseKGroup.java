package Algorithm.LinkList;
import Lib.Base.*;
import Lib.Util;

/**
 * Created by Defias on 2017/10/11.

 K个一组翻转链表

 https://leetcode-cn.com/problems/reverse-nodes-in-k-group/

 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 k 是一个正整数，它的值小于或等于链表的长度。
 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

 链表：1->2->3->4->5
 当 k = 2 时，应当返回: 2->1->4->3->5
 当 k = 3 时，应当返回: 3->2->1->4->5

 算法只能使用常数的额外空间
 不能只是单纯的改变节点内部的值，而是需要实际进行节点交换
 */
public class ReverseKGroup {
    public static void main(String[] args) {
        ReverseKGroup O = new ReverseKGroup();
        ListNode root = Util.makeLinkedList();
        Util.printLinkedList(root);

        ListNode root1 = O.reverseKGroup(root, 2);

        Util.printLinkedList(root1);
    }

    //方法1：分组翻转 - 文青
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode pre = dump;
        ListNode end = dump;

        while(end.next != null) {
            for(int i=0; i<k && end!=null; i++) {
                end = end.next;
            }

            if(end==null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;

            pre.next = reverseList(start);  //翻转以后start指向的结点成为了已翻转的尾结点
            start.next = next;
            pre = start;
            end = start;
        }

        return dump.next;
    }


    //方法2：递归 - 牛逼青年
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) {
            curr = curr.next;
            count++;
        }
        if (count == k) {
            curr = reverseKGroup2(curr, k);

            while (count-- > 0) {
                ListNode tmp = head.next;
                head.next = curr;
                curr = head;
                head = tmp;
            }
            head = curr;
        }
        return head;
    }

    public ListNode reverseList(ListNode root) {
        ListNode pre = null;
        ListNode curr = root;

        while(curr!=null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }


    //方法3：普通方法 - 普通青年
    public ListNode reverseKGroup3(ListNode head, int k) {
        if(head==null || k<2) {
            return head;
        }
        ListNode dump = new ListNode(0);
        ListNode q = dump;
        ListNode first = head;
        ListNode p = head;

        int j = 0;
        while(j<k) {
            if(first==null) {
                return head;
            }
            first = first.next;
            j++;
        }

        ListNode pn = p.next;
        while(true) {
            p.next = q.next;
            q.next = p;
            if(pn!=first) {
                p = pn;
                pn = pn.next;
                continue;
            }

            while(q.next!=null) {
                q = q.next;
            }

            j = 0;
            while(j<k) {
                if(first==null) {
                    break;
                }
                first = first.next;
                j++;
            }

            if(j==k) {
                p = pn;
                pn = pn.next;
                continue;
            } else {
                q.next = pn;
                break;
            }
        }
        return dump.next;
    }
}
