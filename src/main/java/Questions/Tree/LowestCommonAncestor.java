package Questions.Tree;
import java.util.*;

import static Lib.Base.*;
/**
 * Created by Defias on 2017/10/7.

 二叉树的最近公共祖先
 给定一棵二叉树，找到两个节点的最近公共父节点(LCA)

 https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/

 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且
 x的深度尽可能大（一个节点也可以是它自己的祖先）。”


     4
    / \
   3   7
  / \
 5   6

 LCA(3, 5) = 3
 LCA(5, 6) = 3
 LCA(6, 7) = 4


 在root为根的二叉树中找A,B的LCA:
 如果找到了就返回这个LCA；如果只碰到A，就返回A；如果只碰到B，就返回B；如果都没有，就返回null

 所有节点的值都是唯一的。
 A、B 为不同节点且均存在于给定的二叉树中。
 */
public class LowestCommonAncestor {

    //方法1：分治 DFS
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null || root == A || root == B) {
            return root;
        }

        // Divide
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);

        // Conquer
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }


    //方法2：分治 DFS  写法2
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode A, TreeNode B) {
        if(root==null || A==root || B==root) {
            return root;
        }

        //当root不为null且p和q都不是root时分三种情况：
        //1、A和B都在root的右子树中，那么在root的右子树中找到的最近公共祖先结点就是A和B在root为根的树中的最近公共祖先结点
        //2、A和B都在root的左子树中，那么在root的左子树中找到的最近公共祖先结点就是A和B在root为根的树中的最近公共祖先结点
        //3、A和B一个在root的左子树中一个在root的的右子树中，那么A和B在root为根的树中的最近公共祖先结点就是root

        //分别在root的左子树和右子树中去找A和B的最近公共祖先结点
        TreeNode left = lowestCommonAncestor2(root.left, A, B);
        TreeNode right = lowestCommonAncestor2(root.right, A, B);

        if(left==null) {
            return right;
        }

        if(right==null) {
            return left;
        }

        return root;
    }


    //方法3：BFS + HashMap
    public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        if(root==null || A==root || B==root) {
            return root;
        }


        Map<TreeNode, TreeNode> parents = new HashMap<>();  //存结点的父结点
        parents.put(root, null);  //根节点的父节点为null

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        //遍历找出A和B结点的父结点
        while(!parents.containsKey(A) || !parents.containsKey(B)) {
            TreeNode node = queue.poll();
            if(node.left!=null) {
                parents.put(node.left, node); //当前节点是其左子节点的父节点
                queue.add(node.left);
            }

            if(node.right!=null) {
                parents.put(node.right, node);  //当前节点是其右子节点的父节点
                queue.add(node.right);
            }
        }

        //A结点到root结点的路径上的所有结点（A的父亲到root 所有祖先）
        Set<TreeNode> set = new HashSet<>();
        while(A!=null) {
            set.add(A);
            A = parents.get(A);
        }

        //B结点到root结点的路径上哪个结点在上面的集合中，哪个结点即为最近公共祖先结点
        while(!set.contains(B)) {
            B = parents.get(B);
        }
        return B;
    }
}
