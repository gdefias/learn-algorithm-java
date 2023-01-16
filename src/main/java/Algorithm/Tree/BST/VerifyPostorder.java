package Algorithm.Tree.BST;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description: 二叉搜索树的后序遍历序列
 * User: Defias
 * Date: 2018-10

 https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/

 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果
 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同

 参考以下这颗二叉搜索树：

       5
     / \
    2   6
  / \
 1   3

 输入: [1,6,3,2,5]
 输出: false

 输入: [1,3,2,6,5]
 输出: true

 数组长度 <= 1000

 思路：
 [3，5，4，10，12，9]
 后序遍历的最后一个数字一定是根节点，所以数组中最后一个数字9就是根节点，我们从前往后找到第一个比9大的数字10，那么10后面的[10，12]
 （除了9）都是9的右子节点，10前面的[3，5，4]都是9的左子节点，后面的需要判断一下，如果有小于9的，说明不是二叉搜索树，直接返回false。
 然后再以递归的方式判断左右子树
 */

public class VerifyPostorder {
    public static void main(String[] args) {
        //int[] A = {5, 7, 6, 9, 11, 7, 8};
        int[] A = {1,6,3,2,5};
        System.out.println(verifyPostorder2(A));
    }

    //方法1：递归 分治 从前往后看
    public static boolean verifyPostorder(int[] A) {
        if(A==null || A.length==1)
            return true;
        return helper(A, 0, A.length-1);
    }

    public static boolean helper(int[] A, int i, int j) {
        if(i>=j)
            return true;

        int rootval = A[j];
        int mid = j;  //从前往后第一个比rootval大的值的下标
        for(int k=i; k<j; k++) {
            if(A[k] > rootval) {
                mid = k;
                break;
            }
        }

        for(int k=mid; k<j; k++) {
            if(A[k] < rootval) {
                return false;
            }
        }

        return helper(A, i, mid-1) && helper(A, mid, j-1);
    }

    //方法2：非递归 单调栈   从后往前看  [1,6,3,2,5]  [1,3,2,6,8,7,5]
    public static boolean verifyPostorder2(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int parent = Integer.MAX_VALUE;

        //for循环是倒序遍历的
        for(int i = postorder.length-1; i>=0; i--) {
            int cur = postorder[i];
            //如果当前节点小于栈顶元素，说明栈顶元素和当前值构成了倒序，
            //说明当前节点是前面某个节点的左子节点，我们要找到他的父节点
            while(!stack.isEmpty() && stack.peek() > cur)
                parent = stack.pop();
            //只要遇到了某一个左子节点，才会执行上面的代码，才会更
            //新parent的值，否则parent就是一个非常大的值，也就
            //是说如果一直没有遇到左子节点，那么右子节点可以非常大

            if(cur > parent)
                return false;

            stack.add(postorder[i]);
        }
        return true;

    }

}
