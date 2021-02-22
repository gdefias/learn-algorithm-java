package Questions.Stack;
import java.util.Stack;

/**
 * Created by Jeff on 2016/5/1.

  包含min函数的栈  ---java本身的Stack实现

 */

public class MinStack2 {
    private Stack<Integer> stack;    //作为栈
    private Stack<Integer> minStack;    //辅助栈

    public static void main(String[] args) {
        MinStack2 mystack = new MinStack2();
        mystack.push(1);
        System.out.println(mystack.pop());
        mystack.push(2);
        mystack.push(3);
        System.out.println(mystack.min());
        mystack.push(1);
        System.out.println(mystack.min());
    }

    public MinStack2() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int number) {
        stack.push(number);
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {
            minStack.push(Math.min(number, minStack.peek()));
        }
    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    public int min() {
        return minStack.peek();
    }
}