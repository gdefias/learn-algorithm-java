package Questions.String;
/**
 * Created by Defias on 2017/10/14.

 单词搜索 II

 https://leetcode-cn.com/problems/word-search-ii/


 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。

 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

 m == board.length
 n == board[i].length
 1 <= m, n <= 12
 board[i][j] 是一个小写英文字母
 1 <= words.length <= 3 * 104
 1 <= words[i].length <= 10
 words[i] 由小写英文字母组成
 words 中的所有字符串互不相同

 */
import java.util.*;

public class WordSearchII {
    static Set<String> res = new HashSet<String>();


    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[] {"oath","pea","eat","rain"};
        System.out.println(findWords1(board, words));
    }

    //方法1： 使用Word Search中的方法蛮力查看每个单词是否存在  leetcode上运行会超时
    public static List<String> findWords1(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        WordSearch ws = new WordSearch();
        for(String word: words) {
            if(ws.exist(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    //方法2： 使用字典树Trie
    public static List<String> findWords2(char[][] board, String[] words) {
        Trie3 trie = new Trie3();
        for(String word: words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }
        return new ArrayList<String>(res);
    }

    public static void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie3 trie) {
        if(x<0 || x>=board.length || y<0 || y>=board[0].length || visited[x][y]) {
            return;
        }

        str += board[x][y];
        if(!trie.startWith(str)) {
            return;
        }

        if(trie.search(str)) {
            res.add(str);
        }

        visited[x][y] = true;
        dfs(board, visited, str, x+1, y, trie);
        dfs(board, visited, str, x-1, y, trie);
        dfs(board, visited, str, x, y+1, trie);
        dfs(board, visited, str, x, y-1, trie);
        visited[x][y] = false;
    }
}
