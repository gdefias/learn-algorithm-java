package Algorithm.DFSAndBFS;

/**
 * Created by Defias on 2020/07.
 * Description:  矩阵中的路径

 https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/

 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向
 左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

 [["a","b","c","e"],
 ["s","f","c","s"],
 ["a","d","e","e"]]

 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

 
 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 输出：true

 输入：board = [["a","b"],["c","d"]], word = "abcd"
 输出：false

 1 <= board.length <= 200
 1 <= board[i].length <= 200
 */

public class PathInMatrix {

    public static void main(String[] args) {
        char[][] A = new char[][] { {'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'} };
        String word = "ABCCED";

        System.out.println(exist2(A, word));
    }

    //DFS 使用'/'字符标识已访问过的位置
    public static boolean exist(char[][] board, String word) {
        if(board==null || word==null) {
            return false;
        }

        char[] words = word.toCharArray();
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        //剪枝
        if(i>=board.length || i<0 || j>=board[0].length || j<0  || board[i][j] != words[k] ) {
            return false;
        }

        if(k == words.length-1) {
            return true;
        }

        //回溯
        char tmp = board[i][j];
        board[i][j] = '/';

        //按下上左右的顺序分别进行dfs
        boolean res = dfs(board, words, i+1, j, k+1) ||
                dfs(board, words, i-1, j, k+1) ||
                dfs(board, words, i, j-1, k+1) ||
                dfs(board, words, i, j+1, k+1);

        board[i][j] = tmp;

        return res;
    }



    //DFS 使用visited[][]标识已访问过的位置
    public static boolean exist2(char[][] board, String word) {
        if(board==null || word==null) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        char[] words = word.toCharArray();

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(dfs2(board, words, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs2(char[][] board, char[] words, int k, int i, int j, boolean[][] visited) {
        //剪枝
        if(i>=board.length || i<0 || j>=board[0].length || j<0 || visited[i][j] || board[i][j] != words[k]) {
            return false;
        }

        if(k == words.length-1) {
            return true;
        }

        //回溯
        visited[i][j] = true;

        boolean res = dfs2(board, words, k+1, i+1, j, visited) ||
                dfs2(board, words, k+1, i-1, j, visited) ||
                dfs2(board, words, k+1, i, j-1, visited) ||
                dfs2(board, words, k+1, i, j+1, visited);

        visited[i][j] = false;

        return res;
    }

}
