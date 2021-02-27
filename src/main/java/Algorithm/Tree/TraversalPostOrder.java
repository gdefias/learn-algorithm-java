package Algorithm.Tree;
import Lib.Base.*;
import Lib.Util;
import org.w3c.dom.NodeList;
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
        List<Integer> res = new ArrayList<Integer>();
        recur(root, res);
        return res;
    }

    public static void recur(TreeNode root, List<Integer> res) {
        if(root==null) {
            return;
        }
        recur(root.left, res);
        recur(root.right, res);
        res.add(root.val);
    }

    //方法2：迭代  非递归
    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        recur2(root, res);
        return res;
    }

    public static void recur2(TreeNode root, List<Integer> res) {
        if(root==null) {
            return;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        TreeNode visited = null;  //标示节点的右子节点是否已经访问过了

        while(node!=null || !stack.isEmpty()) {
            while(node!=null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.peek();
            if(node.right==null || node.right==visited) {
                stack.pop();
                visited = node;
                res.add(node.val);
                node = null;
            } else {
                node = node.right;
            }
        }
    }


}
