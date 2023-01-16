package Algorithm.String.Trie;
import Algorithm.Queue.MyQueue2;
import Lib.StdOut;
/**
 * Created by Defias on 2020/06.
 * Description:  单词查找树符号表 - R向单词查找树
 */


public class TrieR256KV<Value> {
    private static final int R = 256;        // extended ASCII
    private Node root;      // root of trie
    private int n;          // number of keys in trie

    // R-way trie node
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public static void main(String[] args) {

        TrieR256KV<Integer> st = new TrieR256KV<Integer>();
        st.put("sea", 2);
        st.put("by", 4);
        st.put("sells", 1);
        st.put("shells", 3);
        st.put("the", 5);


        // print results
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }

        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();


        StdOut.println("keysThatMatch(\".he.l.\"):");
        for (String s : st.keysThatMatch(".he.l."))
            StdOut.println(s);
    }


    public TrieR256KV() {
    }


    //键key所对应的值，键不存在时返回空
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null)
            return null;
        return (Value) x.val;
    }


    //表中是否保存着key的值
    public boolean contains(String key) {
        return get(key) != null;
    }

    private Node get(Node x, String key, int d) {
        if (x == null)
            return null;
        if (d == key.length())
            return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }


    //向表中插入键值对，如果值为null则删除该键
    public void put(String key, Value val) {
        if (val == null)
            delete(key);
        else
            root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null)
            x = new Node();
        if (d == key.length()) {
            if (x.val == null)
                n++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }


    //键值对的数量
    public int size() {
        return n;
    }

    //符号表是否为空
    public boolean isEmpty() {
        return size() == 0;
    }


    //符号表中的所有键
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }


    //所有以prefix为前缀的键
    public Iterable<String> keysWithPrefix(String prefix) {
        MyQueue2<String> results = new MyQueue2<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, MyQueue2<String> results) {
        if (x == null) return;
        if (x.val != null) results.enqueue(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    //所有和pattern匹配的键，其中.匹配任意字符
    public Iterable<String> keysThatMatch(String pattern) {
        MyQueue2<String> results = new MyQueue2<String>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, MyQueue2<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.val != null)
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


    //query的前缀中最长的键
    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        else return query.substring(0, length);
    }

    // returns the length of the longest string key in the subtrie
    // rooted at x that is a prefix of the query string,
    // assuming the first d character match and we have already
    // found a prefix match of given length (-1 if no such match)
    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }


    //删除键key和值
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.val != null) n--;
            x.val = null;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }
}
