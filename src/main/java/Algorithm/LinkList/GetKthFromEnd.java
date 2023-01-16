package Algorithm.LinkList;
import static Lib.Base.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 链表中倒数第k个节点
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/

 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

 
 给定一个链表: 1->2->3->4->5, 和 k = 2
 返回链表 4->5
 */
public class GetKthFromEnd {

    public static void main(String[] args) {

    }

    //双指针（快慢指针）
    public static ListNode getKthFromEnd(ListNode root, int k) {
        int count=1;
        ListNode first = root;
        ListNode second = root;
        while(count<=k) {
            first = first.next;
            count++;
        }

        while(first!=null) {
            first = first.next;
            second = second.next;
        }

        return second;
    }
}
