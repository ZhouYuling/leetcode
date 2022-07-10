package offer;

import java.util.LinkedList;
import java.util.Queue;

public class offer_13 {

    private static int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    // 方法一：广度优先遍历
    static class Solution {
        public int movingCount(int m, int n, int k) {
            if (k < 0) return -1;
            if (k == 0) return 1;
            boolean[][] visited = new boolean[m+1][n+1];
            int[][] directions = {{1, 0}, {0, 1}};
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            visited[0][0] = true;
            int ans = 1;
            while (!queue.isEmpty()){
                int[] cell = queue.poll();
                for (int[] dir : directions) {
                    int newi = cell[0] + dir[0], newj = cell[1] + dir[1];
                    if (newi < 0 || newi >= m || newj < 0 || newj >= n || visited[newi][newj] || get(newi) + get(newj) > k) continue;

                    queue.offer(new int[]{newi, newj});
                    visited[newi][newj] = true;
                    ans ++;
                }
            }
            return ans;

        }


    }


    // 方法二：深度优先遍历
    static class Solution2 {
        public int movingCount(int m, int n, int k) {
            if (k == 0) {
                return 1;
            }
            boolean[][] vis = new boolean[m][n];
            int ans = 1;
            vis[0][0] = true;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if ((i == 0 && j == 0) || get(i) + get(j) > k) {
                        continue;
                    }
                    // 边界判断
                    if (i - 1 >= 0) {
                        vis[i][j] |= vis[i - 1][j];
                    }
                    if (j - 1 >= 0) {
                        vis[i][j] |= vis[i][j - 1];
                    }
                    ans += vis[i][j] ? 1 : 0;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int i = new Solution().movingCount(5, 4, 0);
        System.out.println(i);
    }

}
