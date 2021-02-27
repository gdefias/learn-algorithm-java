package Algorithm.Tree;
import static Lib.Base.*;

/**
 * Created by Defias on 2017/10/7.

  扭转后等价的二叉树
  检查两棵二叉树是否在经过若干次扭转后可以等价。扭转的定义是，交换任意节点的左右子树

  等价的定义是，两棵二叉树必须为相同的结构，并且对应位置上的节点的值要相等
 */


public class IsTweakedIdentical {

    public static boolean isTweakedIdentical(TreeNode a, TreeNode b) {
        if(a==null && b==null) {
            return true;
        }
        if(a==null || b==null) {
            return false;
        }
        if(a.val!=b.val) {
            return false;
        }

        if(isTweakedIdentical(a.left, b.left) && isTweakedIdentical(a.right, b.right)) {
            return true;
        }
        if(isTweakedIdentical(a.left, b.right) && isTweakedIdentical(a.right, b.left)) {
            return true;
        }
        return false;

    }
}
