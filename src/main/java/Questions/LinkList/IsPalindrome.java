package Questions.LinkList;
import Lib.Base.*;

import java.util.Stack;

/**
 * Created by Defias on 2017/10/7.

 回文链表

 https://leetcode-cn.com/problems/palindrome-linked-list/

 编写一个函数，检查输入的链表是否是回文的

 示例 1：
 输入： 1->2
 输出： false

 示例 2：
 输入： 1->2->2->1
 输出： true

 进阶：
 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */

public class IsPalindrome {
    ListNode temp;

    public static void main(String[] args) {

    }

    //方法1：反转后半部分链表
    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        //通过快慢指针找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast不为空，说明链表的长度是奇数个
        if (fast != null) {
            slow = slow.next;
        }
        //反转后半部分链表
        slow = reverse(slow);

        fast = head;
        while (slow != null) {
            //然后比较，判断节点值是否相等
            if (fast.val != slow.val)
                return false;
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    //反转链表
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }


    //方法2：使用栈 把链表的节点全部存放到栈中，然后再一个个出栈，这样就相当于链表从后往前访问了
    public static boolean isPalindrome2(ListNode head) {
        ListNode temp = head;
        Stack<Integer> stack = new Stack();
        //把链表节点的值存放到栈中
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }

        //然后再出栈
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    //方法3：递归
    public boolean isPalindrome3(ListNode head) {
        temp = head;
        return check(head);
    }

    private boolean check(ListNode head) {
        if (head == null)
            return true;
        boolean res = check(head.next) && (temp.val == head.val);
        temp = temp.next;
        return res;
    }
}
