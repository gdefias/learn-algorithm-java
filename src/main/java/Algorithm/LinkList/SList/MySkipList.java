package Algorithm.LinkList.SList;
import java.util.Random;

/**
 * Created by Defias on 2020/06.
 * Description:  设计跳表  O(lgn)

 跳表实现
 */

class MySkipList {
    //跳表结点类
    static class Node {
        int val;
        Node right, down;  //分别指向当前层的下一个结点和下一层的结点

        public Node(Node r, Node d, int val) {
            right = r;
            down = d;
            this.val = val;
        }
    }

    //指向整个跳表最左上角的结点（顶层）
    Node head = new Node(null, null, -1);
    Random rand = new Random();
    int MAXLV = 1 << 6;  //64 最大层数  结点数：2^64 已经相当大了

    public static void main(String[] args) {
        MySkipList skiplist = new MySkipList();

        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.add(0);
        skiplist.add(5);
        skiplist.add(6);
        skiplist.add(7);
        skiplist.printSkipList();

        System.out.println(skiplist.search(4));   // 返回 false
        System.out.println(skiplist.search(1));   // 返回 true

        System.out.println(skiplist.erase(4));    // 返回 false，0 不在跳表中
        System.out.println(skiplist.erase(0));    // 返回 true

        skiplist.printSkipList();
    }


    //插入一个元素到跳表
    public void add(int num) {
        Node[] levelTails = new Node[MAXLV];   //保存各层的插入点
        int lv = -1;  //顶层lv=0
        for (Node p=head; p!=null; p=p.down) {       //顶层开始
            while (p.right != null && p.right.val < num) {   //向右找直到找到头了 或 结点值大于或等于待插入结点
                p = p.right;
            }
            levelTails[++lv] = p;  //保存该层的插入点
        }

        boolean insertUp = false;  //是否将当前结点放在上一层中
        Node downNode = null;  //作为下次插入结点的下结点

        do {
            Node insert = levelTails[lv--];  //取出插入点结点
            insert.right = new Node(insert.right, downNode, num);  //插入
            downNode = insert.right;
            insertUp = (rand.nextInt() & 1) == 0;  //掷硬币确定是否向上提拔
        } while (insertUp && lv >= 0);

        //当提拔到顶层了且确定继续提拔就往上追加一层
        if (insertUp) {
            head = new Node(new Node(null, downNode, num), head, -1);
        }
    }

    //查询目标元素是否存在于跳表中
    public boolean search(int target) {
        // 先往右再往下，缩小区间，套路都是这个套路
        for (Node p=head; p!=null; p=p.down) {
            while (p.right != null && p.right.val < target) {
                p = p.right;
            }
            if (p.right != null && p.right.val == target) {
                return true;
            }
        }
        return false;
    }

    //删除指定值得元素，如果num不存在，直接返回false，如果存在多个num ，删除其中任意一个即可
    public boolean erase(int num) {
        boolean exists = false;
        for (Node p = head; p != null; p = p.down) {
            while (p.right != null && p.right.val < num) {
                p = p.right;
            }
            if (p.right != null && p.right.val == num) {
                exists = true;
                p.right = p.right.right;
            }
        }
        return exists;
    }

    //打印跳表
    public void printSkipList() {
        Node p = head;
        while(p != null) {  //逐层打印
            Node pr = p;
            while (pr != null) {  //打印某一层中的一条链
                System.out.print(pr.val+" ");
                pr = pr.right;
            }
            System.out.println();
            p = p.down;  //下一层
        }
    }
}

