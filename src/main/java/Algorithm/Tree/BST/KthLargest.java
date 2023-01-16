package Algorithm.Tree.BST;
import Lib.Util;

import static Lib.Base.*;
/**
 * Created by Defias on 2020/07.
 * Description: 二叉搜索树的第k大节点

 https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/

 给定一棵二叉搜索树，请找出其中第k大的节点。

 
 示例 1:

 输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
      \
      2

 输出: 4

 1 ≤ k ≤ 二叉搜索树元素个数
 */
public class KthLargest {
    public static int index = 0;
    public static int[] res;  //存放前k个大元素，最后一个元素为第k大
    public static int ress = 0;

    public static void main(String[] args) {
        TreeNode root = Util.makeTree4();
        System.out.println(kthLargest2(root, 4));
    }


    //方法1：通过改变中序遍历左右子树访问顺序（改为：右子树 --- 根结点 --- 左子树）得到降序排列的数组，取第k个
    //时间复杂度 : O(n)  空间复杂度 : O(n)
    public static int kthLargest(TreeNode root, int k) {
        res = new int[k];
        recur(root, k);
        return res[k-1];
    }

    public static void recur(TreeNode root, int k) {
        if(root==null) {
            return;
        }

        //先递归右子树
        recur(root.right, k);

        //index从0取到k-1
        if(index<k) {
            res[index++] = root.val;
        } else {
            return;
        }

        //递归左子树
        recur(root.left, k);
    }


    //方法2： 类似方法1  时间复杂度 : O(n)  空间复杂度 : O(1)
    public static int kthLargest2(TreeNode root, int k) {
        dfs(root, k);
        return ress;
    }

    public static void dfs(TreeNode root, int k) {
        if(root == null) {
            return;
        }

        dfs(root.right, k);

        index++;
        if(index == k) {
            ress = root.val;
            return;
        }

        dfs(root.left, k);
    }

    //方法3：快速排序 partition切分思想  递归
    public static int kthLargest3(TreeNode root, int k) {
        int rc = getNum(root.right);
        if(rc == k-1) {
            return root.val;
        } else if(rc > k-1) {
            return kthLargest3(root.right, k);
        } else {
            return kthLargest3(root.left, k-1-rc);
        }
    }

    public static int getNum(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return getNum(root.left) + getNum(root.right) + 1;
    }
}
