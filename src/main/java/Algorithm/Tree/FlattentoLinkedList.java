package Algorithm.Tree;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import static Lib.Base.*;
import static Lib.Util.*;
/**
 * Created by Defias on 2017/10/12.

 二叉树展开为链表

 https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/

 给定一个二叉树，原地将它展开为一个单链表

 例如，给定二叉树
 1
 / \
 2   5
 / \   \
 3   4   6
 将其展开为：
 1
 \
 2
 \
 3
 \
 4
 \
 5
 \
 6
 */
public class FlattentoLinkedList {
    public static TreeNode prev = null;

    public static void main(String[] args) {
        TreeNode root = makeTree1();
        flatten(root);
        print2(root);
    }

    //方法1： 前序遍历  将二叉树展开为单链表之后，单链表中的节点顺序即为二叉树的前序遍历访问各节点的顺序   自顶向下
    public static void flatten(TreeNode root) {
        if(root==null) {
            return;
        }
        //左右子树先保存起来（因为下一步要切断左指针）
        TreeNode left = root.left;
        TreeNode right = root.right;

        if(prev!=null) {
            prev.left = null;  //切断左指针
            prev.right = root;  //右指针作为单链表的指针 指向当前结点
        }
        prev = root;  //往前进

        flatten(left);
        flatten(right);

    }


    //方法2： 前序遍历  非递归
    public static void flatten2(TreeNode root) {
        if (root == null){
            return;
        }
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode pre = null;
        while (!s.isEmpty()) {
            TreeNode temp = s.pop();
            if(pre!=null){
                pre.right = temp;
                pre.left = null;
            }
            if (temp.right != null){
                s.push(temp.right);
            }
            if (temp.left != null){
                s.push(temp.left);
            }
            pre = temp;
        }
    }


    //方法3： 后序遍历 自底向上
    public static void flatten3(TreeNode root) {
        if (root == null)
            return;
        flatten3(root.right);
        flatten3(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }




    /**
     *方法4
     1、将左子树插入到右子树的地方
     2、将原来的右子树接到左子树的最右边节点
     3、考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为null
     * */
    public static void flatten4(TreeNode root) {
        if(root==null) {
            return;
        }
        while(root!=null) {
            if(root.left!=null) {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while(pre.right!=null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
            }
            // 下一个节点
            root = root.right;
        }
    }
}
