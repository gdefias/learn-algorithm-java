package Algorithm.Tree.AVLTree;
import Lib.Base.*;
import Algorithm.Tree.BST.BST;

/**
 * @author: Defias
 * @date: 2020/12/10
 * @description: 平衡二叉树 AVL

 https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/

 输入一棵二叉树的根节点，判断该树是不是平衡二叉树
 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树


 给定二叉树 [3,9,20,null,null,15,7]
    3
    / \
    9  20
    /  \
    15   7
    返回 true

 给定二叉树 [1,2,2,3,3,null,null,4,4]
    1
    / \
    2   2
    / \
    3   3
    / \
    4   4
    返回 false

 1 <= 树的结点个数 <= 10000

 */
public class IsBalanced {

    public static void main(String[] args) {
        BST bst = BST.mockBST();
        BST bst2 = BST.mockBST();


        System.out.println(getHeight(bst.root));
        System.out.println(isBalanced(bst.root));
        System.out.println("\n------");

    }


    //方法1：暴力法 先序遍历 + 判断深度 （从顶至底）  DFS
    //此方法容易想到，但会产生大量重复计算，时间复杂度较高  时间复杂度O(NlogN) 空间复杂度O(N)
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        //左右子树高度差
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
            return false;
        }

        //左右子树本身也要是平衡的
        return isBalanced(root.left) && isBalanced(root.right);
    }

    //获取树的深度/高度  DFS
    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }



    //方法2： 先序遍历+判断深度+剪枝 （从顶至底） DFS  O(N)
    public static boolean isBalanced2(TreeNode root) {
        return helper(root).isBalanced;
    }

    private static ResultType2 helper(TreeNode root) {
        if (root == null) {
            return new ResultType2(true, 0);
        }

        ResultType2 left = helper(root.left);
        ResultType2 right = helper(root.right);

        // 当子树不平衡时，不会使用到深度maxDepth字段
        if (!left.isBalanced || !right.isBalanced) {
            return new ResultType2(false, -9);
        }

        // 即使左右子树都是平衡的当前树也不一定就是平衡的，还需要看高度差
        if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
            return new ResultType2(false, -9);
        }

        //当递归判断时，上层会使用到深度maxDepth字段，需要正确计算出来
        return new ResultType2(true, Math.max(left.maxDepth, right.maxDepth) + 1);
    }

    // ResultType类是结果类型，即递归函数要达到的目的
    // ResultType中真正有用的或者说真正要得到结果的是属性isBalance，而maxDepth是用于在完成isBalance的过程中进行辅助
    static class ResultType2 {
        public boolean isBalanced;
        public int maxDepth;
        public ResultType2(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }


    //方法3：先序遍历 + 判断深度 + 剪枝 （从顶至底）  DFS
    //对暴力法的优化，不判断是否平衡，只计算高度，是否平衡蕴含在高度maxDepth字段中  O(N)
    public static boolean isBalanced3(TreeNode root) {
        return helper3(root) != -99;  //高度为-99时返回false 代表不是平衡二叉树
    }

    //计算二叉树的高度，如果不平衡会立马剪枝叶而不继续计算
    public static int helper3(TreeNode root) {
        if(root==null) {
            return 0;
        }

        int leftdepth;
        if((leftdepth = helper3(root.left)) == -99) {  //左子树不平衡
            return -99;
        }

        int rightdepth;
        if((rightdepth = helper3(root.right)) == -99) {  //右子树不平衡
            return -99;
        }

        //左右子树都平衡了 计算左右子树的高度差进行剪枝
        return  Math.abs(leftdepth-rightdepth)<=1 ? Math.max(leftdepth, rightdepth)+1 : -99;
    }
}


