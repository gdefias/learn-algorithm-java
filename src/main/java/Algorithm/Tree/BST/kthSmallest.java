package Algorithm.Tree.BST;
import Lib.Util;
import java.util.ArrayList;
import java.util.LinkedList;
import static Lib.Base.*;

/**
 * Created by Defias on 2017/10/15.

 二叉搜索树中第K小的元素

 https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/

 给定一个二叉搜索树，编写一个函数kthSmallest来查找其中第k个最小的元素。

 说明：
 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

 输入: root = [3,1,4,null,2], k = 1
 3
 / \
 1   4
 \
 2
 输出: 1


 输入: root = [5,3,6,2,4,null,null,1], k = 3
 5
 / \
 3   6
 / \
 2   4
 /
 1
 输出: 3

 进阶：
 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 */
public class kthSmallest {
    public static int res=Integer.MAX_VALUE;
    public static int index = 0; //累加

    public static void main(String[] args) {
        TreeNode root = Util.makeTree4();
        System.out.println(kthSmallest3(root, 5));
    }

    //方法1：中序遍历 把遍历到的元素依次存入一个list中，list中的第k-1个元素即是第k小的元素  累加  递归
    public static int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> nums = new ArrayList<>();

        helper(root, nums);
        return nums.get(k-1);
    }

    public static void helper(TreeNode root, ArrayList<Integer> nums) {
        if(root==null) {
            return;
        }
        helper(root.left, nums);
        nums.add(root.val);
        helper(root.right, nums);
    }

    //方法2：中序遍历 直接找到第k小的元素后返回   递归
    public static int kthSmallest2(TreeNode root, int k) {
        helper2(root, k);
        return res;
    }

    public static void helper2(TreeNode root, int k) {
        if(root==null) {
            return;
        }
        helper2(root.left, k);

        if(res!=Integer.MAX_VALUE) {
            return;
        }
        index++;
        if(index == k) {
            res = root.val;
            return;
        }

        helper2(root.right, k);
    }

    //方法3：中序遍历  栈  往下减  非递归
    public static int kthSmallest3(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0)
                return root.val;
            root = root.right;
        }
    }


    //方法4：二分搜索
    //根据左子树的结点数量决定取左子树还是右子树进行递归查找
    //如果左子树恰好为k-1个，返回根的val，如果左子树节点数L小与k-1，去右子树中查找第k-L-1小的元素
    public static int kthSmallest4(TreeNode root, int k) {
        int count = countNodes(root.left);
        if(count == k-1) {
            return root.val;
        } else if(count>k-1) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k-count-1);
        }
    }

    //计算二叉树的结点数
    public static int countNodes(TreeNode root) {
        if(root==null)
            return 0;
        return countNodes(root.left)+countNodes(root.right)+1;
    }
}
