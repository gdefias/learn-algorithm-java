package Algorithm.Stack;

import java.util.LinkedList;

/**
 * Created by Defias on 2020/07.
 * Description: 栈    ----用LinkedList实现 （LinkedList本身就是个栈）
 */

public class MyStack3<T> {
    private LinkedList<T> storage = new LinkedList<T>();

    public static void main(String[] args) {
        MyStack3<Integer> mystack = new MyStack3();
        mystack.push(1);
        mystack.pop();
        mystack.push(2);
        System.out.println(mystack.peek());
        mystack.pop();
        System.out.println(mystack.empty());
        mystack.push(3);
        System.out.println(mystack.empty());
    }

    public void push(T v) {
        storage.addFirst(v);
    }

    public T peek() {
        return storage.getFirst();
    }

    public T pop() {
        return storage.removeFirst();
    }

    public boolean empty() {
        return storage.isEmpty();
    }

    public String toString() {
        return storage.toString();
    }
}
