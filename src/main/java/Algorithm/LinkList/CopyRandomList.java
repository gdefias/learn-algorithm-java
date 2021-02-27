package Algorithm.LinkList;
import Lib.Node;
import Lib.Util;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description: 复杂链表的复制
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/

 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的
 任意节点或者 null。

 -10000 <= Node.val <= 10000
 Node.random 为空（null）或指向链表中的节点
 节点数目不超过 1000
 */
public class CopyRandomList {

    public static void main(String[] args) {
        Node root = Util.makeComplexLinkedList();
        Util.printComplexLinkedList(root);

        System.out.println();

        Node newroot = copyRandomList(root);
        Util.printComplexLinkedList(newroot);
    }


    //方法1：新老结点依次相邻
    public static Node copyRandomList(Node head) {
        if(head==null) {
            return head;
        }

        //根据结点值新建新结点，并将新结点插入到各个源结点的后面
        Node p = head;
        while(p!=null) {
            Node newnode = new Node(p.val);
            newnode.next = p.next;
            p.next = newnode;
            p = newnode.next;
        }

        //处理随机指针
        p = head;
        while(p!=null) {
            if(p.random!=null) {
                p.next.random = p.random.next;  //p.random为随机指针指向的老结点，p.random.next为对应的新结点
            }
            p = p.next.next;
        }

        //剔除所有源结点,获得新的链表
        p = head;
        Node newhead = p.next;
        Node q = newhead;

        while(q!=null) {
            p.next = p.next.next;
            p = p.next;

            if(q.next==null) {
                break;
            }
            q.next = q.next.next;
            q = q.next;
        }

        return newhead;
    }

    //方法2：使用一个哈希表
    public Node copyRandomList2(Node head) {
        if(head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while(cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while(cur != null) {
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while(cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }

    //方法3: 使用两个哈希表
    public static Node copyRandomList3(Node root) {
        Node newroot = null;
        Node newlast = newroot;
        HashMap<Integer, Integer> hmvw = new HashMap<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        Node node = root;
        int j=0;
        while(node!=null) {
            hmvw.put(node.val, ++j);
            node = node.next;
        }

        j=0;
        node = root;
        while(node!=null) {
            Node newnode = new Node(node.val);
            if(newlast==null) {
                newroot = newnode;
                newlast = newnode;
            } else {
                newlast.next = newnode;
                newlast = newnode;
            }
            if(node.random!=null)
                hm.put(++j, hmvw.get(node.random.val));
            node = node.next;
        }

        node = newroot;
        Node tmp;
        j=0;
        while(node!=null) {
            ++j;
            if(hm.get(j) == null) {
                node.random = null;
            } else {
                tmp = newroot;
                for (int k=1; k<hm.get(j); k++) {
                    tmp = tmp.next;
                }
                node.random = tmp;
            }
            node = node.next;
        }
        return newroot;
    }
}
