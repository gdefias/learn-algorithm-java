package Algorithm.Tree;
import static Lib.Base.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Defias on 2017/10/15.

 重建二叉树

 https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/

 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 前序遍历 preorder = [3,9,20,15,7]
 中序遍历 inorder = [9,3,15,20,7]

     3
   / \
  9   20
     /  \
    15   7

 0 <= 节点个数 <= 5000

 思路：
 前序遍历的第一个节点是根节点，只要找到根节点在中序遍历中的位置，在根节点之前被访问的节点都位于左子树，在根节点之后被访问的节点都位于右
 子树，由此可知左子树和右子树分别有多少个节点

 由于树中的节点数量与遍历方式无关，通过中序遍历得知左子树和右子树的节点数量之后，可以根据节点数量得到前序遍历中的左子树和右子树的分界，
 因此可以进一步得到左子树和右子树各自的前序遍历和中序遍历，可以通过递归的方式，重建左子树和右子树，然后重建整个二叉树

 */

public class BuildTree {
    public static void main(String[] args) {
        int[] preOrder = {1,2,4,7,3,5,6,8};
        int[] inOrder = {4,7,2,1,5,3,8,6};
        TreeNode root = buildTree(preOrder, inOrder);
        TraversalBinaryTree.printTreeLevelOrder(root);

    }

    //方法1：递归+HashMap  分治
    //使用一个Map存储中序遍历的每个元素及其对应的下标，目的是为了快速获得一个元素在中序遍历中的位置
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null || preorder.length==0) {
            return null;
        }

        HashMap<Integer, Integer> inordermap = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            inordermap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, inordermap);
    }

    public static TreeNode buildTree(int[] preorder, int i, int j, int[] inorder, int p, int q, HashMap<Integer, Integer> map) {
        //出口 前序遍历序列访问完
        if(i>j) {
            return null;
        }

        //根结点
        int rootval = preorder[i];
        TreeNode root = new TreeNode(rootval);

        //剪枝
        if(i==j) {
            return root;
        }

        //通过中序遍历序列获得当前根结点左右子树的结点数量
        int inordermid = map.get(rootval);
        int leftsize = inordermid-p;
        int rightsize = q-inordermid;

        //确定左右子树的前序和中序遍历序列的边界
        int lefti = i+1;  //左子树的左边界
        int leftj = lefti+leftsize-1;  //左子树的右边界

        int righti = leftj+1;  //右子树的左边界
        int rightj = righti+rightsize-1;  //右子树的右边界

        //递归重建左右子树
        root.left = buildTree(preorder, lefti, leftj, inorder, p, inordermid-1, map);
        root.right = buildTree(preorder, righti, rightj, inorder, inordermid+1, q, map);

        return root;
    }

    //方法2：递归+HashMap 分治 写法2
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        //把前序遍历的值和中序遍历的值放到list中
        List<Integer> preorderList = new ArrayList<>();
        List<Integer> inorderList = new ArrayList<>();
        for (int i = 0; i < preorder.length; i++) {
            preorderList.add(preorder[i]);
            inorderList.add(inorder[i]);
        }

        return buildTree2(preorderList, inorderList);
    }

    private TreeNode buildTree2(List<Integer> preorderList, List<Integer> inorderList) {
        //出口  中序遍历序列访问完
        if (inorderList.size() == 0)
            return null;

        //前序遍历的第一个值就是根节点
        int rootVal = preorderList.remove(0);
        //创建根结点
        TreeNode root = new TreeNode(rootVal);

        //查看根节点在中序遍历中的位置，然后再把中序遍历的数组劈两半，前面部分是根节点左子树的所有值，后面部分是根节点右子树的所有值
        int mid = inorderList.indexOf(rootVal);

        //[0，mid)是左子树的所有值，inorderList.subList(0, mid)表示截取inorderList的值，截取的范围是[0，mid)，包含0不包含mid
        root.left = buildTree2(preorderList, inorderList.subList(0, mid));

        //[mid+1，inorderList.size())是右子树的所有值，inorderList.subList(mid + 1, inorderList.size())表示截取inorderList
        //的值，截取的范围是[mid+1，inorderList.size())，包含mid+1不包含inorderList.size()
        root.right = buildTree2(preorderList, inorderList.subList(mid + 1, inorderList.size()));

        return root;
    }
}
