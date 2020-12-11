package Questions.Tree;
import Lib.Base.*;
import Lib.Util;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author: Defias
 * @date: 2020/12/10
 * @description: 二叉树的中序遍历
 */
public class TraversalInOrder {
    public static void main(String[] args) {
        TreeNode root = Util.makeTree3();
        System.out.println(inorderTraversal2(root));
    }

    //方法1：递归
    public static ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        helper(root, result);
        return result;
    }

    public static void helper(TreeNode root, ArrayList<Integer> result) {
        if(root==null) {
            return;
        }
        helper(root.left, result);
        result.add(root.val);
        helper(root.right, result);
    }


    //方法2：栈  非递归
    public static ArrayList<Integer> inorderTraversal2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root==null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr!=null || !stack.isEmpty()) {
            while(curr!=null) {
                stack.add(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }

}
