package Algorithm.Tree;
import static Lib.Base.*;
import static Algorithm.Tree.Base.TraversalBinaryTree.mockTree;
/**
 * @author: Defias
 * @date: 2020/12/23
 * @description: 计算给定二叉树中叶节点的数量
 */
public class LeafCount {
    public static int count = 0;

    public static void main(String[] args) {
        TreeNode root = mockTree();
        System.out.println(gtLeafCount(root));
    }

    //DFS
    public static int gtLeafCount(TreeNode root) {
        if(root==null) {
            return count;
        }

        if(root.left==null && root.right==null) {
            count++;
        }

        if(root.left!=null) {
            gtLeafCount(root.left);
        }

        if(root.right!=null) {
            gtLeafCount(root.right);
        }
        return count;
    }
}
