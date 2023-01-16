package Algorithm.Tree;
import static Lib.Base.*;
import Lib.Util;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import static Algorithm.Tree.Base.TraversalBinaryTree.printTreeLevelOrder;
/**
 * Created with IntelliJ IDEA.
 * Description: 二叉树的镜像 / 翻转二叉树
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/invert-binary-tree/
 https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/

 完成一个函数，输入一个二叉树，输出它的镜像

 例如输入：
             4
         /      \
      2          7
     / \       / \
    1   3     6   9

 镜像输出：
             4
          /     \
         7      2
        / \    / \
       9  6   3  1
 0 <= 节点个数 <= 1000
 */
public class MirrorTree {
    public static void main(String[] args) {
        TreeNode A = Util.makeTree();
        printTreeLevelOrder(A);
        System.out.println();

        TreeNode B = mirrorTree5(A);
        printTreeLevelOrder(B);
    }


    //方法1：递归 DFS  交换左右子树
    public static TreeNode mirrorTree(TreeNode root) {
        if(root==null||(root.left==null && root.right==null))
            return root;

        //交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        root.left = mirrorTree(root.left);
        root.right = mirrorTree(root.right);
        return root;
    }


    //方法2：递归 DFS  后序遍历
    public static TreeNode mirrorTree2(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = mirrorTree2(root.left);
        TreeNode right = mirrorTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //方法3：递归 DFS  逆向后序遍历
    public static TreeNode mirrorTree3(TreeNode root) {
        if(root == null) {
            return root;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = mirrorTree3(right);
        root.right = mirrorTree3(left);

        return root;
    }


    //方法4：非递归  中序遍历
    //在访问每个节点的时候交换
    public static TreeNode mirrorTree4(TreeNode root) {
        if(root==null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while(!stack.isEmpty() || node!=null) {
            while(node!=null) {
                stack.push(node);
                node = node.left;
            }

           if(!stack.isEmpty()) {
               node = stack.pop();
               //左右子节点交换
               TreeNode temp = node.left;
               node.left = node.right;
               node.right = temp;

               //注意这里以前是node.right，因为上面已经交换了，所以这里要改为node.left
               node = node.left;
           }
        }
        return root;
    }


    //方法5：非递归  层序遍历
    //在访问每个节点的时候交换
    public static TreeNode mirrorTree5(TreeNode root) {
        if (root == null)      //如果为空直接返回
            return null;
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            //交换非叶子node节点的两个子节点
            if(node.left!=null || node.right!=null) {
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
            }

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return root;
    }
}
