package Algorithm.LinkList;
import Lib.Base.*;

/**
 * Created by Defias on 2017/10/10.

 两数相加

 https://leetcode-cn.com/problems/add-two-numbers/

 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字
 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和
 您可以假设除了数字 0 之外，这两个数都不会以 0 开头

 示例：
 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807

 (2 -> 4) + (5 -> 6 -> 4)
 7 -> 0 -> 5

 */

public class AddTwoNumbers {

    //逐位相加，相加一位两个链表同步向后移动一位
    //若存在链表长度不够移就保持为null，作0算
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;  //进位
        while(l1!=null || l2!=null || carry!=0) {
            ListNode cur = new ListNode(0);
            int sum = ((l1==null)?0:l1.val) + ((l2==null)?0:l2.val) + carry;
            cur.val = sum%10;
            carry = sum/10;
            prev.next = cur;
            prev = cur;

            l1 = (l1==null)?null:l1.next;
            l2 = (l2==null)?null:l2.next;
        }
        return head.next;
    }

    //分别遍历两个链表取各节点的值转换为十进制的数，然后相加
    //将相加后的结果表示成逆序链表
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if(l1==null || l2==null) {
            throw new ArithmeticException();
        }
        int num1 = 0;
        int num2 = 0;
        int base = 1;

        ListNode node = l1;
        while(node!=null) {
            num1 += base*node.val;
            base *= 10;
        }

        node = l2;
        base = 1;
        while(node!=null) {
            num2 += base*node.val;
            base *= 10;
        }

        int sum = num1+num2;

        ListNode root = null;
        ListNode next = null;
        while(sum>0) {
            int w = sum%10;
            if(root==null) {
                root = new ListNode(w);
                next = root;
            } else {
                next.next = new ListNode(w);
                next = next.next;
            }
            sum = sum / 10;
        }
        return root;
    }

}
