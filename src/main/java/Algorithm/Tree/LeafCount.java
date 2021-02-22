package Questions.Tree;
import Lib.Base;
import static Questions.Tree.BinaryTree.mockTree;
/**
 * @author: Defias
 * @date: 2020/12/23
 * @description: 计算给定二叉树中叶节点的数量
 */
public class LeafCount {
    public static int count = 0;
    public static int maxDepth = 0;

    public static void main(String[] args) {
        Base.TreeNode root = mockTree();
        System.out.println(gtLeafCount(root));
    }

    //方法1
    public static int gtLeafCount(Base.TreeNode root) {
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
