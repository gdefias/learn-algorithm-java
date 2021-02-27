package Algorithm.Tree;
import Lib.Util;
import java.util.LinkedList;
import java.util.Queue;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/8.

 翻转二叉树
 翻转一棵二叉树

 https://leetcode-cn.com/problems/invert-binary-tree/

 输入：
 4
 /   \
 2     7
 / \   / \
 1   3 6   9

 输出：
 4
 /   \
 7     2
 / \   / \
 9   6 3   1
 */

public class Reverse {
    public static void main(String[] args) {
        TreeNode root = Util.makeTree();
        System.out.println("Invert befor:");
        Util.printTreePreOrder(root);

        System.out.println("\nInvert after:");
        Util.printTreePreOrder(reverse2(root));
    }

    //方法1：递归  DFS
    public static TreeNode reverse(TreeNode root) {
        if(root==null) {
            return null;
        }
        helper(root);
        return root;
    }

    private static void helper(TreeNode root) {
        //交换左右子树
        if(root.left!=null || root.right!=null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }

        //对不为null的子树递归进行翻转
        if(root.left!=null) {
            helper(root.left);
        }

        if(root.right!=null) {
            helper(root.right);
        }
    }


    //方法2：BFS
    public static TreeNode reverse2(TreeNode root) {
        if(root==null) {
            return null;
        }
        helper2(root);
        return root;
    }

    private static void helper2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if(node.left!=null || node.right!=null) {
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
            }

            if(node.left!=null) {
                queue.offer(node.left);
            }

            if(node.right!=null) {
                queue.offer(node.right);
            }
        }
    }

}
