package Questions.LinkList;
import java.util.HashSet;
import java.util.Set;

import static Lib.Base.*;

/**
 * Created with IntelliJ IDEA.
 * Description:  两个链表的第一个公共节点
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/

 输入两个链表，找出它们的第一个公共结点

 如果两个链表没有交点，返回 null.
 在返回结果后，两个链表仍须保持原有的结构
 可假定整个链表结构中没有循环
 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存

 思路： 双指针同步走
 使用两个指针 node1，node2 分别指向两个链表 headA，headB 的头结点，然后同时分别逐结点遍历，当 node1 到达链表 headA 的末尾时，重新定
 位到链表 headB 的头结点；当 node2 到达链表 headB 的末尾时，重新定位到链表 headA 的头结点。这样，当它们相遇时，所指向的结点就是第一
 个公共结点
 */
public class GetIntersectionNode {

    public static void main(String[] args) {

    }

    //方法1：双指针同步走
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;

        int count=0;  //关键点：记录转换的次数

        while(p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;

            if(p1==null) {
                p1 = headB;
                count++;
            }

            if(p2==null) {
                p2 = headA;
                count++;
            }

            if(count>2) {  //转换2次以上说明没有交点退出，否则陷入死循环
                return null;
            }
        }

        return p1;
    }

    //方法1：双指针同步走-简洁写法
    //如果没有交集，那么a + b = b + a, 最后两个指针同时指向null，这样俩指针一样是相等跳出while
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {

        ListNode h1 = headA, h2 = headB;

        while (h1 != h2) {
            h1 = h1 == null ? headB : h1.next;
            h2 = h2 == null ? headA : h2.next;
        }

        return h1;
    }


    //方法2：使用集合
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //创建集合set
        Set<ListNode> set = new HashSet<>();
        //先把链表A的结点全部存放到集合set中
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        //然后访问链表B的结点，判断集合中是否包含链表B的结点，如果包含就直接返回
        while (headB != null) {
            if (set.contains(headB))
                return headB;
            headB = headB.next;
        }
        //如果集合set不包含链表B的任何一个结点，说明他们没有交点，直接返回null
        return null;
    }


    //方法3：暴力解法
    public static ListNode getIntersectionNode3(ListNode A, ListNode B) {
        if(A==null|| B==null)
            return null;


        int Alen = getLinkedLength(A);
        int Blen = getLinkedLength(B);

        ListNode ah = A;
        ListNode bh = B;
        if(Alen > Blen) {
            for(int i=1; i<=Alen-Blen; i++) {
                ah = ah.next;
            }
        } else {
            for(int i=1; i<=Blen-Alen; i++) {
                bh = bh.next;
            }
        }

        while(ah != bh) {
            ah = ah.next;
            bh = bh.next;
        }
        return ah;
    }

    public static int getLinkedLength(ListNode head) {
        int length = 0;
        ListNode p = head;
        while (p!=null) {
            length++;
            p = p.next;
        }
        return  length;
    }
}
