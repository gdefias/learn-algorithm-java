package Algorithm.Set;

/**
 * Created by Defias on 2017/9/12.
 *
 * 符号表
 * 顺序查找， 基于无序链表实现
 */

public class SequentialSearchST<Key extends Comparable<Key>, Value> {
    public class Node {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Node first;
    public int n;

    public void put(Key key, Value val) {
        for(Node x=first; x!=null; x=x.next) {
            if(x.key==key) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }


    public Value get(Key key) {
        for(Node x=first; x!=null; x=x.next) {
            if(x.key==key) {
                return x.val;
            }
        }
        return null;
    }

    public void delete(Key key) {
        if(key==null) {
            return;
        }
        first = delete(first, key);
    }

    public Node delete(Node x, Key key) {
        if(x==null) {
            return null;
        }
        if(x.key==key) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }


}
