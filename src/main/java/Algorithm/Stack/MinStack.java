package Algorithm.Stack;

import java.util.ArrayList;

/**
 * Created by Defias on 2020/07.
 * Description: 包含min函数的栈  ---ArrayList实现

 https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/

 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数，调用 min、push 及 pop 的时间复杂度都是 O(1)

 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.min();   --> 返回 -3.
 minStack.pop();
 minStack.top();      --> 返回 0.
 minStack.min();   --> 返回 -2.

 函数的调用总次数不超过 20000 次

 ["MinStack","push","push","min","min","push","min","min","top","min","pop","push","push","min","push","pop","top","min","pop"]
 [[],[-10],[14],[],[],[-20],[],[],[],[],[],[10],[-7],[],[-7],[],[],[],[]]

 [-10,-10,-20,-20,-20,-20,-10,-7,-10]


 ["MinStack","push","push","push","top","pop","min","pop","min","pop","push","top","min","push","top","min","pop","min"]
 [[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]

 [2147483647,2147483646,2147483646,2147483647,2147483647,-2147483648,-2147483648,2147483647]
 */
public class MinStack {
    public ArrayList<Integer> stack;     //作为栈
    public ArrayList<Integer> minstack;   //辅助栈


    public static void main(String[] args) {
        test();
    }

    public static void test() {
        MinStack O = new MinStack();
        O.push(-10);
        O.push(14);
        System.out.println(O.min());
        System.out.println(O.min());
        O.push(-20);
        System.out.println(O.min());
        System.out.println(O.min());
        System.out.println(O.top());
        System.out.println(O.min());
        O.pop();
        O.push(10);
        O.push(-7);
        System.out.println(O.min());
        O.push(-7);
        O.pop();
        System.out.println(O.top());
        System.out.println(O.min());
        O.pop();
    }

    public static void test2() {
        MinStack O = new MinStack();
        O.push(2147483646);
        O.push(2147483646);
        O.push(2147483647);

        System.out.println(O.top());
        O.pop();
        System.out.println(O.min());
        O.pop();
        System.out.println(O.min());
        O.pop();
        O.push(2147483647);
        System.out.println(O.top());
        System.out.println(O.min());
        O.push(-2147483648);
        System.out.println(O.top());
        System.out.println(O.min());
        O.pop();
        System.out.println(O.min());
    }


    public MinStack() {
        stack = new ArrayList<Integer>();
        minstack = new ArrayList<Integer>();
    }

    public void push(int x) {
        stack.add(x);  //加入到ArrayList的末尾

        //更新最小值
        if(minstack.size()==0) {
            minstack.add(x);
            return;
        }
        int vmin = min();
        if(x < vmin) {
            vmin = x;
        }
        minstack.add(vmin);    //minstack与stack一一对应
    }

    public void pop() {
        stack.remove(stack.size()-1);    //从ArrayList的末尾移除
        minstack.remove(minstack.size()-1);
    }

    public int top() {
        return stack.get(stack.size()-1);
    }

    public int min() {
        return minstack.get(minstack.size()-1);
    }
}
