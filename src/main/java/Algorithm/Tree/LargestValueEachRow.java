package Algorithm.Tree;
import static Lib.Base.*;
import static Lib.Util.*;
import java.util.*;
/**
 * Created by Defias on 2017/10/14.

 在每个树行中找最大值

 https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/

 您需要在二叉树的每一行中找到最大的值。

 Input:
 1
 / \
 3   2
 / \   \
 5   3   9

 Output: [1, 3, 9]
 */
public class LargestValueEachRow {

    public static void main(String[] args) {
        TreeNode root = makeTree3();
        List<Integer> res = largestValues2(root);
        for(int i: res) {
            System.out.print(i+" ");
        }
    }

    //BFS
    public static List<Integer> largestValues1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root==null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int level = queue.size();
            PriorityQueue<Integer> maxqueue = new PriorityQueue<Integer>(Collections.reverseOrder());
            for(int i=0; i<level; i++) {
                TreeNode node = queue.poll();
                maxqueue.add(node.val);
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
            res.add(maxqueue.poll());
        }
        return res;
    }

    //DFS
    public static List<Integer> largestValues2(TreeNode root) {
        if (root == null) {
            return null;
        }

        HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
        largestValues2Helper(res, root, 0);

        List<Integer> result = new ArrayList<>();
        for(Integer key: res.keySet()) {
            result.add(res.get(key));
        }
        return result;
    }

    public static void largestValues2Helper(HashMap<Integer, Integer> res, TreeNode root, int level) {
        if(res.containsKey(level)) {
            int Vmax = Math.max(res.get(level), root.val);
            res.put(level, Vmax);
        } else {
            res.put(level, root.val);
        }

        if(root.left!=null) {
            largestValues2Helper(res, root.left, level+1);
        }

        if(root.right!=null) {
            largestValues2Helper(res, root.right, level+1);
        }
    }

}
