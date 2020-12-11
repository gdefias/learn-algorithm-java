package Questions.Cache;

/**
 * Created by Defias on 2020/06.
 * Description: LRU 缓存机制  LRU Cache

 https://leetcode-cn.com/problems/lru-cache-lcci/
 https://leetcode-cn.com/problems/lru-cache/

 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。

 实现 LRUCache 类：
 LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，
 它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间

 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？

 输入
 ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 输出
 [null, null, null, 1, null, -1, null, -1, 3, 4]

 解释
 LRUCache lRUCache = new LRUCache(2);
 lRUCache.put(1, 1); // 缓存是 {1=1}
 lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 lRUCache.get(1);    // 返回 1
 lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 lRUCache.get(2);    // 返回 -1 (未找到)
 lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 lRUCache.get(1);    // 返回 -1 (未找到)
 lRUCache.get(3);    // 返回 3
 lRUCache.get(4);    // 返回 4


 实现：
 双向链表 + 哈希表
 */

import Questions.LinkList.DLinkedList;
import static Questions.LinkList.DLinkedList.DListNode;
import java.util.HashMap;

public class LRUCache {
    private int capacity;   //缓存容量限额
    private HashMap<Integer, DListNode> cache;
    private DLinkedList dlinklist;


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



    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dlinklist = new DLinkedList();
    }

    public int get(int key) {
        DListNode node;
        if((node = cache.get(key)) == null) {
            return -1;
        }
        dlinklist.moveNodeToHand(node);
        return node.val;

    }

    public void put(int key, int value) {
        DListNode node;
        if((node = cache.get(key)) != null) {
            node.val = value;            //更新值
            dlinklist.moveNodeToHand(node);   //移到链表头
        } else {
            DListNode newnode = new DListNode(key, value);
            if(cache.size() >= capacity) {
                DListNode delnode = dlinklist.deleteTailNode();  //删除双向链表尾结点
                cache.remove(delnode.key);  //同时从hashmap中删除对应的key
            }
            cache.put(key, newnode);   //hashmap中加入新结点
            dlinklist.addHeadNode(newnode);  //同时链表中链表头也添加新结点
        }
    }

}
