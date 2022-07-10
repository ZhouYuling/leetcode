package leetcode;

public class code_079 {

    public boolean exist(char[][] board, String word) {

        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        // 确定单词起始位置
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // 假设board[i][j]位置和单词首字母(下标为0)一直
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {

        // 判断board[i][j]是否和s字符串上的k位置字符一致，不一致返回，结束循环
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }

        // 表示[i][j]位置已经访问
        visited[i][j] = true;
        // 上下右左，一般二维矩阵的算法题中都有这个属性
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            // 遍历四个方向，确定新左表，确定是否存在s下一个位置(k+1)的字符存在在二维数组中
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                //确定不能范围，才进行递归遍历
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }

        // 复原现场
        visited[i][j] = false;
        return result;
    }

}
