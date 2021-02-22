package Questions.Tree;
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

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            //注意：先放右节点，再放左节点，这样拿出来时就刚好符合前序遍历的前左后右的顺序
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
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


