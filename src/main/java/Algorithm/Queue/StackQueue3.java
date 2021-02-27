package Algorithm.Queue;
import java.util.Stack;
/**
 * Created by Defias on 2017/10/7.

  用两个栈实现队列
 */

public class StackQueue3 {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public static void main(String[] args) {
        StackQueue3 myQueue = new StackQueue3();
        myQueue.push(1);
        System.out.println(myQueue.pop());
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.top());
        System.out.println(myQueue.pop());

    }

    public StackQueue3() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    private void stack2ToStack1() {
        while (! stack2.empty()) {
            stack1.push(stack2.pop());
        }
    }

    public void push(int number) {
        stack2.push(number);
    }

    public int pop() {
        if (stack1.empty() == true) {
            this.stack2ToStack1();
        }
        return stack1.pop();
    }

    public int top() {
        if (stack1.empty() == true) {
            this.stack2ToStack1();
        }
        return stack1.peek();
    }
}
