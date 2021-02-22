package Questions.String;
/**
 * Created by Defias on 2020/06.
 * Description: 字典树/前缀树

 实现1 - 结点的字符隐式的保存在数据结构中
 */

public class Trie {
    private static int R = 26;

    //结点
    public static class TrieNode {
        public boolean is_end;   //记录当前结点是不是一个单词的结束字母
        public TrieNode[] child;  //value记录孩子结点     索引位置key标示了当前字母是多少

        public TrieNode() {
            child = new TrieNode[R];  //子结点数组长度26，0：‘a’，1：‘b’.....
            is_end = false;
        }
    }

    TrieNode root;  //记录前缀树的根

    public Trie() {
        root = new TrieNode();
    }

    //插入单词
    public void insert(String word) {
        TrieNode ptr = root;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (ptr.child[ch-'a'] == null) {  //如果c - 'a'为空，说明还没有存入
                ptr.child[ch-'a'] = new TrieNode();  //存入节点
            }

            ptr = ptr.child[ch-'a'];
        }
        ptr.is_end = true;  //最后的结点为单词的最后一个单词，is_end设置为true
    }


    //查找单词
    public boolean search(String word) {
        TrieNode ptr = root;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (ptr.child[ch-'a'] == null) { //如果不存在于前缀树中，返回false
                return false;
            }
            ptr = ptr.child[ch-'a'];
        }

        return ptr.is_end; //如果所有字符都在前缀树中，那么判断最后一个字母结束标志是否为true
    }

    //查找单词前缀
    public  boolean startsWith(String prefix) {
        TrieNode ptr = root;
        for(int i=0; i<prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (ptr.child[ch-'a'] == null) { //如果不存在于前缀树中，返回false
                return false;
            }
            ptr = ptr.child[ch-'a'];
        }

        return true;
    }

}
