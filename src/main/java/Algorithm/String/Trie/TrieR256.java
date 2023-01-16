package Algorithm.String.Trie;
import Lib.StdOut;
import Algorithm.Queue.MyQueue2;
import java.util.Iterator;
/**
 * Created by Defias on 2020/06.
 * Description:  单词查找树（单词查找树）
 */

public class TrieR256 implements Iterable<String> {
    private static final int R = 256;        // extended ASCII

    private Node root;      // root of trie
    private int n;          // number of keys in trie

    // R-way trie node
    private static class Node {
        private Node[] next = new Node[R];     //以字符的ascii值为索引
        private boolean isString;   //单词结束标志
    }

    public static void main(String[] args) {
        TrieR256 set = new TrieR256();

        set.add("qwe");
        set.add("sea");
        set.add("by");
        set.add("sells");
        set.add("shells");
        set.add("the");

        // print results
        if (set.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : set) {
                StdOut.println(key);
            }
            StdOut.println();
        }

        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(set.longestPrefixOf("shellsort"));
        StdOut.println();


        StdOut.println("keysWithPrefix(\"sh\"):");
        for (String s : set.keysWithPrefix("sh"))
            StdOut.println(s);
        StdOut.println();


        StdOut.println("keysThatMatch(\".he.l.\"):");
        for (String s : set.keysThatMatch(".he.l."))
            StdOut.println(s);
    }


    public TrieR256() {
    }


    //集合中是否包含key
    public boolean contains(String key) {
        Node x = get(root, key, 0);
        if (x == null)
            return false;
        return x.isString;
    }

    private Node get(Node x, String key, int d) {
        if (x == null)
            return null;
        if (d == key.length())
            return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }


    //添加key到集合中
    public void add(String key) {
        root = add(root, key, 0);  //从key字符串的索引0位置开始存
    }

    private Node add(Node x, String key, int d) {
        if (x == null)
            x = new Node();

        if (d == key.length()) {  //存完一个单词key
            if (!x.isString)
                n++;     //原来没存这个单词，那单词的数量就要加1
            x.isString = true;  //标识单词结束
        } else {
            char c = key.charAt(d);  //取字符作为next数组的索引
            x.next[c] = add(x.next[c], key, d+1);  //递归的存下一个字符
        }
        return x;
    }


    //集合中key的数量
    public int size() {
        return n;
    }

    //集合是否为空
    public boolean isEmpty() {
        return size() == 0;
    }



    //获取集合中所有key的迭代器
    public Iterator<String> iterator() {
        return keysWithPrefix("").iterator();
    }


    //所有以prefix为前缀的key
    public Iterable<String> keysWithPrefix(String prefix) {
        MyQueue2<String> results = new MyQueue2<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, MyQueue2<String> results) {
        if (x == null) return;
        if (x.isString) results.enqueue(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }


    //所有和pattern匹配的key，其中.匹配任意字符
    public Iterable<String> keysThatMatch(String pattern) {
        MyQueue2<String> results = new MyQueue2<String>();
        StringBuilder prefix = new StringBuilder();
        collect(root, prefix, pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, MyQueue2<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.isString)
            results.enqueue(prefix.toString());
        if (d == pattern.length())
            return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }


    //query的前缀中最长的key
    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        return query.substring(0, length);
    }

    // returns the length of the longest string key in the subtrie
    // rooted at x that is a prefix of the query string,
    // assuming the first d character match and we have already
    // found a prefix match of length length
    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.isString) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }


    //从集合中删除指定的key
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.isString) n--;
            x.isString = false;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.isString) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }
}