package Questions.Tree.BST;
import Lib.Base.*;
import Lib.Util;

/**
 * @author: Defias
 * @date: 2020/12/10
 * @description: 打印二叉搜索树的所有叶子节点
 */
public class PrintLeafNode {

    public static void main(String[] args) {
        TreeNode root = Util.makeTree4();
        print(root);
    }

    //方法1：先序遍历 递归
    public static void print(TreeNode root) {
        if(root==null) {
            return;
        }

        if(root.left==null && root.right==null) {
            System.out.print(root.val+" ");
        }

        if(root.left!=null) {
            print(root.left);
        }

        if(root.right!=null) {
            print(root.right);
        }
    }
}
