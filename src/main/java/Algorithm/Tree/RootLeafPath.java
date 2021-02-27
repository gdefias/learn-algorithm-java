package Algorithm.Tree;
import Lib.Util;
import java.util.ArrayList;
import java.util.List;
import static Lib.Base.TreeNode;
/**
 * Created by Defias on 2017/10/7.

  所有根到叶子的路径串
 */
public class RootLeafPath {

    public List<String> result;

    public static void main(String[] args) {
        System.out.println("\n-----根到叶子节点的所有路径-----\n");
        TreeNode root = Util.makeTree();
        List<String> paths = getPath1(root);
        System.out.println(paths);
    }


    //方法1：先序遍历  回溯  DFS  字符串累加（天然的回溯）
    public static List<String> getPath1(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root==null) {
            return result;
        }

        String path = String.valueOf(root.val);
        helper(root, result, path);
        return result;
    }

    public static void helper(TreeNode root, List<String> result, String path) {

        //访问到叶子结点获得一条路径
        if (root.left == null && root.right == null) {
            result.add(path);
        }

        if (root.left != null) {
            helper(root.left, result, path+"->"+root.left.val);
        }

        if (root.right != null) {
            helper(root.right, result, path+"->"+root.right.val);
        }

    }

    //写法2
    public List<String> getPath2(TreeNode root) {
        result = new ArrayList<String>();
        if (root != null) {
            traverse(root, String.valueOf(root.val));
        }
        return result;
    }

    public void traverse(TreeNode root, String path) {
        if (root.left == null && root.right == null) {
            result.add(path);
        }
        if (root.left != null) {
            traverse(root.left, path + "->" + root.left.val);
        }
        if (root.right != null) {
            traverse(root.right, path + "->" + root.right.val);
        }
    }
}
