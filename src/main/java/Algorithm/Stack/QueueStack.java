package Questions.Stack;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Defias
 * @date: 2020/12/4
 * @description: 用队列实现栈    --- 两个队列实现

  使用队列实现栈的下列操作：
  push(x) -- 元素 x 入栈
  pop() -- 移除栈顶元素
  top() -- 获取栈顶元素
  empty() -- 返回栈是否为空
 */

public class QueueStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public static void main(String[] args) {
        QueueStack stack = new QueueStack();
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

    public QueueStack() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }

    //主队列中留一个元素，其余元素放入辅助队列中
    private void moveItems() {
        while (queue1.size() != 1) {
            queue2.offer(queue1.poll());
        }
    }

    //交换两个队列的引用
    private void swapQueues() {
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public void push(int value) {
        queue1.offer(value);
    }

    public int top() {
        moveItems();
        int item = queue1.poll();
        swapQueues();
        queue1.offer(item);
        return item;
    }

    public int pop() {
        moveItems();
        int item = queue1.poll();
        swapQueues();
        return item;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
