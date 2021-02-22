package Questions.Tree.BST;
import Lib.Util;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/12.

 恢复二叉搜索树
 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。

 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？

 https://leetcode-cn.com/problems/recover-binary-search-tree/

 */
public class BSTRecover {
    public static TreeNode firstElement = null;
    public static TreeNode secondElement = null;
    public static TreeNode prev = null;

    public static void main(String[] args) {
        TreeNode root = Util.makeTree3();
        Util.printTreeInOrder(root);
        System.out.println();
        recoverTree(root);
        Util.printTreeInOrder(root);
    }

    public static void recoverTree(TreeNode root) {
        traverse(root);

        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
        //System.out.println(firstElement.val);
        //System.out.println(secondElement.val);
    }

    //中序遍历找出出错的两个结点
    public static void traverse(TreeNode root) {
        if(root==null) {
            return;
        }

        traverse(root.left);

        if(prev!=null && prev.val>root.val) {
            if(firstElement==null) {
                firstElement = prev;
                secondElement = root;
            } else {
                secondElement = root;
            }
        }
        prev = root;

        traverse(root.right);
    }
}

