package Algorithm.Tree.BST;
import Lib.Util;

import java.util.List;

import static Lib.Base.TreeNode;

/**
 * @author: Felix
 * @date: 2022/12/27
 * @description: 恢复二叉搜索树
 *
 * 动态规划的思想   进一步节省空间
 */


public class BSTRecover2 {
    public static TreeNode x = null;
    public static TreeNode y = null;
    public static TreeNode prev = null;

    public static void main(String[] args) {
        TreeNode root = Util.makeTree3();
        Util.printTreeInOrder(root);
        System.out.println();

        recoverTree(root);

        Util.printTreeInOrder(root);
    }

    public static void recoverTree(TreeNode root) {
        //中序遍历 过程中找到出错的两个位置
        dfs(root);

        //恢复
        if(x!=null && y!=null) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;

        }
    }

    public static void dfs(TreeNode root) {
        if(root==null) {
            return;
        }

        dfs(root.left);

        if(prev==null) {
            prev = root;
        } else {
            if(prev.val>root.val) {
                y = root;
                if(x == null) {
                    x = prev;
                }
            } else {
                prev = root;
            }
        }

        dfs(root.right);
    }

}
