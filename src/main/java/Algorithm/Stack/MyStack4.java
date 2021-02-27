package Algorithm.Stack;
import java.util.*;

/**
 * Created by Jeff on 2016/5/1.

  栈    ----用ArrayList实现
 */

class MyStack4 {
    private List<Integer> array = new ArrayList<Integer>();

    public static void main(String[] args) {
        MyStack4 mystack = new MyStack4();
        mystack.push(1);
        mystack.pop();
        mystack.push(2);
        System.out.println(mystack.top());
        mystack.pop();
        System.out.println(mystack.isEmpty());
        mystack.push(3);
        System.out.println(mystack.isEmpty());
    }

    public void push(int x) {
        array.add(x);
    }

    public void pop() {
        int n = array.size();
        if (n > 0)
            array.remove(n-1);
        return;
    }

    public int top() {
        int n = array.size();
        return array.get(n-1);
    }

    public boolean isEmpty() {
        return array.size() == 0;
    }

}