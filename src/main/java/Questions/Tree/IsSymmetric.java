package Questions.Tree;
import static Lib.Base.*;
/**
 * Created by Defias on 2020/07.
 * Description: 对称的二叉树

 https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/

 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     1
    / \
   2   2
  / \ / \
 3  4 4  3

 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     1
    / \
   2   2
    \   \
    3    3



 输入：root = [1,2,2,3,4,4,3]
 输出：true

 0 <= 节点个数 <= 1000
 */
public class IsSymmetric {

    //方法1
    public boolean isSymmetric(TreeNode root) {
        if(root==null) {
            return true;
        }

        return recur(root.left, root.right);
    }

    public boolean recur(TreeNode left, TreeNode right) {
        if(left==null && right==null) {
            return true;
        }

        if(left==null || right==null || left.val!=right.val) {
            return false;
        }

        return recur(left.left, right.right) && recur(left.right, right.left);
    }

    //方法2
    public static boolean isSymmetric2(TreeNode root) {
        if(root==null)
            return true;

        return recur2(root.left, root.right);
    }

    public static boolean recur2(TreeNode left, TreeNode right) {
        if(left==null || right==null) {
            return left == right;
        }

        return left.val==right.val && recur2(left.left, right.right) && recur2(left.right, right.left);
    }
}
