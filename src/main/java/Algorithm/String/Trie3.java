package Algorithm.String;
/**
 * Created by Defias on 2017/11/4.
 *
 * 字典树  - 实现3 - 单词字符串显示的定义在字符串尾结点中
 */

public class Trie3 {
    private static int R = 26;  //基数

    public static class TrieNode3 {
        public String item;  //字符串
        public TrieNode3[] children;

        public TrieNode3() {
            item = "";
            children = new TrieNode3[R];
        }

        public TrieNode3(String word) {
            item = word;
            children = new TrieNode3[R];
        }
    }

    public TrieNode3 root;  //根结点

    public Trie3() {
        root = new TrieNode3();
    }

    public void insert(String word) {
        TrieNode3 node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode3();
            }
            node = node.children[c - 'a'];
        }
        node.item = word;      //单词字符串保存到字符串尾结点中
    }

    public boolean search(String word) {
        TrieNode3 node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.item.equals(word);
    }

    public boolean startWith(String prefix) {
        TrieNode3 node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return true;
    }
}
