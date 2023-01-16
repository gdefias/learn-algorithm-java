package Algorithm.Tree;
import Lib.Util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/14.

 判断是否存在一条路径的路径和为目标值

 路径总和
 https://leetcode.cn/problems/path-sum/

 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 For example:
 Given the below binary tree and sum = 22,
 5
 / \
 4   8
 /   / \
 11  13  4
 /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 *
 */

public class PathSum2 {

    public static void main(String[] args) {
        TreeNode root = Util.makeTree3();
        System.out.println(hasPathSum2(root, 40));
    }

    //方法1：分治 递归 DFS 往下减
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)
            return false;

        if(root.val==sum && root.left==null && root.right==null) {
            return true;
        }

        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }

    //方法2：BFS 队列 累加
    public static boolean hasPathSum2(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> queueVal = new LinkedList<Integer>();

        queue.offer(root);
        queueVal.offer(root.val);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int val = queueVal.poll();
            if(node.left==null && node.right==null && val==sum) {
                return true;
            }

            if(node.left != null) {
                queue.offer(node.left);
                queueVal.offer(node.left.val + val);
            }

            if(node.right != null) {
                queue.offer(node.right);
                queueVal.offer(node.right.val + val);
            }
        }

        return false;
    }



    //方法3：后序遍历 栈 DFS 回溯 累加
    public static boolean hasPathSum3(TreeNode root, int sum) {
        if(root==null)
            return false;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode visited = null;
        TreeNode node = root;
        int s = 0;  //累加和

        while(node!=null || !stack.isEmpty()) {
            while(node!=null) {
                stack.push(node);
                s += node.val;  //累加
                node = node.left;
            }

            node = stack.peek();
            if(node.left==null && node.right==null && s==sum) {
                return true;
            }

            if (node.right == null || node.right == visited) {
                stack.pop();
                s -= node.val;
                visited = node;
                node = null;
            } else {
                //否则，继续遍历右子树
                node = node.right;
            }
        }
        return false;
    }
}
