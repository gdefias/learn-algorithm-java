package Algorithm.LinkList;
import Lib.Base.*;
/**
 * Created by Defias on 2017/10/7.

 分隔链表

 https://leetcode-cn.com/problems/partition-list/

 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前
 你应当保留两个分区中每个节点的初始相对位置


 输入: head = 1->4->3->2->5->2, x = 3
 输出: 1->2->2->4->3->5

 */

public class PartitionList {

    //分别构建两个链表再接上  时间复杂度: O(N)  空间复杂度: O(1)
    public static ListNode partition(ListNode head, int x) {
        if(head==null) {
            return null;
        }

        ListNode leftdummy = new ListNode(0);
        ListNode rightdummy = new ListNode(0);
        ListNode left = leftdummy;
        ListNode right = rightdummy;

        while(head!=null) {
            if(head.val<x) {
                left.next = head;
                left = left.next;
            } else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }

        right.next = null;
        left.next = rightdummy.next; //接上

        return leftdummy.next;
    }
}
