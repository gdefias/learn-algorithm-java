package Algorithm.LinkList;
import static Lib.Base.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Defias on 2017/10/15.

 从尾到头打印链表

 https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/

 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）

 输入：head = [1,3,2]
 输出：[2,3,1]

 0 <= 链表长度 <= 10000
 */


public class ReversePrint {
    public static void main(String[] args) {

    }

    //方法1：使用栈
    public static int[] reversePrint(ListNode root) {
        Stack<Integer> stack = new Stack<>();

        //入栈
        ListNode p = root;
        while(p!=null) {
            stack.push(p.val);
            p = p.next;
        }

        //出栈
        int[] res = new int[stack.size()];
        int index = 0;
        while(!stack.empty()) {
            res[index++] = stack.pop();
        }

        return res;
    }


    //无需返回数组的递归打印  单链表从后往前打印
    public void reversePrintNotReturn(ListNode head) {
        if (head == null)
            return;
        reversePrint(head.next);
        System.out.println(head.val);
    }

    //方法2：递归
    public static int[] reversePrint2(ListNode head) {
        int cout = length(head);
        int[] res = new int[cout];

        reversePrintHelper(head, res, cout - 1);
        return res;
    }

    //计算链表的长度
    public static int length(ListNode head) {
        int cout = 0;
        ListNode p = head;
        while (p != null) {
            cout++;
            p = p.next;
        }
        return cout;
    }

    public static void reversePrintHelper(ListNode head, int[] res, int index) {
        if (head == null)
            return;
        reversePrintHelper(head.next, res, index - 1);

        res[index] = head.val;
    }


    //方法3：先反转链表，再顺序打印
}
