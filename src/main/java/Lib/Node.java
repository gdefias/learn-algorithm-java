package Lib;

/**
 * Created by Defias on 2017/10/6.
 *
 * 复杂链表结点
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
