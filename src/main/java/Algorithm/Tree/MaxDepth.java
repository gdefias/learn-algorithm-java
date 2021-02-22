package Questions.Tree;
import Lib.Base;
import Lib.Util;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2016/3/21.

 二叉树的深度

 https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/

 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

 给定二叉树 [3,9,20,null,null,15,7]，

 3
 / \
 9  20
 /  \
 15   7
 返回它的最大深度 3

 节点总数 <= 10000
 */

public class MaxDepth {
    private static int depth = 0;

	public static void main(String[] args) {
        TreeNode root = Util.makeTree();
        int result = maxDepth4(root);
        System.out.println(result);
	}

    //方法1：递归 DFS 后序遍历 二叉树的深度是根节点的左子树与右子树较深的那一个加1
    public static int maxDepth1(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return Math.max(maxDepth1(root.left), maxDepth1(root.right))+1;
    }



    //方法2：递归 DFS 先序遍历 更新最大深度
    public static int maxDepth2(TreeNode root) {
        helper2(root, 1);
        return depth;
    }

    private static void helper2(TreeNode root, int creDepth) {
        if(root==null) {
            return;
        }
        if(creDepth > depth) {
            depth = creDepth;
        }
        helper2(root.left, creDepth+1);
        helper2(root.right, creDepth+1);
    }


    //方法3：递归  DFS 获得所有叶子结点的深度，然后找出所有叶子结点的深度最大的那一个
    public static int maxDepth3(TreeNode root) {
        if(root==null) {
            return 0;
        }

        List<Integer> leaflevels = new ArrayList<>();

        helper3(root, 1, leaflevels);

        //找出所有叶子结点的深度最大的那一个
        leaflevels.sort(null);
        return leaflevels.get(leaflevels.size()-1);
    }

    public static void helper3(TreeNode root, int level, List<Integer> leaflevels) {
        if(root.left==null && root.right==null) {
            leaflevels.add(level);
        }

        if(root.left!=null) {
            helper3(root.left, level+1, leaflevels);
        }

        if(root.right!=null) {
            helper3(root.right, level+1, leaflevels);
        }
    }

    //方法4： BFS 层序遍历
    public static int maxDepth4(TreeNode root) {
	    if(root==null) {
	        return 0;
        }

	    int level = 0;
	    Queue<TreeNode> queue1 = new LinkedList<>();
	    queue1.add(root);

	    while(!queue1.isEmpty()) {
	        int size = queue1.size();
	        for(int i=0; i<size; i++) {
	            TreeNode node = queue1.poll();
	            if(node.left != null) {
	                queue1.add(node.left);
                }

	            if(node.right != null) {
	                queue1.add(node.right);
                }
            }

	        level++;  //每访问一层加1
        }

	    return level;

    }

    //方法5： BFS、层序遍历  写法2
    public static int TreeHightGetter3(Base.TreeNode root) {
        if(root == null)
            return 0;
        List<Base.TreeNode> queue = new LinkedList<Base.TreeNode>() {{
            add(root);
        }};
        int res = 0;

        while(!queue.isEmpty()) {
            res++;  //每访问一层深度加1

            List<Base.TreeNode> qtmp = new LinkedList<>();  //存放下一层的所有结点
            for(Base.TreeNode node : queue) { //倒出当前层的所有结点
                if(node.left != null)
                    qtmp.add(node.left);
                if(node.right != null)
                    qtmp.add(node.right);
            }

            queue = qtmp;  //对下一层进行循环迭代

        }
        return res;
    }
}





