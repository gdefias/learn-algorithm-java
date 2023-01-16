package Algorithm.String.Trie;
/**
 * Created by Defias on 2017/10/15.
   字典树

   实现2 - 结点的字符显示的定义在结点中
 */
public class Trie2 {
    private static int R = 26;

    //结点
    public static class TrieNode {
        public char val;  //结点的字符
        public boolean isWord;   //当前结点是不是一个单词的结束字母
        public TrieNode[] children;  //数组，也可以用hashmap

        public TrieNode() {
            children = new TrieNode[R];
        }

        public TrieNode(char c) {
            this.val = c;
            children = new TrieNode[R];
        }
    }


    private TrieNode root;  //根结点

    public Trie2() {
        root = new TrieNode();
        root.val = '\0';
    }

    //插入单词
    public void insert(String word) {
        TrieNode node = root;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if(node.children[ch-'a'] == null) {
                node.children[ch-'a'] = new TrieNode(ch);
            }
            node = node.children[ch-'a'];
        }
        node.isWord = true;
    }

    //查找单词
    public boolean search(String word) {
        TrieNode node = root;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if(node.children[ch-'a']==null)
                return false;
            node = node.children[ch-'a'];
        }
        return node.isWord;
    }

    //查找单词前缀
    public boolean startWith(String prefix) {
        TrieNode node = root;
        for(int i=0; i<prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if(node.children[ch-'a']==null)
                return false;
            node = node.children[ch-'a'];
        }
        return true;
    }
}
