package Algorithm.LinkList;
import static Lib.Base.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 删除链表的节点
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/

 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 返回删除后的链表的头节点。

 输入: head = [4,5,1,9], val = 5
 输出: [4,1,9]
 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9

 题目保证链表中节点的值互不相同
 */

public class DeleteNode {
    public static void main(String[] args) {

    }

    //方法1
    public ListNode deleteNode(ListNode head, int val) {
        if(head==null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    //方法2
    public ListNode deleteNode2(ListNode head, int val) {
        if(head==null) {
            return null;
        }

        ListNode p = head;
        ListNode prev = head;

        if(p.val==val) {
            return head.next;
        }
        p = p.next;

        while(p!=null && p.val!=val) {
            p = p.next;
            prev = prev.next;
        }

        if(p!=null) {
            prev.next = p.next;
        }

        return head;
    }
}
