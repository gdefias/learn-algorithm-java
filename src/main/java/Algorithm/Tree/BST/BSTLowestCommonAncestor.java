package Questions.Tree.BST;
import static Lib.Base.*;
/**
 * Created by Defias on 2017/10/8.

 二叉查找树公共祖先
 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先

 https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/

 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能
 大（一个节点也可以是它自己的祖先）。”

 所有节点的值都是唯一的。
 p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class BSTLowestCommonAncestor {

    //方法1：递归  充分利用BST结点值的大小关系
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(p.val<root.val && q.val<root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if(p.val>root.val && q.val>root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
