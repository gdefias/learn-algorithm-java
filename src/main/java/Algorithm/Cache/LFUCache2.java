package Algorithm.Cache;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by Defias on 2020/06.
 * Description: LFU Cache


 实现2：
 哈希表 + 平衡二叉树（TreeSet 红黑树）
 */

public class LFUCache2 {

    //结点类
    class Node implements Comparable<Node> {
        int key;
        int value;
        int cnt;   //表示出现的频率 新结点重置为1
        int time;  //表示出现的时间 只增不减

        public Node(int key, int value, int cnt, int time) {
            this.key = key;
            this.value = value;
            this.cnt = cnt;
            this.time = time;
        }

        @Override
        public int compareTo(Node n) {  //重写比较方法
            if(this.cnt == n.cnt) {
                return time - n.time;
            } else {
                return cnt - n.cnt;
            }
        }
    }

    private int capacity;   //容量
    private HashMap<Integer, Node> map;   //哈希表
    private TreeSet<Node> ts;   //平衡二叉树
    private int time;

    public static void main(String[] args) {
        LFUCache2 cache = new LFUCache2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        System.out.println(cache.get(2));       // 返回 -1 (未找到key 2)
        System.out.println(cache.get(3));       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        System.out.println(cache.get(1));       // 返回 -1 (未找到 key 1)
        System.out.println(cache.get(3));       // 返回 3
        System.out.println(cache.get(4));       // 返回 4
    }


    public LFUCache2(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        ts = new TreeSet<>();
        time = 0;
    }

    public int get(int key) {
        if(capacity == 0) {
            return -1;
        }
        Node node = map.get(key);
        //没有该节点
        if(node == null) {
            return -1;
        }
        //存在该节点的话,先删除原来的旧的缓存
        ts.remove(node);
        node.time = ++time;
        node.cnt += 1;
        ts.add(node);
        map.put(key, node);

        return node.value;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        Node node = map.get(key);
        // 该节点是新节点
        if(node == null) {
            if(map.size() == capacity) {//缓存达到容量上限
                Node f = ts.first();
                map.remove(f.key);
                ts.remove(f);

            }
            Node cache = new Node(key, value, 1, ++time);
            map.put(key, cache);
            ts.add(cache);


        } else {//该节点已经存在
            ts.remove(node);
            node.time = ++time;
            node.cnt += 1;
            node.value = value;
            ts.add(node);
            map.put(key, node);
        }
    }
}
