package Algorithm.Tree;
import static Lib.Base.*;
import Lib.Util;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Created with IntelliJ IDEA.
 * Description:  树的子结构
 * User: Defias
 * Date: 2018

 https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/

 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 B是A的子结构， 即 A中有出现和B相同的结构和节点值。

 0 <= 节点个数 <= 10000
 */
public class IsSubStructure {
    public static void main(String[] args) {
        TreeNode A = Util.makeTree();
        TreeNode B = Util.makeTree2();
        System.out.println(isSubStructure1(A, B));
    }

    //方法1：递归
    public static boolean isSubStructure1(TreeNode A,  TreeNode B) {
        if(B==null || A==null)
            return false;

        return isRootSubtree(A, B) || isSubStructure1(A.left, B) || isSubStructure1(A.right, B);
    }

    //两个子树是否从根开始构成B是A的子结构
    public static boolean isRootSubtree(TreeNode A,  TreeNode B) {
        if(B==null) {
            return true;  //B子树的结点都比较完了，确定是A的子结构，所以返回true
        }

        if(A!=null && A.val==B.val) {
            return isRootSubtree(A.left, B.left) && isRootSubtree(A.right, B.right);
        }
        return false;
    }


    //方法2：BFS
    public static boolean isSubStructure2(TreeNode A,  TreeNode B) {
        if(B==null || A==null)
            return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(A);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(isRootSubtree(node, B)) {
                return true;
            }
            if(node.left!=null) {
                queue.offer(node.left);
            }

            if(node.right!=null) {
                queue.offer(node.right);
            }
        }

        return false;
    }
}
