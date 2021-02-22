package Questions.Tree;
import Lib.Util;
import java.util.LinkedList;
import java.util.Queue;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/8.

  二叉树最小深度
  最小深度是从根节点到最近叶子节点的最短路径上的节点数量

 */
public class MinDepth {

    public static void main(String[] args) {
        TreeNode root = Util.makeTree();
        int result = minDepth2(root);

        System.out.println(result);
    }

    //方法1：DFS
    public static int minDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if(left==0) {
            return right+1;
        }

        if(right==0) {
            return left+1;
        }

        return Math.min(left, right)+1;
    }

    //方法2：BFS
    public static int minDepth2(TreeNode root) {
        if(root==null) {
            return 0;
        }

        int mindepth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if(node.left==null && node.right==null) {  //首次遇到叶子结点立马剪枝
                    return mindepth+1;
                }

                if(node.left!=null) {
                    queue.add(node.left);
                }

                if(node.right!=null) {
                    queue.add(node.right);
                }

            }
            mindepth++;
        }

        return mindepth;
    }
}
