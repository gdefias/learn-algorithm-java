package Algorithm.Stack;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Felix
 * @date: 2022/12/28
 * @description: 用队列实现栈    --- 两个队列实现
 *
 * 当有元素时总是确保一个队列为空，一个队列装元素，除非两个队列都为空
 */
public class QueueStack3 {
    public Queue<Integer> queue1;
    public Queue<Integer> queue2;

    public static void main(String[] args) {
        QueueStack3 stack = new QueueStack3();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.empty());
        System.out.println(stack.top());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.empty());
    }

    public QueueStack3() {
        this.queue1 = new LinkedList<Integer>();
        this.queue2 = new LinkedList<Integer>();
    }

    public void push(int x) {
        if(!this.queue1.isEmpty() || (this.queue1.isEmpty() && this.queue2.isEmpty())) {
            this.queue1.offer(x);
        } else {
            this.queue2.offer(x);
        }
    }

    public int pop() {
        if(this.queue1.isEmpty() && this.queue2.isEmpty()) {
            return -1;
        }
        if(!this.queue1.isEmpty()) {
            while(this.queue1.size() > 1) {
                this.queue2.offer(this.queue1.poll());
            }
            return this.queue1.poll();
        } else {
            while(this.queue2.size() > 1) {
                this.queue1.offer(this.queue2.poll());
            }
            return this.queue2.poll();
        }
    }

    public int top() {
        if(this.queue1.isEmpty() && this.queue2.isEmpty()) {
            return -1;
        }

        int res;
        if(!this.queue1.isEmpty()) {
            while(this.queue1.size() > 1) {
                this.queue2.offer(this.queue1.poll());
            }
            res = this.queue1.poll();
            this.queue2.offer(res);
        } else {
            while(this.queue2.size() > 1) {
                this.queue1.offer(this.queue2.poll());
            }
            res = this.queue2.poll();
            this.queue1.offer(res);
        }
        return res;
    }

    public boolean empty() {
        if(this.queue1.isEmpty() && this.queue2.isEmpty()) {
            return true;
        }
        return false;
    }
}
