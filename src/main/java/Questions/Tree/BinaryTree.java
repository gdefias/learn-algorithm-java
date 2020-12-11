package Questions.Tree;
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
 * 前序、中序、后序、层序遍历 递归与非递归
 */
public class BinaryTree {

    public static void main(String[] args) {
        //BinaryTree BinaryTreeO = new BinaryTree();
        TreeNode root = mockTree();

        //递归遍历
        printTreePreOrder(root);
        System.out.println("\n-----递归前序遍历-----\n");

        printTreeInOrder(root);
        System.out.println("\n-----递归中序遍历-----\n");

        printTreePostOrder(root);
        System.out.println("\n-----递归后序遍历-----\n");

        //层序遍历
        printTreeLevelOrder(root);
        System.out.println("\n-----层序遍历-----\n");

        //非递归遍历
        PreOrderTraversal(root);
        System.out.println("\n-----非递归前序遍历-----\n");

        InOrderTraversal(root);
        System.out.println("\n-----非递归中序遍历-----\n");

        LeafSumGetter leafSumGetter = BinaryTree.getLeafSumGetter();
        int leafSum = leafSumGetter.get(root);
        System.out.print(leafSum);
        System.out.println("\n-----叶子结点的和-----\n");

        System.out.println(BinaryTree.TreeHightGetter(root));
        System.out.println(BinaryTree.TreeHightGetter2(root));
        System.out.println(BinaryTree.TreeHightGetter3(root));
        System.out.println("-----树的高度-----\n");

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

    //前序遍历-非递归（借助栈) 写法2
    public static void PreOrderTraversal2(TreeNode root) {
        if (root == null){
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

    //中序遍历-非递归（借助栈 与前序遍历类似)
    //相对于前序遍历，需要在左子树遍历完之后，再进行出栈处理，访问结点数据
    public static void InOrderTraversal(TreeNode root) {
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
        TreeNode lastVisit = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            //查看当前栈顶元素
            node = stack.peek();

            //如果其右子树也为空，或者右子树已经访问，则可以直接输出当前节点的值
            if (node.right == null || node.right == lastVisit) {
                System.out.println(node.val);
                stack.pop();
                lastVisit = node;
                node = null;
            } else {
                //否则，继续遍历右子树
                node = node.right;
            }
        }

    }

    //求叶子节点的和
    public static LeafSumGetter getLeafSumGetter() {
        return new LeafSumGetter();
    }

    public static class LeafSumGetter {
        public int sum;
        public int get(TreeNode root) {
            sum = 0;
            traverse(root);
            return sum;
        }

        public void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                sum += root.val;
            }
            traverse(root.left);
            traverse(root.right);
        }
    }


    //计算给定二叉树中叶节点的数量
    public static GetLeafCounts gtLeafCounts() {
        return new GetLeafCounts();
    }

    public static class GetLeafCounts {
        public int count = 0;

        public int get(TreeNode root) {
            if(root==null) {
                return count;
            }

            if(root.left==null && root.right==null) {
                count++;
            }

            if(root.left!=null) {
                get(root.left);
            }

            if(root.right!=null) {
                get(root.right);
            }
            return count;
        }
    }




    //方法1 本质：DFS、先序遍历
    public static int TreeHightGetter(TreeNode root) {
        return GetTreeHightGetter.get(root);
    }
    

    public static class GetTreeHightGetter {
        public static int maxDepth;

        public static int get(TreeNode root) {
            maxDepth = 0;
            traverse(root, 1);
            return maxDepth;
        }

        public static void traverse(TreeNode root, int depth) {
            if (root == null) {
                return;
            }

            maxDepth = Math.max(depth, maxDepth);
            traverse(root.left, depth+1);
            traverse(root.right, depth+1);
        }
    }

    //方法2  本质：DFS、后序遍历
    public static int TreeHightGetter2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(TreeHightGetter2(root.left), TreeHightGetter2(root.right));
    }

    //方法3  本质：BFS、层序遍历
    public static int TreeHightGetter3(TreeNode root) {
        if(root == null)
            return 0;
        List<TreeNode> queue = new LinkedList<TreeNode>() {{
            add(root);
        }};
        int res = 0;

        while(!queue.isEmpty()) {
            res++;  //每访问一层深度加1

            List<TreeNode> qtmp = new LinkedList<>();  //存放下一层的所有结点
            for(TreeNode node : queue) { //倒出当前层的所有结点
                if(node.left != null)
                    qtmp.add(node.left);
                if(node.right != null)
                    qtmp.add(node.right);
            }

            queue = qtmp;  //对下一层进行循环迭代

        }
        return res;
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