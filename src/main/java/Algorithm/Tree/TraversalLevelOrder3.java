package Algorithm.Tree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import static Lib.Base.TreeNode;
import static Lib.Util.makeTree1;

/**
 * Created by Defias on 2020/07.
 * Description: 从上到下打印二叉树 III

 https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/

 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，
 其他行以此类推。

 例如:
 给定二叉树: [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7

 返回其层次遍历结果：
 [
 [3],
 [20,9],
 [15,7]
 ]

 节点总数 <= 1000
 */
public class TraversalLevelOrder3 {

    public static void main(String[] args) {
        TreeNode root = makeTree1();
        System.out.println(levelOrder(root));
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
}
