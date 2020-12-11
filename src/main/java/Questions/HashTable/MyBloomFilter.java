package Questions.HashTable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
/**
 * Created by Defias on 2020/06.
 * Description: 布隆过滤器
 *
 */

public class MyBloomFilter {
    private static final int DEFAULT_SIZE = 2 << 24;   //布隆过滤器大小
    private static final int[] seeds = new int[] { 5, 7, 11, 13, 31, 37, 61 };    //代表不同的哈希函数
    private BitSet bits = new BitSet(DEFAULT_SIZE);   //使用BitSet实现

    private SimpleHash[] func = new SimpleHash[seeds.length];  //哈希函数组

    public static void main(String[] args) {

        testMyBloomFilter();

    }

    public static void testMyBloomFilter() {
        MyBloomFilter bf = new MyBloomFilter();
        List<String> strs = new ArrayList<String>();
        strs.add("123456");
        strs.add("hello word");
        strs.add("transDocId");
        strs.add("123456");
        strs.add("transDocId");
        strs.add("hello word");
        strs.add("test");

        for (int i=0;i<strs.size();i++) {
            String s = strs.get(i);
            boolean bl = bf.contains(s);
            if(bl){
                System.out.println(i+","+s);
            }else{
                bf.add(s);
            }
        }
    }


    public MyBloomFilter() {
        //初始化哈希函数组
        for (int i=0; i<seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    //新增元素，各哈希函数哈希出来的索引位置置为true
    public void add(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    //检测是否包含指定元素
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    //哈希函数类，不同的种子参数代表不同的哈希函数
    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i=0; i<len; i++) {
                result = seed * result+value.charAt(i);
            }
            return (cap-1) & result;
        }
    }

}
