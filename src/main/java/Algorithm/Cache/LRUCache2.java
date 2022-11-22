package Algorithm.Cache;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Defias on 2020/06.
 * Description: LRU Cache

 实现：
 使用LinkedHashMap的accessOrder模式
 */

public class LRUCache2 extends LinkedHashMap<Integer, Integer> {
    private int capacity;   //缓存容量


    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2( 2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }


    public LRUCache2(int capacity) {

        //调用父类中的构造方法创建一个LinkedHashMap，设置其容量为capacity，负载因子loadFactor为0.75，并开启accessOrder为true
        //当accessOrder为false时（默认情况），linkedHashMap只会按插入顺序维护双向链表。而开启了accessOrder之后，linkedHashMap就会把每一次对结点的访问也作为标准来进行排序
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        //若key存在,返回对应value值;若key不存在,返回-1
        return super.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        super.put(key,value);
    }

    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest){
        //若返回的结果为true，则执行removeEntryForKey方法删除eldest entry
        return size() > capacity;
    }
}
