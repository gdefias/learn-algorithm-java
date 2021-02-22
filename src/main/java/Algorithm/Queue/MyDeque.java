package Questions.Queue;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 双端队列    ---LinkedList实现（LinkedList本身就是个双端队列）
 */

public class MyDeque<T> {
    private LinkedList<T> deque = new LinkedList<T>();

    public void addFirst(T e) {
        deque.addFirst(e);
    }

    public void addLast(T e) {
        deque.addLast(e);
    }

    public T getFirst() {
        return deque.getFirst();
    }

    public T getLast() {
        return deque.getLast();
    }

    public T removeFirst() {
        return deque.removeFirst();
    }

    public T removeLast() {
        return deque.removeLast();
    }

    public int size() {
        return deque.size();
    }

    public String toString() {
        return deque.toString();
    }
}
