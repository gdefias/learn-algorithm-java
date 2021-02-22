package Questions.Tree.Treap;
import static Lib.Base.TreapNode;
/**
 * Created by Defias on 2020/06.
 * Description: 树堆
 */


public class Treap {
    public TreapNode root=null;  //Treap的根节点

    public static void main(String[] args) {
        Treap treap = mockTreap();
        printTreapInOrder(treap.root);
        System.out.println("\n--------");
        printTreapPreOrder(treap.root);
        System.out.println("\n--------");

//        TreapNode node = treap.search(3);
//        printTreapNode(node);
//        System.out.println("--------");

        treap.remove(3);
        printTreapInOrder(treap.root);
        System.out.println("\n--------");
        printTreapPreOrder(treap.root);
        System.out.println("\n--------");

    }

    //制造一个Treap
    public static Treap mockTreap() {
        Treap treap = new Treap();
        treap.insert(8, 1);
        treap.insert(3, 30);
        treap.insert(10, 23);
        treap.insert(3,111);
//        treap.insert(6,1);
//        treap.insert(14,23);
//        treap.insert(4,43);
//        treap.insert(7,89);
//        treap.insert(67, 67);
        return treap;
    }

    //Treap中查找指定关键字的结点，如果没有找到返回null
    public TreapNode search(int key){
        TreapNode p = this.root;   //从树根开始
        while(p!=null){
            if(p.key == key) {
                break;
            } else if(p.key < key){
                p = p.right;
            } else{
                p = p.left;
            }
        }
        return p;
    }


    //Treap中插入指定关键字key和优先级priotity的结点
    public void insert(int key, int... priotity) {
        TreapNode node;
        if(priotity.length != 0) {
            node = new TreapNode(key, priotity[0]);
        } else {
            node = new TreapNode(key);
        }

        if(this.root == null) {
            this.root = node;
            return;
        }
        this.root = insert(this.root, node);
    }

    //指定根结点的Treap中插入指定结点，返回插入完成后的根
    public TreapNode insert(TreapNode root, TreapNode node) {
        if(root == null) {
            throw new NullPointerException();
        }
        if(root == null) {
            return root;
        }

        if(node.key < root.key){
            if(root.left == null) {
                root.left = node;

                //根的左儿子优先级比根高就右旋
                if(root.priority > root.left.priority) {
                    root = rotateRight(root);
                }
            } else {
                root.left = insert(root.left, node);
            }

        } else {
            if(root.right == null) {
                root.right = node;

                //根的右儿子优先级比根高就左旋
                if(root.priority > root.right.priority) {
                    root = rotateLeft(root);
                }
            } else {
                root.right = insert(root.right, node);
            }
        }
        return root;
    }




    //对指定结点进行左旋操作
    public TreapNode rotateLeft(TreapNode node) {
        TreapNode root = node.right;
        node.right = root.left;
        root.left = node;
        return root;
    }

    //对指定结点进行右旋操作
    public TreapNode rotateRight(TreapNode node) {
        TreapNode root = node.left;
        node.left = root.right;
        root.right = node;
        return root;
    }


    //Treap中删除指定关键字的结点，有多个时任意删任意一个
    public void remove(int key) {
        if(root == null){
            return;
        }
        this.root = remove(this.root, key);
    }


    //指定根结点的Treap中删除关键字的结点
    public TreapNode remove(TreapNode root, int key) {
        if(root == null){
            throw new NullPointerException();
        }

        if (key < root.key && root.left!=null) {
            root.left = remove(root.left, key);  //在左子树中删
        }

        else if(key > root.key && root.right!=null) {
            root.right = remove(root.right, key);  //在右子树中删
        }

        else if(key == root.key) {   //删根
            if(root.left==null && root.right==null) {
                root = null;
            } else if(root.left==null && root.right!=null) {
                root = root.right;
            } else if(root.right==null && root.left!=null) {
                root = root.left;
            } else {  //左右子树都不为空需要旋转操作
                if(root.left.priority < root.right.priority) {  //左儿子优先级比右儿子高就右旋
                    root = rotateRight(root);
                } else {
                    root = rotateLeft(root);
                }
            }
        }

        return root;
    }

    //先序遍历打印Treap
    public static void printTreapPreOrder(TreapNode root) {
        if(root == null) {
            return;
        }
        System.out.print(root.key + "(" + root.priority + ") ");
        printTreapPreOrder(root.left);
        printTreapPreOrder(root.right);
    }

    //中序遍历打印Treap
    public static void printTreapInOrder(TreapNode root) {
        if(root == null) {
            return;
        }
        printTreapInOrder(root.left);
        System.out.print(root.key + "(" + root.priority + ") ");
        printTreapInOrder(root.right);
    }

    //打印TreapNode
    public static void printTreapNode(TreapNode node) {
        if(node == null) {
            return;
        }
        System.out.println(node.key + "(" + node.priority + ")");
    }
}