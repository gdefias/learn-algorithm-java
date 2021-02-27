package Algorithm.Tree.BST;
import static Lib.Base.*;

/**
 * Created by Defias on 2017/10/7.

 插入一个结点到BST中，需要保证该树仍然是一棵二叉查找树
 */

public class BSTInsert {

    public static TreeNode inserTreeNode(TreeNode root, TreeNode node) {
        if(root==null) {
            return node;
        }

        if(node.val <= root.val) {
            root.left = inserTreeNode(root.left, node);
        } else {
            root.right = inserTreeNode(root.right, node);
        }
        return root;
    }
}
