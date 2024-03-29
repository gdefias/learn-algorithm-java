package Algorithm.Tree.Base;
import Lib.Base.*;
import Lib.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author: Defias
 * @date: 2020/12/9
 * @description: 二叉树的后序遍历

 给定一个二叉树，返回它的 后序遍历。

 https://leetcode-cn.com/problems/binary-tree-postorder-traversal/

 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class TraversalPostOrder {

    public static void main(String[] args) {
        TreeNode root =  Util.makeTree();
        System.out.println(postorderTraversal2(root));
    }

    //方法1：递归
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        recur(root, result);
        return result;
    }

    public static void recur(TreeNode root, List<Integer> result) {
        if(root==null) {
            return;
        }
        recur(root.left, result);
        recur(root.right, result);
        result.add(root.val);
    }


    //方法2：迭代  非递归
    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode lastVisit = null;
        TreeNode p = root;

        while(p!=null || !stack.isEmpty()) {
            while(p!=null) {
                stack.push(p);
                p = p.left;
            }

            p = stack.pop();
            if(p.right==null || p.right==lastVisit) {
                result.add(p.val);
                lastVisit = p;
                p = null;
            } else {
                stack.push(p);
                p = p.right;
            }
        }
        return result;
    }
}
