package Questions.Tree;
import Lib.Util;

import static Lib.Base.*;
import java.util.*;
/**
 * Created with IntelliJ IDEA.
 * Description: 二叉树中和为某一值的路径
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof

 输入一棵二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径，从树的根结点开始往下一直到叶结点所经过的结点形成一条路径
 给定如下二叉树，以及目标和 sum = 22，

        5
       / \
      4   8
     /   / \
    11  13  4
  /  \    / \
 7    2  5   1

 返回:
 [
 [5,4,11,2],
 [5,8,4,5]
 ]
 */
public class PathSum {
    private static List<List<Integer>> rootleafpaths;

    public static void main(String[] args) {
        TreeNode A = Util.makeTree();
        System.out.println(pathSum4(A, 21));
    }

    //方法1： 先序遍历  回溯  DFS  累加
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();  //存放最终结果
        Deque<Integer> path = new LinkedList<>();  //存放遍历到的路径
        int s = 0;  //已遍历路径的累加和

        backtrace(root, sum, s, path, result);
        return result;
    }

    public static void backtrace(TreeNode root, int sum, int s, Deque<Integer> path, List<List<Integer>> result) {
        if(root == null) {
            return;
        }
        path.addLast(root.val);
        s += root.val;

        if(root.left==null && root.right==null && s==sum) {
            result.add(new LinkedList<>(path));
        }

        if(root.left!=null) {
            backtrace(root.left, sum, s, path,  result);
        }

        if(root.right!=null) {
            backtrace(root.right, sum, s, path,  result);
        }

        path.removeLast();  //回溯
        s -= root.val;
    }

    //方法2： 先序遍历  回溯 DFS  往下减
    public static List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();  //存放最终结果
        Deque<Integer> path = new LinkedList<>();  //存放遍历到的路径

        backtrace2(root, sum, path, result);
        return result;
    }

    public static void backtrace2(TreeNode root, int sum, Deque<Integer> path, List<List<Integer>> result) {
        if(root == null) {
            return;
        }
        path.addLast(root.val);

        if(root.left==null && root.right==null) {
            if(root.val==sum) {
                result.add(new LinkedList<>(path));
            }
            path.removeLast();  //回溯
            return;
        }

        backtrace2(root.left, sum-root.val, path,  result);
        backtrace2(root.right, sum-root.val, path,  result);

        path.removeLast();  //回溯
    }


    //方法3：先序遍历  回溯  DFS  累加  栈
    public static List<List<Integer>> pathSum3(TreeNode root, int sum) {
        Stack<Integer> stack = new Stack<>(); //存放遍历到的路径
        List<List<Integer>> result = new LinkedList<>();  //存放最终结果
        recur(root, sum, stack, result);

        return result;
    }

    public static void recur(TreeNode root, int sum, Stack<Integer> stack, List<List<Integer>> result) {
        if(root==null)
            return;
        stack.push(root.val);

        if(root.left==null && root.right==null) {
            if(root.val==sum) {
                result.add(new LinkedList<>(stack));
            }
            stack.pop();  //回溯
            return;
        }

        if(root.left!=null) {
            recur(root.left, sum-root.val, stack, result);
        }

        if(root.right!=null) {
            recur(root.right, sum-root.val, stack, result);
        }
        stack.pop();
    }



    //方法4：求出所有路径，然后过滤出满足条件的
    public static List<List<Integer>> pathSum4(TreeNode root, int sum) {
        rootleafpaths = new ArrayList<>();
        List<Integer> rootleafpath = new ArrayList<>();
        rootleafpath.add(root.val);

        helper(root, rootleafpath);
        //System.out.println(rootleafpaths);

        List<List<Integer>> result = new LinkedList<>();  //存放最终结果

        for(int i=0; i<rootleafpaths.size(); i++) {
            List<Integer> path = rootleafpaths.get(i);
            if(sumpath(path) == sum) {
                result.add(path);
            }
        }

        return result;

    }

    public static void helper(TreeNode root, List<Integer> rootleafpath) {
        if(root==null)
            return;

        if(root.left==null && root.right==null) {
            rootleafpaths.add(rootleafpath);
        }

        if(root.left != null) {
            List<Integer> left = new ArrayList<>(rootleafpath);
            left.add(root.left.val);
            helper(root.left, left);
        }

        if(root.right != null) {
            List<Integer> right= new ArrayList<>(rootleafpath);
            right.add(root.right.val);
            helper(root.right, right);
        }
    }

    public static int sumpath(List<Integer> path) {
        int sum=0;
        for(Integer i: path) {
            sum += i;
        }
        return sum;
    }
}
