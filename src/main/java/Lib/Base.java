package Lib;
import static Lib.Base.*;
import java.util.Random;

/**
 *
 * @author Defias
 * @date 2020/06
 * Description:  基本方法和类
 */

public class Base {

    //交换数组元素
    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    //获取数组的最大最小值  O(n)
    public static int[] getMaxMin(int[] A) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int a: A) {
            max = Math.max(max, a);
            min = Math.min(min, a);
        }

        int[] res = new int[2];
        res[0] = max;
        res[1] = min;

        return res;
    }

    //打印数组
    public static void printArray(int[] A) {
        System.out.println("-----------");
        for(int i=0; i<A.length; ++i) {
            System.out.print(A[i] + " ");
        }
        System.out.println("\n-----------");
    }


    //单链表结点（模拟）
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    //树堆结点类
    public static class TreapNode{
        public int key;   //元素的关键字值
        public int priority;   //结点的优先级，用于满足堆的性质
        public TreapNode left, right;  //左右孩子结点

        public TreapNode(int key, int... priority){
            this.key = key;
            this.left = null;
            this.right = null;

            if(priority.length != 0) {
                this.priority = priority[0];
            } else {
                this.priority = random();
            }
        }
        
        //随机数生成器，用于随机生成结点元素的优先级
        public int random(){
            Random random = new Random();
            return random.nextInt(1000);
        }
    }

    //打印单链表List
    public static void printList(ListNode head) {
        ListNode cur = head;
        System.out.println("-----------");
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println("\n-----------");
    }

    //二叉树结点（模拟）
    public static class TreeNode {
        public int val;
        public TreeNode left;   //左子树
        public TreeNode right;  //右子树
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    //红黑树结点（模拟）
    public static class RBTreeNode {
        public int val;
        public boolean color;     //RED或BLACK
        public RBTreeNode left;   //左子树
        public RBTreeNode right;  //右子树
        public RBTreeNode(int val) {
            this.val = val;
        }
    }

    //键值对类型
    public static class Pair {
        public String key;
        public int val;
        public Pair(String key, int val) {  //构造函数需要键和值
            this.key = key;
            this.val = val;
        }
    }

    //键值对二叉树结点
    public static class PairTreeNode {
        public Pair pair;
        public PairTreeNode left;
        public PairTreeNode right;
        public PairTreeNode(Pair pair) {   //构造函数需要一个键值对对象
            this.pair = pair;
        }
    }


}
