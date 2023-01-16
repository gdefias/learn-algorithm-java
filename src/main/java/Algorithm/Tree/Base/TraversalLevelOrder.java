package Algorithm.Tree.Base;
import java.util.*;

import static Lib.Base.TreeNode;
import static Lib.Util.listToIntArray;
import static Lib.Util.makeTree1;

/**
 * Created by Defias on 2020/07.
 * Description: 从上到下打印二叉树

 https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/

 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

 例如:
 给定二叉树:[3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7

 返回：
 [3,9,20,15,7]

 节点总数 <= 1000
 */


public class TraversalLevelOrder {

    public static void main(String[] args) {
        TreeNode root = makeTree1();
        int[] res = levelOrder(root);

        for(int i=0; i<res.length; i++) {
            System.out.println(res[i]);
        }
    }

    public static int[] levelOrder(TreeNode root) {
        List<Integer> lists = levelOrderCron(root);
        return listToIntArray(lists);
    }

    public static List<Integer> levelOrderCron(TreeNode root) {
        List<Integer> lists = new ArrayList<>();
        if(root==null) {
            return lists;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            lists.add(node.val);
            if(node.left!=null) {
                queue.offer(node.left);
            }
            if(node.right!=null) {
                queue.offer(node.right);
            }
        }
        return lists;
    }
}
