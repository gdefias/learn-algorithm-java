package Algorithm.Tree.Base;
import Lib.Util;
import java.util.ArrayList;
import java.util.Stack;
import static Lib.Base.TreeNode;
/**
 * Created by Jeff on 2016/5/2.

  二叉树的前遍历
 */

public class TraversalPreOrder {

    public static void main(String[] args) {
        TreeNode root = Util.makeTree3();

        System.out.println(preorderTraversal3(root));
    }

    //方法1：递归
    public static ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        traverse(root, result);
        return result;
    }

    private static void traverse(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        traverse(root.left, result);
        traverse(root.right, result);
    }


    //方法2：非递归 栈
    public static ArrayList<Integer> preorderTraversal2(TreeNode root) {
        ArrayList<Integer> result = new  ArrayList<>();
        if(root==null) {
            return result;
        }

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode node = root;
        while(node!=null || !s.isEmpty()) {
            while(node!=null) {
                result.add(node.val);
                s.push(node);
                node = node.left;
            }

            node = s.pop();
            node = node.right;
        }

        return result;
    }




    //方法3：分治  递归
    public static ArrayList<Integer> preorderTraversal3(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if (root == null) {
            return result;
        }

        //Divide
        ArrayList<Integer> left = preorderTraversal3(root.left);
        ArrayList<Integer> right = preorderTraversal3(root.right);

        //Conquer
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }

}


