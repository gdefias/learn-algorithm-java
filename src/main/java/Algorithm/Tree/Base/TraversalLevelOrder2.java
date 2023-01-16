package Algorithm.Tree.Base;
import static Lib.Util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/7.

 二叉树层序遍历（从上到下打印二叉树 II）

 https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/

 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

 示例：
 二叉树：[3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 返回其层次遍历结果：
 [
 [3],
 [9,20],
 [15,7]
 ]
 */

public class TraversalLevelOrder2 {
    public static void main(String[] args) {
        TreeNode root = makeTree1();
        System.out.println(BFSSolution(root));
    }


    //方法1：BFS
    public static List<List<Integer>> BFSSolution(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        Q.offer(root);

        while(!Q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int Qsize = Q.size();  //Qsize即当前层的元素个数
            for(int i=0; i<Qsize; i++) {
                TreeNode node = Q.poll();
                level.add(node.val);
                if(node.left != null) {
                    Q.offer(node.left);
                }
                if(node.right != null) {
                    Q.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }


    //方法2：DFS
    public List<List<Integer>> DFSSolution(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(res, root, 0); //从index第0层开始
        return res;
    }

    public static void dfs(List<List<Integer>> res, TreeNode root, int index) {
        if(root==null) {
            return;
        }

        if(index>=res.size()) {
            res.add(new LinkedList<Integer>());
        }

        res.get(index).add(root.val);

        dfs(res, root.left, index+1);
        dfs(res, root.right, index+1);
    }


    //方法3：Queue + Dummy node   BFS
    //使用null结点标识queue中层与层节点的间隔
    public static List<List<Integer>> BFSSolution2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        Q.offer(root);
        Q.offer(null); // dummy node

        ArrayList<Integer> level = new ArrayList<Integer>();
        while (!Q.isEmpty()) {
            TreeNode node = Q.poll();
            if (node == null) {    //访问到null说明访问完了一层
                if (level.size() == 0) { //说明所有层都访问完了，退出即可
                    break;
                }
                result.add(level);  //把访问的这一层的结果保存到最终结果中

                //为下一层的访问重置level
                level = new ArrayList<Integer>();
                //当前层访问完 给下一层添加null标识
                Q.offer(null);
                continue;
            }

            level.add(node.val);
            if (node.left != null) {
                Q.offer(node.left);
            }
            if (node.right != null) {
                Q.offer(node.right);
            }
        }
        return result;
    }

    //方法4：Two Queue    BFS
    public static List<List<Integer>> BFSSolution3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayList<TreeNode> Q1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> Q2 = new ArrayList<TreeNode>();

        Q1.add(root);
        while (Q1.size() != 0) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            Q2.clear();
            for (int i = 0; i < Q1.size(); i++) {
                TreeNode node = Q1.get(i); //每一层都是从Q1中读取的
                level.add(node.val);

                if (node.left != null) {
                    Q2.add(node.left);  //下一层放在Q2中，相当于间接的进行了层与层的隔离
                }
                if (node.right != null) {
                    Q2.add(node.right);
                }
            }

            //交换两个队列，以便能访问到Q2
            ArrayList<TreeNode> temp = Q1;
            Q1 = Q2;
            Q2 = temp;

            //把访问的这一层的结果保存到最终结果中
            result.add(level);
        }
        return result;
    }




}
