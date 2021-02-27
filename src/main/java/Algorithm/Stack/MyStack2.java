package Algorithm.Stack;

/**
 * Created by Defias on 2020/07.
 * Description: 栈

  链式实现   泛型
 */
public class MyStack2<T> {

    //泛型结点类
    private static class Node<U> {
        U item;
        Node<U> next;
        Node() {
            item = null;
            next = null;
        }

        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<T>();

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        MyStack2<String> lss = new MyStack2<String>();
        for(String s : "Phasers on stun!".split(" "))
            lss.push(s);
        String s;
        while((s = lss.pop()) != null)
            System.out.println(s);
    }



    public void push(T item) {
        top = new Node<T>(item, top);
    }

    public T pop() {
        T result = top.item;
        if(!top.end())
            top = top.next;
        return result;
    }
}
