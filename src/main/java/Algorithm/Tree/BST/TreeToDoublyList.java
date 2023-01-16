package Algorithm.Tree.BST;
import static Lib.Base.*;
import Lib.Util;
import java.util.Stack;
/**
 * Created with IntelliJ IDEA.
 * Description: 二叉搜索树与双向链表
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/

 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

 当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针
 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

 思路：
 使用 中序遍历“从小到大”访问树的节点， 将访问到的节点构建成双向链表
 */
public class TreeToDoublyList {
    public static TreeNode pre = null; //当前结点的前一个结点  当遍历完后即是双向链表尾结点
    public static TreeNode head = null; //双向链表头结点

    public static void main(String[] args) {
        TreeNode A = Util.makeTree3();
        TreeNode B = treeToDoublyList(A);
//        Util.printDTreeNode(B);
    }

    //方法1：递归 中序遍历  DFS
    public static TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) {
            return null;
        }

        dfs(root);

        //首尾相连构成环
        head.left = pre;
        pre.right = head;

        return head;
    }

    public static void  dfs(TreeNode cur) {
        if(cur==null) {
            return;
        }

        dfs(cur.left);

        if(pre == null) {
            head = cur;
        } else {
            pre.right = cur;
        }
        cur.left = pre;
        pre = cur;

        dfs(cur.right);
    }



    //方法2：非递归 中序遍历  栈
    //public static TreeNode treeToDoublyList2(TreeNode root) {
    //    if (root == null) {
    //        return null;
    //    }
    //
    //    Stack<TreeNode> stack = new Stack<>();
    //    TreeNode p = root;
    //}

}
