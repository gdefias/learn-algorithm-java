package Algorithm.Tree.RedBlackTree;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-10
 */

public class RBNode<T extends Comparable<T>>{
    boolean color; //颜色
    final boolean RED = true;
    final boolean BLACK = false;
    T key; //关键字(键值)
    RBNode<T> left; //左子节点
    RBNode<T> right; //右子节点
    RBNode<T> parent; //父节点

    public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
        this.key = key;
        this.color = color;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public T getKey() {
        return key;
    }

    public String toString() {
        return "" + key + (this.color == RED ? "R" : "B");
    }
}
