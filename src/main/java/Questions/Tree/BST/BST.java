package Questions.Tree.BST;
import static Lib.Base.*;
/**
 * @author Defias
 * @date 2017/9/15
 *
 * 二叉查找树BST
 */
public class BST {
    public TreeNode root;   //树根

    public static void main(String[] args) {
        BST bst = mockBST();
        BST bst2 = mockBST();

        bst.printBST(bst.root);
        System.out.println("\n------");
        System.out.println(bst.contains(13));
    }


    //是否包含指定值的结点
    public boolean contains(int target) {
        return containsHelper(root, target);
    }

    //递归查找
    private boolean containsHelper(TreeNode root, int target) {
        if (root == null) {
            return false;
        }

        if (target == root.val) {
            return true;
        } else if (target < root.val) {
            return containsHelper(root.left, target);
        } else {
            return containsHelper(root.right, target);
        }
    }

    //新增结点
    public void add(int val) {
        root = addHelper(root, val);  //递归添加
    }

    private TreeNode addHelper(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = addHelper(root.left, val);
        } else {
            root.right = addHelper(root.right, val);
        }
        return root;
    }

    //中序遍历打印
    public void printBST() {
        printBST(root);
    }

    private void printBST(TreeNode root) {
        if (root == null) {
            return;
        }
        printBST(root.left);
        System.out.print(root.val + " ");  //中序
        printBST(root.right);
    }

    /*制造一棵测试二叉树查找树
                        8
                     /     \
                    3       10
                  /  \        \
                 1    6        14
                     / \       /
                    4   7    13
    **/
    public static BST mockBST() {
        BST bst = new BST();
        bst.add(8);
        bst.add(3);
        bst.add(10);
        bst.add(1);
        bst.add(6);
        bst.add(14);
        bst.add(4);
        bst.add(7);
        bst.add(13);
        return bst;
    }


}

