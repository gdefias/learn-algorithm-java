package Questions.Cache;
import Questions.LinkList.DLinkedList;
import static Questions.LinkList.DLinkedList.DListNode;
import java.util.HashMap;
/**
 * Created by Defias on 2020/06.
 * Description: LFU 缓存   LFU Cache

 https://leetcode-cn.com/problems/lfu-cache/

 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构

 实现 LFUCache 类：
 LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
 void put(int key, int value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最
 不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。

 注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。

 进阶：
 你是否可以在 O(1) 时间复杂度内执行两项操作？

 输入：
 ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 输出：
 [null, null, null, 1, null, -1, 3, null, -1, 3, 4]

 解释：
 LFUCache lFUCache = new LFUCache(2);
 lFUCache.put(1, 1);
 lFUCache.put(2, 2);
 lFUCache.get(1);      // 返回 1
 lFUCache.put(3, 3);   // 去除键 2
 lFUCache.get(2);      // 返回 -1（未找到）
 lFUCache.get(3);      // 返回 3
 lFUCache.put(4, 4);   // 去除键 1
 lFUCache.get(1);      // 返回 -1（未找到）
 lFUCache.get(3);      // 返回 3
 lFUCache.get(4);      // 返回 4

 0 <= capacity, key, value <= 104
 最多调用 105 次 get 和 put 方法


 实现1：
 两个哈希表 + N个双向链表
 */
public class LFUCache {
    private int capacity;   //LFU Cache容量
    private HashMap<Integer, DListNode> keycache;
    private HashMap<Integer, DLinkedList> freqcache;
    private int minFreq;  //记录最小频率，在LFU缓存满的时候，可以快速定位到最小频率的链表，以达到 O(1) 时间复杂度删除一个元素


    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
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


    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keycache = new HashMap<>();
        this.freqcache = new HashMap<>();
        this.minFreq = 0;
    }


    public int get(int key) {
        DListNode node;
        if((node = keycache.get(key)) == null) {
            return -1;
        }

        updateNodeCnt(node);

        return node.val;

    }

    //更新结点频率
    public void updateNodeCnt(DListNode node) {
        DLinkedList nodelist;
        if(node == null || ((nodelist=freqcache.get(node.cnt)) == null)) {
            return;
        }

        int oldcnt = node.cnt;
        int newcnt = ++node.cnt;  //更新结点频率
        DLinkedList newlist;
        if((newlist = freqcache.get(newcnt)) == null) {  //频率加1的双向链表
            newlist = new DLinkedList();
            freqcache.put(newcnt, newlist);
        }

        nodelist.deleteNode(node);  //删除原频率链表中的该结点

        newlist.addHeadNode(node);  //添加到新链表的表头

        if(nodelist.size == 0) {
            freqcache.remove(oldcnt);  //原频率链表删到为空了就从频率哈希表中删除该链表的频率key
        }

        if(!freqcache.containsKey(minFreq)) {   //频率哈希表中频率最小的被删除了，更新当前最小频率
            minFreq++;
        }

    }

    public void put(int key, int value) {
        DListNode node;
        if((node = keycache.get(key)) != null) {  //已有该结点
            node.val = value; //更新值
            updateNodeCnt(node);

        } else {  //无该结点
            if(capacity == 0) {  //容量限额为0的特殊情况
                return;
            }

            if(keycache.size() == capacity) {   //容量超限了
                DLinkedList minFreqList = freqcache.get(minFreq);  //最小频率的链表
                DListNode minNode = minFreqList.tail.prev;  //最小频率结点
                keycache.remove(minNode.key);  //从哈希表1中删除该结点key

                minFreqList.deleteTailNode();  //删除最小频率结点
                if(minFreqList.size == 0) {  //频率链表删到为空了就从频率哈希表中删除该链表的频率key
                    freqcache.remove(minNode.cnt);
                }
            }
            node = new DListNode(key, value);
            this.minFreq = ++node.cnt;

            keycache.put(node.key, node);

            DLinkedList nodelist;
            if((nodelist=freqcache.get(minFreq)) == null) {
                nodelist = new DLinkedList();
                freqcache.put(minFreq, nodelist);
            }
            nodelist.addHeadNode(node);
        }

    }

}
