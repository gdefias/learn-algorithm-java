package Algorithm.LinkList;
import Lib.Base.*;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * Created by Defias on 2017/10/12.

 合并K个升序链表  K路归并

 https://leetcode-cn.com/problems/merge-k-sorted-lists/

 给你一个链表数组，每个链表都已经按升序排列
 请你将所有链表合并到一个升序链表中，返回合并后的链表。

 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 输出：[1,1,2,3,4,4,5,6]
 解释：链表数组如下：
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 将它们合并到一个有序链表中得到。
 1->1->2->3->4->4->5->6

 输入：lists = []
 输出：[]

 输入：lists = [[]]
 输出：[]
 
 k == lists.length
 0 <= k <= 10^4
 0 <= lists[i].length <= 500
 -10^4 <= lists[i][j] <= 10^4
 lists[i] 按 升序 排列
 lists[i].length 的总和不超过 10^4
 */
public class MergeKLists {

    //方法1：逐一比较   每次比较K个指针求min  时间复杂度：O(NK)
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length==0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        while(true) {
            ListNode minnode = null;
            int minindex = -1;

            for(int i=0; i<lists.length; i++) {
                if(lists[i]==null) {
                    continue;
                }

                if(minnode==null || lists[i].val<minnode.val) {
                    minindex = i;
                    minnode = lists[i];
                }
            }

            if(minnode==null) {
                break;
            }
            p.next = minnode;
            p = p.next;
            lists[minindex] = lists[minindex].next;
        }

        return dummy.next;
    }

    //方法2：使用小顶堆对方法1进行优化，每次O(logK)比较K个指针求min, 时间复杂度：O(NlogK)
    public static ListNode mergeKLists2(ListNode[] lists) {
        if(lists==null || lists.length==0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if(o1.val==o2.val)
                    return 0;
                else if(o1.val<o2.val)
                    return -1;
                else
                    return 1;
            }
        });

        for(ListNode node: lists) {
            if(node!=null) {
                queue.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while(!queue.isEmpty()) {
            ListNode minnode = queue.poll();
            p.next = minnode;
            p = p.next;

            if(minnode.next != null) {
                queue.offer(minnode.next);
            }
        }
        return dummy.next;
    }

    //方法3：顺序合并 逐一进行二路归并  时间复杂度：O(NK)
    public static ListNode mergeKLists3(ListNode[] lists) {
        if(lists==null || lists.length==0) {
            return null;
        }

        ListNode res = lists[0];
        for(int i=1; i<lists.length; i++) {
            res = MergeTwoLists.mergeTwoLists(res, lists[i]);
        }

        return res;
    }


    //方法4：归并排序思想（分治思想） 递归版本  时间复杂度：O(NlogK)
    public static ListNode mergeKLists4(ListNode[] lists) {
        if(lists==null || lists.length==0) {
            return null;
        }

        return merge(lists, 0, lists.length-1);
    }

    public static ListNode merge(ListNode[] lists, int start, int end) {
        if(start == end) {
            return lists[start];
        }

        int mid = start+(end-start)/2;  //中间砍一刀
        ListNode l1 = merge(lists, start, mid);  //前一半合并
        ListNode l2 = merge(lists, mid+1, end);  //后一半合并

        //对已合并的前一半和后一半进行合并（也就是二路归并）得到最终的结果
        return MergeTwoLists.mergeTwoLists2(l1, l2);
    }
}
