package offer;

public class offer_12 {

    class Solution {
        public boolean exist(char[][] board, String word) {
            char[] words = word.toCharArray();
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(dfs(board, words, i, j, 0)) return true;
                }
            }
            return false;
        }
        boolean dfs(char[][] board, char[] word, int i, int j, int k) {
            if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
            if(k == word.length - 1) return true;
            char tmp = board[i][j];
            board[i][j] = '/';
            boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                    dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
            board[i][j] = tmp;
            return res;
        }
    }

    // 手写
    class Solution2 {

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public boolean exist(char[][] board, String word) {
            char[] words = word.toCharArray();
            if (words.length == 0) return true;
            if (board.length == 0 || board[0].length == 0) return false;
            int h = board.length, w = board[0].length;
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (dfs(board, words, visited, i, j, 0)) return true;
                }
            }

            return false;
        }

        private boolean dfs(char[][] board, char[] words, boolean[][] visited, int i, int j, int k) {
            if (board[i][j] != words[k]) return false;
            if (k == words.length - 1) return true;

            boolean result = false;
            visited[i][j] = true;
            for (int[] dir : directions){
                int newi = i + dir[0], newj = j + dir[1];
                if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length){
                    if (!visited[newi][newj]) {
                        boolean flag = dfs(board, words, visited, newi, newj, k + 1);
                        if (flag) {
                            result = true;
                            break;
                        }
                    }
                }
            }
            visited[i][j] = false;

            return result;
        }
    }
}
