package Questions.LinkList;
import static Lib.Base.*;
import Lib.Util;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description: 反转链表
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/

 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点

 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL

 0 <= 节点个数 <= 5000
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode root = Util.makeLinkedList();
        Util.printLinkedList(root);

        ListNode root1 = reverseList(root);

        Util.printLinkedList(root1);
    }


    //方法1：头插法
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;

        while(head != null) {
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }


    //方法2： 使用栈
    public static ListNode reverseList2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        //入栈
        while(head != null) {
            stack.push(head);
            head = head.next;
        }

        if(stack.size()==0) {
            return null;
        }

        //出栈
        head = stack.pop();
        ListNode node = head;
        while(!stack.isEmpty()) {
            node.next = stack.pop();
            node = node.next;
        }
        node.next = null;
        return head;
    }

    //方法3：递归
    public static ListNode reverseList3(ListNode head) {
        //终止条件
        if(head==null || head.next==null) {
            return head;
        }

        //head为当前节点
        //从当前节点的下一个结点开始递归调用
        ListNode reverse = reverseList3(head.next);
        //reverse是反转之后的链表，因为函数reverseList3表示的是对链表的反转，所以反转完之后head.next肯定
        //是链表reverse的尾结点，然后我们再把当前节点head挂到head.next节点的后面就完成了链表的反转
        head.next.next = head;

        //这里head相当于变成了尾结点，尾结点都是为空的，否则会构成环
        head.next = null;

        return reverse;
    }

    //更易理解的递归版本
    public ListNode reverseList33(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode next = head.next;
        ListNode reverse = reverseList(next);
        next.next = head;

        head.next = null;
        return reverse;
    }

    //尾递归 对递归的优化
    //以上递归往下传递的时候基本上没有逻辑处理，当往回反弹的时候才开始处理，也就是从链表的尾端往前开始处理的。可以再改一下，在链表递归的
    //时候从前往后处理，处理完之后直接返回递归的结果，这就是所谓的尾递归，这种运行效率要比上一种更多
    //尾递归虽然也会不停的压栈，但由于最后返回的是递归函数的值，所以在返回的时候都会一次性出栈，不会一个个出栈这么慢
    public static ListNode reverseListLast(ListNode head) {
        return reverseListLast(head, null);
    }

    public static ListNode reverseListLast(ListNode head, ListNode newhead) {
        if(head==null) {
            return newhead;
        }
        ListNode next = head.next;
        head.next = newhead;
        return reverseListLast(next, head);

        //这样写就不是尾递归
        //ListNode node = reverseListInt(next, head);
        //return node;
    }


    //其他头插法写法
    public static ListNode reverseList4(ListNode root) {
        ListNode pre = null; //已完成反转的链表头
        ListNode curr = root;  //当前结点
        ListNode next;  //当前结点的next

        while(curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    //头插法
    public static ListNode reverseList5(ListNode root) {
        if(root==null || root.next==null) {
            return root;
        }

        ListNode before;
        ListNode current = root;
        ListNode after = current.next;
        current.next = null;
        while(after!=null) {
            before = current;
            current = after;
            after = after.next;
            current.next = before;
        }
        return current;
    }

    //头插法
    public static ListNode reverseList6(ListNode head) {
        if(head==null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = null;

        ListNode p = head;
        ListNode q = p.next;

        while(p!=null) {
            dummy.next = p;
            p.next = prev;
            prev = p;

            p = q;
            if(q!=null) {
                q = q.next;
            }
        }
        return dummy.next;
    }

}
