package Questions.Stack;
import java.util.LinkedList;
import java.util.Queue;
/**
 * @author: Defias
 * @date: 2020/12/4
 * @description:   用队列实现栈    --- 一个队列实现

  使用队列实现栈的下列操作：
  push(x) -- 元素 x 入栈
  pop() -- 移除栈顶元素
  top() -- 获取栈顶元素
  empty() -- 返回栈是否为空
 */
public class QueueStack2 {
    Queue<Integer> queue;

    public static void main(String[] args) {
        QueueStack2 stack = new QueueStack2();
        stack.push(1);
        stack.pop();
        stack.push(2);
        stack.push(3);
        System.out.println(stack.empty());
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
    }


    public QueueStack2() {
        queue = new LinkedList<Integer>();
    }

    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        for(int i=0; i<size; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return  queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
