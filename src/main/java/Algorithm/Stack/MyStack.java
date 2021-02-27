package Algorithm.Stack;
import java.util.NoSuchElementException;
import static Lib.Base.ListNode;

/**
 * Created by Defias on 2017/9/15.
 * @author yzh

   栈 后进先出

   链式实现
 */
public class MyStack {
    private ListNode head = null;    //栈顶

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        MyStack stack = mockLinkedStack();
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());

        System.out.println("pop: " + stack.pop());
        System.out.println(stack.peek());

        stack.empty();
        System.out.println(stack.isEmpty());
    }


    //入栈
    public void push(int val) {
        ListNode newHead = new ListNode(val);
        newHead.next = head;
        head = newHead;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int ret = head.val;
        head = head.next;
        return ret;
    }

    //查看栈顶
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.val;
    }

    //是否为空
    public boolean isEmpty() {
        return head == null;
    }

    //清空栈
    public void empty() {
        head = null;
    }


    //制造一个测试栈
    public static MyStack mockLinkedStack() {
        MyStack stack = new MyStack();
        stack.push(100);
        stack.push(10);
        stack.push(112);
        stack.push(23);
        stack.push(32);
        stack.push(9);
        //stack.push(')');
        return stack;
    }

}
