package Lib;

/**
 * Created by Defias on 2017/10/6.
 *
 * 双向链表结点
 */
public class DListNode {
    public int val;
    public DListNode next;
    public DListNode prev;
    public DListNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}
