package Algorithm.String;
import java.util.ArrayList;
import java.util.List;
import Algorithm.String.Trie.Trie4.TrieNode;
/**
 * @author: Defias
 * @date: 2020/12/8
 * @description: 单词搜索 II
 */
public class WordSearchII2 {
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();


    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[] {"oath","pea","eat","rain"};
        System.out.println((new WordSearchII2()).findWords(board, words));
    }

    // 回溯 + 字典树Trie
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtrace(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtrace(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // 当前字符标记为特殊字符以防重复使用
        this._board[row][col] = '#';

        // 0表示不变，正负1表示在当前行列坐标上进行四个方向的调整，获得下一个字符
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];

            //调整后的行列值合法性检查
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }

            if (currNode.children.containsKey(this._board[newRow][newCol])) {
                backtrace(newRow, newCol, currNode);
            }
        }

        // 还原
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}