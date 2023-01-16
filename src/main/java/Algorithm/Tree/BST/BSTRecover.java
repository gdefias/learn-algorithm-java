package Algorithm.Tree.BST;
import Lib.Util;

import java.util.ArrayList;
import java.util.List;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/12.

 恢复二叉搜索树
 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。

 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？

 https://leetcode-cn.com/problems/recover-binary-search-tree/

 */
public class BSTRecover {
    public static void main(String[] args) {
        TreeNode root = Util.makeTree3();
        Util.printTreeInOrder(root);
        System.out.println();

        recoverTree(root);

        Util.printTreeInOrder(root);
    }

    public static void recoverTree(TreeNode root) {
        //中序遍历
        List<TreeNode> lists = new ArrayList<>();
        dfs(root, lists);

        //找到出错的两个位置
        TreeNode x = null;
        TreeNode y = null;
        int size = lists.size();
        for(int i=0; i<size-1; i++) {
            if(lists.get(i).val > lists.get(i+1).val) {
                y = lists.get(i+1);
                if(x == null) {
                    x = lists.get(i);
                } else {
                    break;
                }
            }
        }


        //恢复
        if(x!=null && y!=null) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;

        }
    }

    //中序遍历找出出错的两个结点
    public static void dfs(TreeNode root, List<TreeNode> lists) {
        if(root==null) {
            return;
        }

        dfs(root.left, lists);
        lists.add(root);
        dfs(root.right, lists);
    }
}

