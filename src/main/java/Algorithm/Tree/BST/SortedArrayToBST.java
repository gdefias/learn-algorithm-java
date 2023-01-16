package Algorithm.Tree.BST;
import Algorithm.Tree.Base.TraversalBinaryTree;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/7.

 排序数组转高度最小的BST
 给一个排序数组（从小到大），将其转换为一棵高度最小的排序二叉树

 给出数组 [1,2,3,4,5,6,7], 返回
         4
       /   \
     2     6
   / \    / \
  1   3  5   7

 思路：
 根节点是数组索引中间值，左边的元素是左子树，右边的元素是右子树
 对左右子树的创建也是同理，先创建根节点（中间值），二分后左边元素是左子树，右边元素是右子树，重复这个二分过程直到所有元素创建完毕
 */
public class SortedArrayToBST {

    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,6,7,8,9};

        TreeNode root = sortedArrayToBST(A);

        TraversalBinaryTree.printTreeLevelOrder(root);
        System.out.println(IsBST.isValidBST3(root));  //验证BST
    }

    //中序遍历 总是选择中间位置左边的数字作为根节点
    //时间复杂度：O(n) 空间复杂度：O(logn)
    public static TreeNode sortedArrayToBST(int[] A) {
        int len = A.length;
        TreeNode root = null;

        root = helper(root, A, 0, len-1);
        return root;
    }

    public static TreeNode helper(TreeNode root, int[] A, int left, int right) {
        if(left <= right) {
            int mid = left+(right-left)/2;
            root = new TreeNode(A[mid]);
            root.left = helper(root.left, A, left, mid-1);
            root.right = helper(root.right, A, mid+1, right);
        }

        return root;
    }
}
