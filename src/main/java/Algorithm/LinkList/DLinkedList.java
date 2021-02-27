package Algorithm.LinkList;
/**
 * Created by Defias on 2020/06.
 * Description: 双向链表
 *
 */

public class DLinkedList {

    //双向链表结点
    public static class DListNode {
        public int key;
        public int val;
        public int cnt;   //使用频率，LFU Cache算法会使用到
        public DListNode next;
        public DListNode prev;

        public DListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.cnt = 0;
            this.next = null;
            this.prev = null;
        }
    }

    public DListNode head, tail;  //链表头尾
    public int size;  //链表结点数

    public DLinkedList() {
        head = new DListNode(0,0);   //哨兵结点
        tail = new DListNode(0, 0);
        head.next = tail;  //头尾相连
        tail.prev = head;
        size = 0;
    }

    //添加结点到链表头
    public void addHeadNode(DListNode node) {
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        size++;
    }


    //删除链表中的指定结点
    public void deleteNode(DListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }


    //移动指定结点移到链表头
    public void moveNodeToHand(DListNode node) {
        deleteNode(node);
        addHeadNode(node);
    }


    //删除链表尾结点并返回该节点
    public DListNode deleteTailNode() {
        if(tail.prev == head) {
            return null;
        }
        DListNode node = tail.prev;
        deleteNode(node);
        return node;
    }

}
