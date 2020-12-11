package Questions.Tree.BST;
import Lib.Util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static Lib.Base.TreeNode;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-11

 验证二叉搜索树
 给定一个二叉树，判断其是否是一个有效的二叉搜索树。

 https://leetcode-cn.com/problems/validate-binary-search-tree/

 假设一个二叉搜索树具有如下特征：
    节点的左子树只包含小于当前节点的数。
    节点的右子树只包含大于当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。

 输入:
 2
 / \
 1   3
 输出: true



 输入:
 5
 / \
 1   4
    / \
   3   6
 输出: false
 解释: 输入为: [5,1,4,null,null,3,6]。
      根节点的值为 5 ，但是其右子节点值为 4
 */
public class IsBST {
    public static int beforv = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = Util.makeTree4();
        System.out.println(isValidBST4(root));
    }


    //方法1：递归 时间复杂度 : O(n)  空间复杂度 : O(n)
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //递归函数：以root为根的子树中所有节点的值是否都在 (minVal, maxVal) 的范围内（开区间）
    public static boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null)
            return true;

        if (root.val >= maxVal || root.val <= minVal)
            return false;

        //递归调用左子树时需要把上界maxVal设为root.val
        //递归调用右子树时需要把下界minVal设为root.val
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }



    //方法2: 递归 类似方法1 使用ResultType
    public static boolean isValidBST2(TreeNode root) {
        return helper(root).is_bst;
    }

    private static ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        if (!left.is_bst || !right.is_bst) {
            //false的时候，范围随便给，不会被用到
            return new ResultType(false, 0, 0);
        }

        if (root.left != null && left.maxValue >= root.val || root.right != null && right.minValue <= root.val) {
            return new ResultType(false, 0, 0);
        }

        return new ResultType(true, Math.max(root.val, right.maxValue), Math.min(root.val, left.minValue));
    }


    static class ResultType {
        boolean is_bst;   //是否是BST
        int maxValue, minValue;  //子树的结点值得范围

        ResultType(boolean is_bst, int maxValue, int minValue) {
            this.is_bst = is_bst;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }


    /*
    方法3：中序遍历 非递归 栈 得到一个递增的数组

    如果一棵树是BST，那么对这棵树进行中序遍历可以得到一个升序数组

    如果中序遍历结果数组是升序的，是否能肯定就是二叉搜索树？
    1） 如果二叉搜索树定义为：左<=根<右，用中序遍历无法区分：
      20        20
     /           \
    20            20
    它们的中序遍历结果都一样，但是左边的是BST，右边的不是BST

    2）如果将二叉搜索树定义为：左<根<右  中序遍历就可以区分了
    **/
    public static boolean isValidBST3(TreeNode root) {
        if(root==null) {
            return true;
        }


        LinkedList<TreeNode> stack = new LinkedList<>();
        int beforv = Integer.MIN_VALUE;  //保存前一个结点的值
        TreeNode curr = root;

        while(curr!=null || !stack.isEmpty()) {
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if(curr.val<=beforv) {  //当前结点值小于或等于前一个结点的值就不是升序的了，也就可以判断不是BST了
                return false;
            }
            beforv = curr.val;
            curr = curr.right;

        }
        return true;
    }


    //中序遍历 递归  得到一个递增的数组
    public static boolean isValidBST4(TreeNode root) {
        if(root==null) {
            return true;
        }

        if(!isValidBST4(root.left)) {
            return false;
        }

        if(root.val<=beforv) {
            return false;
        }
        beforv = root.val;

        if(!isValidBST4(root.right)) {
            return false;
        }

        return true;
    }
}
