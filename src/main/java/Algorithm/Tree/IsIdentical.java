package Algorithm.Tree;
import static Lib.Base.*;
/**
 * Created by Defias on 2017/10/7.

 等价二叉树
 检查两棵二叉树是否等价
 等价的意思是说，首先两棵二叉树必须拥有相同的结构，并且每个对应位置上的节点上的数都相等
 */


public class IsIdentical {

    public static boolean isIdentical(TreeNode a, TreeNode b) {
        if(a==null && b==null) {
            return true;
        } else if(a==null || b==null) {
            return false;
        } else {
            if(a.val==b.val && isIdentical(a.left, b.left) &&  isIdentical(a.right, b.right))
                return true;
            else
                return false;
        }
    }

    //写法2
    public boolean isIdentical2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else {
            return root1.val == root2.val
                    && isIdentical(root1.left, root2.left)
                    && isIdentical(root1.right, root2.right);
        }
    }
}
