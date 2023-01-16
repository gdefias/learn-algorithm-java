package Algorithm.Tree.Treap;
import Lib.Base.TreeNode;
import Algorithm.Tree.Base.TraversalBinaryTree;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Defias on 2020/07.
 * Description: 序列化二叉树
 *
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 */


public class TreeSerialize {
    public static void main(String[] args) {
        TreeSerialize O = new TreeSerialize();
        TreeNode root = TraversalBinaryTree.mockTree();

        String res = O.serialize(root);
        System.out.println(res);

        TreeNode node = O.deserialize(res);
        TraversalBinaryTree.printTreeLevelOrder(node);
    }

    //层序遍历
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        res.append("[X,");

        Queue<TreeNode> queue = new LinkedList<>();

        if(root==null) {
            res.append("N"+",");
        } else {
            queue.offer(root);
        }

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node!=null) {
                res.append(String.valueOf(node.val)+",");
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                res.append("N"+",");
            }
        }
        res.append("X]");
        return res.toString();
    }

    //[X,8,3,10,1,6,N,14,N,N,4,7,13,N,N,N,N,N,N,N,X]
    public TreeNode deserialize(String data) {
        String[] res = data.split(",");
        TreeNode root = null;
        int index = 1;
        Queue<TreeNode> queue = new LinkedList<>();

        if(!res[index].equals("N")) {
            root = new TreeNode(Integer.parseInt(res[index]));
            queue.offer(root);
        }

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();

            index++;
            if(!res[index].equals("N")) {
                node.left = new TreeNode(Integer.parseInt(res[index]));
                queue.offer(node.left);
            }

            index++;
            if(!res[index].equals("N")) {
                node.right = new TreeNode(Integer.parseInt(res[index]));
                queue.offer(node.right);
            }
        }
        return root;
    }
}
