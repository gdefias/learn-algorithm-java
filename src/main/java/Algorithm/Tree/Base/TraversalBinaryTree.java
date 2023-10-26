package Algorithm.Tree.Base;
import java.util.*;
import java.util.LinkedList;
import static Lib.Base.TreeNode;
/**
 *
 * @author Defias
 * @date 2017/9/15
 *
 * 二叉树
 *
 * 前序、中序、后序、层序遍历 递归与非递归 莫里斯遍历
 */
public class TraversalBinaryTree {

    public static void main(String[] args) {
        TreeNode root = mockTree();

        //递归遍历
        printTreePreOrder(root);
        System.out.println("\n-----递归前序遍历-----\n");

        PreOrderTraversal(root);
        System.out.println("\n-----非递归前序遍历-----\n");

        Morris_preorderTraversal(root);
        System.out.println("\n-----莫里斯前序遍历-----\n");


        printTreeInOrder(root);
        System.out.println("\n-----递归中序遍历-----\n");

        InOrderTraversal(root);
        System.out.println("\n-----非递归中序遍历-----\n");


        printTreePostOrder(root);
        System.out.println("\n-----递归后序遍历-----\n");

        PostOrderTraversal(root);
        System.out.println("\n-----非递归后序遍历-----\n");

        printTreeLevelOrder(root);
        System.out.println("\n-----层序遍历-----\n");

    }

    //前序遍历 （从根结点开始：根节点 - 左子树 - 右子树）
    public static void printTreePreOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        printTreePreOrder(root.left);
        printTreePreOrder(root.right);
    }

    //中序遍历 （从根结点开始： 左子树 - 根节点 - 右子树）
    public static void printTreeInOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        printTreeInOrder(root.left);
        System.out.print(root.val+" ");
        printTreeInOrder(root.right);
    }

    //后序遍历 （从根结点开始： 左子树 - 右子树 - 根节点）
    public static void printTreePostOrder(TreeNode root) {
        if(root == null) {
            return;
        }

        printTreePostOrder(root.left);
        printTreePostOrder(root.right);
        System.out.print(root.val+" ");
    }

    //层序遍历（借助队列)
    public static void printTreeLevelOrder(TreeNode root) {
        if(root==null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            System.out.print(tmp.val+" ");
            if(tmp.left!=null)
                queue.add(tmp.left);
            if(tmp.right!=null) {
                queue.add(tmp.right);
            }
        }
    }

    //前序遍历-非递归（借助栈)
    public static void PreOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode temp = s.pop();
            System.out.println(temp.val);

            //先向栈中存放右子树，再存放左子树
            if (temp.right != null) {
                s.push(temp.right);
            }
            if (temp.left != null) {
                s.push(temp.left);
            }
        }
    }

    //前序遍历-非递归（借助栈) 写法2
    public static void PreOrderTraversal2(TreeNode root) {
        if(root==null) {
            return;
        }

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode node = root;
        while(node!=null || !s.isEmpty()) {
            while(node!=null) {
                System.out.print(node.val + " ");
                s.push(node);
                node = node.left;
            }

            node = s.pop();
            node = node.right;
        }
    }



    //前序遍历-非递归（借助栈) 写法3
    public static void PreOrderTraversal3(TreeNode root) {
        if(root==null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while(true) {
            while(node!=null) {
                System.out.print(node.val+" ");
                stack.push(node);
                node = node.left;
            }

            if(stack.isEmpty()) {
                break;
            }
            node = (TreeNode)stack.pop();
            node = node.right;
        }
    }

    //莫里斯前序遍历 非递归 无需栈
    public static void Morris_preorderTraversal(TreeNode root) {
        TreeNode cur = root;
        while(cur!=null){
            if(cur.left!=null){
                TreeNode pre = cur.left;
                while(pre.right!=null && pre.right!=cur){
                    pre = pre.right;
                }
                if(pre.right == null){	// 第一次到达左子树的最右端
                    System.out.print(cur.val + " ");
                    pre.right = cur;
                    cur = cur.left;
                } else { // 第二次到达左子树的最右端
                    pre.right = null;
                    cur = cur.right;
                }
            } else{
                System.out.print(cur.val + " ");
                cur = cur.right;
            }
        }
    }


    //中序遍历-非递归（借助栈)
    //相对于前序遍历，需要在左子树遍历完之后，再进行出栈处理，访问结点数据
    public static void InOrderTraversal(TreeNode root) {
        if(root==null) {
            return;
        }

        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode node = root;
        while(node!=null || !s.isEmpty()) {
            while(node!=null) {
                s.push(node);
                node = node.left;
            }

            node = (TreeNode)s.pop();
            System.out.print(node.val + " ");
            node = node.right;
        }

    }

    //中序遍历-非递归（借助栈)  写法2
    public static void InOrderTraversal2(TreeNode root) {
        if(root==null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while(true) {
            while(node!=null) {
                stack.push(node);
                node = node.left;
            }

            if(stack.isEmpty()) {
                break;
            }
            node = (TreeNode)stack.pop();
            System.out.print(node.val+" ");
            node = node.right;
        }

    }

    //后序遍历-非递归
    public static void PostOrderTraversal(TreeNode root) {
        if(root==null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode lastVisit = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            //当前栈顶元素弹栈
            node = stack.pop();

            //如果其右子树也为空，或者右子树已经访问，则可以直接输出当前节点的值
            if (node.right == null || node.right == lastVisit) {
                System.out.print(node.val + " ");
                lastVisit = node;
                node = null;
            } else {
                //否则，放回栈后继续遍历右子树
                stack.push(node);
                node = node.right;
            }
        }

    }

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
    public static TreeNode mockTree() {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(14);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(13);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.right = node6;

        node5.left = node7;
        node5.right = node8;

        node6.left = node9;
        return node1;
    }
}