package Algorithm.Tree;
import java.util.*;
import static Lib.Base.TreeNode;
import static Algorithm.Tree.Base.TraversalBinaryTree.mockTree;

/**
 * Created by Defias on 2020/07.
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        /*
        制造一棵测试二叉树
                        8
                     /     \
                    3       10
                  /  \        \
                 1    6        14
                     / \       /
                    4   7    13
        * */
        TreeNode root = mockTree();

        //层序遍历
//        printTreeLevelOrder(root);
//        System.out.println("\n-----层序遍历-----\n");
//
//        List<List<Integer>> res = levelOrder(root);
//        System.out.println(res);


        List<List<Integer>> res = pathSum(root, 12);
        System.out.println(res);

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int c = 1;
        while(!queue.isEmpty()) {
            LinkedList<Integer> dres = new LinkedList<>();
            TreeNode node;
            for(int i=queue.size(); i>0; i--) {
                node = queue.poll();
                if((c&1)==1) {
                    dres.addLast(node.val);
                } else {
                    dres.addFirst(node.val);
                }
                boolean dumpleft = (node.left!=null &&  queue.add(node.left));
                boolean dumpright = (node.right!=null &&  queue.add(node.right));
            }
            result.add(dres);
            c++;
        }
        return result;

    }


    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        int s = 0;  //已遍历路径的累加和

        backtrace(root, sum, path, s, result);

        return result;
    }

    public static void backtrace(TreeNode root, int sum,  Deque<Integer> path, int s, List<List<Integer>> result) {
        if(root == null){
            return;
        }

        if(root.left==null && root.right==null) {
            if(s + root.val == sum) {
                path.addLast(root.val);
                result.add(new LinkedList<>(path));
                path.removeLast();   //回溯
            }
            return;
        }

        path.addLast(root.val);

        if(root.left!=null) {
            backtrace(root.left, sum, path, s+root.val, result);
        }

        if(root.right!=null) {
            backtrace(root.right, sum, path, s+root.val, result);
        }

        path.removeLast();  //回溯
    }
}
