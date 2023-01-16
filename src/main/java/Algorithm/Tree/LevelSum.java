package Algorithm.Tree;
import static Lib.Util.*;
import java.util.*;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/8.

  二叉树的某层结点之和
 */

public class LevelSum {
    private static int sum = 0;

    public static void main(String[] args) {
        TreeNode root = makeTree();
        int result = levelSum(root, 3);
        System.out.println(result);
    }

    //方法1：BFS
    public static int levelSum(TreeNode root, int level) {
        int sum = 0;
        if(root==null || level<=0) {
            return sum;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if(index == level) {
                    sum += node.val;
                } else {
                    if(node.left!=null) {
                        queue.add(node.left);
                    }

                    if(node.right!=null) {
                        queue.add(node.right);
                    }
                }
            }
            if(index == level) {
                break;
            }
            index++;
        }

        return sum;
    }

    //方法2：DFS  累加指定层的节点值
    public static int levelSum2(TreeNode root, int level) {
        if(root==null || level<=0) {
            return sum;
        }
        helper2(root, 1, level);   //根节点为第1层
        return sum;
    }

    //DFS遍历到二叉树的每个结点
    private static void helper2(TreeNode root, int currentlevel, int level) {
        if(currentlevel == level) {
            sum = sum + root.val;
        }
        if(root.left != null) {
            helper2(root.left, currentlevel+1, level);
        }
        if(root.right != null) {
            helper2(root.right, currentlevel+1, level);
        }
    }


    //方法3: DFS 用一个hashmap保存各层节点的值
    public static int levelSum3(TreeNode root, int level) {
        int levelSum = 0;
        if(root==null || level<=0) {
            return levelSum;
        }
        //保存各层结点的值列表
        Map<Integer, List<Integer>> map = new HashMap<>();

        helper3(root, root.val, 1, map);  //根节点为第1层
        //System.out.println(map);

        //取出指定层的值列表然后求和
        List<Integer> result = map.get(level);
        //System.out.println(result);
        for(int i: result) {
            levelSum = levelSum + i;
        }

        return levelSum;
    }

    //DFS遍历到二叉树的每个结点
    public static void helper3(TreeNode root, int val, int level, Map<Integer, List<Integer>> map) {
        if(!map.containsKey(level)) {
            List<Integer> levelvals = new ArrayList<>();
            map.put(level, levelvals);
        }
        map.get(level).add(val);

        if(root.left!=null) {
            helper3(root.left, root.left.val, level+1, map);
        }

        if(root.right!=null) {
            helper3(root.right, root.right.val, level+1, map);
        }
    }




}
