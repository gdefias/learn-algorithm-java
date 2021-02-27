package Algorithm.Queue;
import java.util.*;
/**
 * Created by Jeff on 2016/5/1.

 用两个栈实现队列

 https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/

 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的
 功能。(若队列中没有元素，deleteHead 操作返回 -1 )

 输入：
 ["CQueue","appendTail","deleteHead","deleteHead"]
 [[],[3],[],[]]
 输出：[null,null,3,-1]

 输入：
 ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 [[],[],[5],[2],[],[]]
 输出：[null,-1,null,null,5,2]
 */

public class StackQueue {
    public Stack<Integer> stack1;
    public Stack<Integer> stack2;

    public StackQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if(stack2.empty()) {
            if(stack1.empty()) {
                return -1;
            }
            while(!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

}