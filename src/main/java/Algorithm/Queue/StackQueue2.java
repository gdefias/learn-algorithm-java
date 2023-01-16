package Algorithm.Queue;
import java.util.Stack;
/**
 * Created by Defias on 2017/10/15.

 用两个栈实现队列

 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 (若队列中没有元素，deleteHead操作返回 -1 )

 */

public class StackQueue2 {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    //向stack1添加结点
    public void appendTail(int node) {
        if(stack1.size()>0 && stack2.size()==0) {
            while(stack1.size()>0) {
                stack2.push(stack1.pop());
            }
        }
        stack1.push(node);
    }

    //从stack2取结点
    public int deleteHead() {
        if(stack2.size()==0 && stack1.size()>0) {
            while(stack1.size()>0) {
                stack2.push(stack1.pop());
            }
        }

        if(stack2.size()==0 && stack1.size()==0)
            return -1;
        return stack2.pop();
    }
}
