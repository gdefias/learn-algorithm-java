package Lib;
import java.util.List;

import static Lib.Base.*;
/**
 * Created by Defias on 2017/9/26.
 *
 * 工具类
 */
public class Util {

    //前序遍历 （从根结点开始：根节点 - 左子树 - 右子树）
    public static void printTreePreOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        printTreePreOrder(root.left);
        printTreePreOrder(root.right);
    }

    //中序遍历 （从根结点开始： 左子树 - 根节点 - 右子树）
    public static void printTreeInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printTreeInOrder(root.left);
        System.out.println(root.val);
        printTreeInOrder(root.right);
    }

    //打印链表
    public static void printLinkedList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.print("NIL\n");
    }

    //打印复杂链表
    public static void printComplexLinkedList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
        System.out.println();

        curr = head;
        while (curr != null) {
            if(curr.random!=null)
                System.out.print(curr.val+"-->"+curr.random.val+" ");
            else
                System.out.print(curr.val+"-->null ");
            curr = curr.next;
        }
    }

    //打印二叉树结点构成的双向链表
    public static void printDTreeNode(TreeNode root) {
        if(root==null)
            return;
        System.out.print(root.val + " --> ");
        TreeNode current = root;
        while(current.right!=null) {
            System.out.print(current.right.val + " --> ");
            current = current.right;
        }
        System.out.print("null\n");

        System.out.print(current.val + " <-- ");
        while(current.left!=null) {
            System.out.print(current.left.val + " <-- ");
            current = current.left;
        }
        System.out.print("null\n");

    }

    //制造链表
    public static ListNode makeLinkedList() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return node1;
        //printLinkedList(node1);
    }

    public static ListNode makeLinkedList2() {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return node1;
    }

    //制造复杂链表
    public static Node makeComplexLinkedList() {
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(5);
        Node node4 = new Node(7);
        Node node5 = new Node(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node1.random = node3;
        node2.random = node5;
        node3.random = null;
        node4.random = node2;
        node5.random = null;

        return node1;
        //printComplexLinkedList(node1);
    }

    /*
    制造一棵树
                        8
                     /     \
                    3       10
                  /  \        \
                 1    6        4
                     / \       /
                    4   7    2

    * */
    public static TreeNode makeTree() {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(2);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.right = node6;

        node5.left = node7;
        node5.right = node8;

        node6.left = node9;
        return node1;
    }

    /*
    制造一棵树
                      6
                     / \
                    4   7

    * */
    public static TreeNode makeTree2() {
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        return node1;
    }

    /*
   制造一棵树
                       10
                    /     \
                   6       14
                 /  \     /  \
                4    8   12   16
   * */
    public static TreeNode makeTree3() {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(12);
        TreeNode node3 = new TreeNode(14);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(16);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;
        return node1;
    }

    public static int[][] make2DArray() {
        int[][] A = new int[5][3];
        A[0][0] = 1;
        A[0][1] = 2;
        A[0][2] = 3;
//        A[0][3] = 4;
//        A[0][4] = 5;

        A[1][0] = 6;
        A[1][1] = 7;
        A[1][2] = 8;
//        A[1][3] = 9;
//        A[1][4] = 10;

        A[2][0] = 11;
        A[2][1] = 12;
        A[2][2] = 13;
//        A[2][3] = 14;
//        A[2][4] = 15;

        A[3][0] = 16;
        A[3][1] = 17;
        A[3][2] = 18;
//        A[3][3] = 19;
//        //A[3][4] = 20;

        A[4][0] = 21;
        A[4][1] = 22;
        A[4][2] = 23;
//        A[4][3] = 24;
//        //A[4][4] = 25;

        return A;
    }


    public static void print2DArray(int[][] array2D) {
        if(array2D==null || array2D.length==0 || array2D[0].length==0) {
            return;
        }
        int rows = array2D.length;
        int cols = array2D[0].length;

        for(int x=0; x<rows; x++){
            for (int y=0; y<cols; y++) {
                System.out.print(array2D[x][y]+" ");
            }
            System.out.println();
        }
    }




    /*
  制造一棵树
                      45
                   /     \
                 34       32
                /  \     /  \
               41  34   93   77

  * */
    public static TreeNode makeTree1() {
        TreeNode node1 = new TreeNode(45);
        TreeNode node2 = new TreeNode(34);
        TreeNode node3 = new TreeNode(32);
        TreeNode node4 = new TreeNode(41);
        TreeNode node5 = new TreeNode(34);
        TreeNode node6 = new TreeNode(93);
        TreeNode node7 = new TreeNode(77);
        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;
        return node1;
    }

    /*
    制造一棵树
                      8
                   /     \
                  5       9
                /  \     /  \
               4    6   10   11

    * */
    public static TreeNode makeTree33() {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(10);
        TreeNode node7 = new TreeNode(11);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;
        return node1;
    }

    /*
    创建一棵BST

                     100
                   /     \
                  90      110
                /  \     /  \
               89    92 101  118
     */
    public static TreeNode makeTree4() {
        TreeNode node1 = new TreeNode(100);
        TreeNode node2 = new TreeNode(90);
        TreeNode node3 = new TreeNode(110);
        TreeNode node4 = new TreeNode(89);
        TreeNode node5 = new TreeNode(92);
        TreeNode node6 = new TreeNode(101);
        TreeNode node7 = new TreeNode(118);
        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;
        return node1;
    }

    public static TreeNode makeTree5() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);

        node1.left = node2;
        return node1;
    }



    //创建List
    public static ListNode createList() {
        ListNode root = new ListNode(1);
        ListNode p = root;

        for(int i=2; i<=10; ++i) {
            ListNode newnode = new ListNode(i);
            p.next = newnode;
            p = p.next;
        }
        return root;
    }

    //打印List
    public static void print(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "-->");
            cur = cur.next;
        }
        System.out.print("null\n");
    }


    //打印TreeNode作为List
    public static void print2(TreeNode head) {
        TreeNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "-->");
            cur = cur.right;
        }
        System.out.print("null\n");
    }

    //打印数组
    public static void printArray(int[] nums) {
        for(int i: nums) {
            System.out.println(i);
        }
    }

    //list装原始类型数组
    public static int[] listToIntArray(List<Integer> lists) {
        int[] result = new int[lists.size()];
        for(int i=0; i<lists.size(); i++) {
            result[i] = lists.get(i);
        }

        return result;
    }
}
