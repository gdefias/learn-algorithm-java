package Algorithm.Queue;
import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Created by Defias on 2020/07.
 * Description: 队列的最大值

 https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/

 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 若队列为空，pop_front 和 max_value需要返回 -1

 输入:
 ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 [[],[1],[2],[],[],[]]

 输出:
 [null,null,null,2,1,2]

 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 1 <= value <= 10^5
 */

public class MaxQueue {
    Queue<Integer> queue;  //使用一个队列存push和pop的原始数据
    Deque<Integer> deque;  //使用一个双端队列维护queue的最大值  从大到小的单调队列

    public MaxQueue() {
        this.queue = new LinkedList<>();
        this.deque = new LinkedList<>();
    }

    public int max_value() {
        if(deque.isEmpty()) {
            return -1;
        }
        return deque.getFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while(!deque.isEmpty() && value>=deque.getLast()) {
            deque.removeLast();
        }

        deque.addLast(value);
    }

    public int pop_front() {
        if(queue.isEmpty()) {
            return -1;
        }
        int res = queue.poll();

        if(res==deque.getFirst()) {
            deque.removeFirst();
        }
        return res;
    }
}
