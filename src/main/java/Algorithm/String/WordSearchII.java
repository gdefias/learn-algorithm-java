package Algorithm.String;
import Algorithm.String.Trie.Trie3;

import java.util.*;
/**
 * Created by Defias on 2017/10/14.

 单词搜索 II

 https://leetcode-cn.com/problems/word-search-ii/


 给定一个m x n 二维字符网格board和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 输出：["eat","oath"]

 提示：
 m == board.length
 n == board[i].length
 1 <= m, n <= 12
 board[i][j] 是一个小写英文字母
 1 <= words.length <= 3 * 104
 1 <= words[i].length <= 10
 words[i] 由小写英文字母组成
 words 中的所有字符串互不相同
 */


public class WordSearchII {
    public static Set<String> res = new HashSet<String>();

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[] {"oath","pea","eat","rain"};
        System.out.println(findWords(board, words));
    }

    //方法2： 回溯 + 字典树Trie
    public static List<String> findWords(char[][] board, String[] words) {
        Trie3 trie = new Trie3();
        for(String word: words) {
            trie.insert(word);
        }

        String str = "";  //记录已搜索到的字符串前缀

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                dfs(board, visited, str, i, j, trie);
            }
        }
        return new ArrayList<String>(res);
    }

    public static void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie3 trie) {
        if(x<0 || x>=board.length || y<0 || y>=board[0].length || visited[x][y]) {
            return;
        }

        str += board[x][y];
        if(!trie.startWith(str)) {  //已搜索到的字符串无效
            return;
        }

        if(trie.search(str)) {  //已搜索到一个完整的字符串
            res.add(str);
        }

        visited[x][y] = true;

        dfs(board, visited, str, x+1, y, trie);
        dfs(board, visited, str, x-1, y, trie);
        dfs(board, visited, str, x, y+1, trie);
        dfs(board, visited, str, x, y-1, trie);

        visited[x][y] = false;
    }


    //方法2：不使用字典树 只是使用回溯 在leetcode上运行会超时
    public static List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        WordSearch ws = new WordSearch();
        for(String word: words) {
            if(ws.exist(board, word)) {
                res.add(word);
            }
        }
        return res;
    }
}
