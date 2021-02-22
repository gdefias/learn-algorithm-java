package Questions.Tree;
import static Lib.Base.*;
import static Lib.Util.makeTree1;

import java.util.*;
/**
 * Created by Defias on 2017/10/14.

  二叉树的锯齿形层次遍历(之字形层序遍历)

  https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/

  给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。


 给定二叉树 [3,9,20,null,null,15,7],
      3
     / \
    9  20
      /  \
     15   7

 返回锯齿形层次遍历如下：
 [
 [3],
 [20,9],
 [15,7]
 ]
 */
public class TraversalziLevelOrder {

    public static void main(String[] args) {
        TreeNode root = makeTree1();
        System.out.println(zigzagLevelOrder2(root));
    }

    //方法1:BFS
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root==null) {
            return null;
        }

        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        boolean flag = true;  //控制存放顺序
        queue.offer(root);
        while(!queue.isEmpty()) {
            int levelnum = queue.size();

            //每一层的值
            List<Integer> levels = new LinkedList<Integer>();

            for(int i=0; i<levelnum; i++) {
                TreeNode node = queue.poll();
                if(flag) {
                    levels.add(node.val);  //往后存
                } else {
                    levels.add(0, node.val);  //往前存
                }

                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
            res.add(levels);
            flag = !flag;  //交替
        }
        return res;
    }


    //方法2: DFS  先序遍历
    public static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        helper(res, root, 0);  //从index第0层开始
        return res;
    }

    public static void helper(List<List<Integer>> res, TreeNode root, int index) {
        if(root==null) {
            return;
        }

        //res.size()为已存放的层数，需要大于当前index，以存放index层的值
        if(res.size()<=index) {
            res.add(new LinkedList<Integer>());
        }

        List<Integer> indexlist = res.get(index);
        if(index%2==0) {  //交替
            indexlist.add(root.val);  //往后存
        } else {
            indexlist.add(0, root.val);  //往前存
        }

        helper(res, root.left, index+1);

        helper(res, root.right, index+1);
    }
}
