package Algorithm.Tree;
import java.util.LinkedList;
import java.util.Queue;
import static Lib.Base.*;
import static Lib.Util.*;
/**
 * Created by Defias on 2017/10/8.

 二叉树叶子结点之和
 */

public class LeafSum {
    private static int sum = 0;  //叶结点之和

    public static void main(String[] args) {
        TreeNode root = makeTree();
        int result = leafSum2(root);
        System.out.println(result);
    }

    //DFS
    public static int leafSum(TreeNode root) {
        helper(root);
        return sum;
    }

    private static void helper(TreeNode node) {
        if(node==null) {
            return;
        }
        if(node.left==null && node.right==null) {
            sum = sum + node.val;
        }
        helper(node.left);
        helper(node.right);
    }


    //BFS
    public static int leafSum2(TreeNode root) {
        if(root==null) {
            return sum;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.left==null && node.right==null) {
                sum += node.val;
            }

            if(node.left!=null) {
                queue.add(node.left);
            }

            if(node.right!=null) {
                queue.add(node.right);
            }
        }
        return sum;
    }

}
