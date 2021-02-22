package Questions.Tree;
import Lib.Util;
import java.util.Stack;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/14.

 判断是否存在一条路径的路径和为目标值

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
        System.out.println(hasPathSum(root, 40));
    }

    //方法1：分治 递归 往下减
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)
            return false;

        if(root.val==sum && root.left==null && root.right==null) {
            return true;
        }

        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }



    //方法2：后序遍历 栈 DFS 累加
    public static boolean hasPathSum2(TreeNode root, int sum) {
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
