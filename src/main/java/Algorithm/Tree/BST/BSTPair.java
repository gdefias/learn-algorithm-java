package Algorithm.Tree.BST;
import static Lib.Base.*;
/**
 * Created by Defias on 2017/9/16.

  键值对二叉查找树
 */
public class BSTPair {
    private PairTreeNode root;   //根结点

    public static void main(String[] args) {
        BSTPair pairbst = mockPairBST();

        //打印该树
        pairbst.print();

        System.out.println("habhc为key的值是: " + pairbst.get("habhc"));

        System.out.println(pairbst.contains("abc"));
    }


    //新增结点   O(lgn)
    public void insert(String key, int val) {
        root = insertHelper(root, new Pair(key, val));
    }

    private PairTreeNode insertHelper(PairTreeNode root, Pair pair) {
        if (root == null) {
            return new PairTreeNode(pair);
        }

        //key字符串字典序比较
        if (pair.key.compareTo(root.pair.key) < 0) {
            root.left = insertHelper(root.left, pair);
        } else {
            root.right = insertHelper(root.right, pair);
        }
        return root;
    }

    //是否包含指定的key  O(lgn)
    public boolean contains(String key) {
        return containsHelper(root, key);
    }

    private boolean containsHelper(PairTreeNode root, String key) {
        if (root == null) {
            return false;
        }

        if (key.equals(root.pair.key)) {
            return true;
        } else if (key.compareTo(root.pair.key) < 0) {
            return containsHelper(root.left, key);
        } else {
            return containsHelper(root.right, key);
        }
    }

    //获取指定的key对应的值  O(lgn)
    public int get(String key) {
        return getHelper(root, key);
    }

    private int getHelper(PairTreeNode root, String key) {
        if(root == null) {
            return Integer.MIN_VALUE;
        }

        if(key.equals(root.pair.key)) {
            return root.pair.val;
        } else if(key.compareTo(root.pair.key) < 0) {
            return getHelper(root.left, key);
        } else {
            return getHelper(root.right, key);
        }
    }

    //中序遍历打印值
    public void print() {
        printHelper(root);
        System.out.println();
    }

    private void printHelper(PairTreeNode root) {
        if (root == null) {
            return;
        }
        printHelper(root.left);
        System.out.print(root.pair.key + "->" + root.pair.val + "  ");   //中序
        printHelper(root.right);
    }

    //制造一棵测试键值对二叉树查找树
    public static BSTPair mockPairBST() {
        BSTPair pairbst = new BSTPair();
        pairbst.insert("abc", 1);
        pairbst.insert("def", 2);
        pairbst.insert("ghi", 3);
        pairbst.insert("asdbc", 123);
        pairbst.insert("iabdjc", 912);
        pairbst.insert("habhc", 341);
        pairbst.insert("olabc", 451);
        pairbst.insert("vabbnc", 417);
        return pairbst;
    }
}
