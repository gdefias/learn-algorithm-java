package Questions.String;
import java.util.HashMap;

/**
 * @author: Defias
 * @date: 2020/12/8
 * @description: HashMap实现   根节点不包含字符，除根节点外的每一个子节点都包含一个字符
 */
public class Trie4 {

    static class TrieNode {
        //以单词的字符为key，当前字符节点为value
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;  //单词的最后一个字符节点上存word
        public TrieNode() {}
    }
}
