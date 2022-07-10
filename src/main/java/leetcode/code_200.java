package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class code_200 {

    //并查集
    static class Solution {
        class UnionFind {
            int count;
            int[] parent;
            int[] rank;

            public UnionFind(char[][] grid) {
                count = 0;
                int m = grid.length;
                int n = grid[0].length;
                parent = new int[m * n];
                rank = new int[m * n];
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (grid[i][j] == '1') {
                            parent[i * n + j] = i * n + j;
                            ++count;
                        }
                        rank[i * n + j] = 0;
                    }
                }
            }

            public int find(int i) {
                if (parent[i] != i) parent[i] = find(parent[i]);
                return parent[i];
            }

            public void union(int x, int y) {
                int rootx = find(x);
                int rooty = find(y);
                if (rootx != rooty) {
                    if (rank[rootx] > rank[rooty]) {
                        parent[rooty] = rootx;
                    } else if (rank[rootx] < rank[rooty]) {
                        parent[rootx] = rooty;
                    } else {
                        parent[rooty] = rootx;
                        rank[rootx] += 1;
                    }
                    --count;
                }
            }

            public int getCount() {
                return count;
            }
        }

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;
            int num_islands = 0;
            UnionFind uf = new UnionFind(grid);
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        grid[r][c] = '0';
                        if (r - 1 >= 0 && grid[r-1][c] == '1') {
                            uf.union(r * nc + c, (r-1) * nc + c);
                        }
                        if (r + 1 < nr && grid[r+1][c] == '1') {
                            uf.union(r * nc + c, (r+1) * nc + c);
                        }
                        if (c - 1 >= 0 && grid[r][c-1] == '1') {
                            uf.union(r * nc + c, r * nc + c - 1);
                        }
                        if (c + 1 < nc && grid[r][c+1] == '1') {
                            uf.union(r * nc + c, r * nc + c + 1);
                        }
                    }
                }
            }

            return uf.getCount();
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        int i = solution.numIslands(grid);
        System.out.println(i);

    }


    //深度优先搜索
    class Solution1{
        void dfs(char[][] grid, int r, int c) {
            //岛屿的长
            int nr = grid.length;
            //岛屿的宽
            int nc = grid[0].length;

            //超过边界，或者该坐标点为0则返回
            if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
                return;
            }

            //该坐标为1，则赋值为0
            grid[r][c] = '0';
            //向上移一个单位
            dfs(grid, r - 1, c);
            //向下移一个单位
            dfs(grid, r + 1, c);
            //向左移一个单位
            dfs(grid, r, c - 1);
            //向右移一个单位
            dfs(grid, r, c + 1);
        }

        //程序入口
        public int numIslands(char[][] grid) {
            //矩阵为空，或者长度为0
            if (grid == null || grid.length == 0) {
                return 0;
            }

            //矩阵长
            int nr = grid.length;
            //矩阵宽
            int nc = grid[0].length;
            //岛屿的数量
            int num_islands = 0;
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    //该坐标对应的值为1
                    if (grid[r][c] == '1') {
                        //岛屿数量+1
                        ++num_islands;
                        //根据该坐标扩散找周围坐标是否为1
                        dfs(grid, r, c);
                    }
                }
            }

            return num_islands;
        }
    }

    class Solution2 {
        public int numIslands(char[][] grid) {
            //边界
            if (grid == null || grid.length == 0) {
                return 0;
            }

            //长
            int nr = grid.length;
            //宽
            int nc = grid[0].length;
            //岛屿个数
            int num_islands = 0;

            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    //该坐标为1
                    if (grid[r][c] == '1') {
                        //岛屿数量+1
                        ++num_islands;
                        //该点坐标置为0
                        grid[r][c] = '0';
                        //使用队列进行广度优先搜索
                        Queue<Integer> neighbors = new LinkedList<>();
                        //加入队列表示当前位置为1
                        neighbors.add(r * nc + c);
                        while (!neighbors.isEmpty()) {
                            int id = neighbors.remove();
                            //还原该点的坐标
                            int row = id / nc;
                            int col = id % nc;
                            //向左移动一个位置
                            if (row - 1 >= 0 && grid[row-1][col] == '1') {
                                //放入队列
                                neighbors.add((row-1) * nc + col);
                                //把该节点置为0
                                grid[row-1][col] = '0';
                            }
                            //向右移动一个位置
                            if (row + 1 < nr && grid[row+1][col] == '1') {
                                neighbors.add((row+1) * nc + col);
                                grid[row+1][col] = '0';
                            }
                            //向上移动一个位置
                            if (col - 1 >= 0 && grid[row][col-1] == '1') {
                                neighbors.add(row * nc + col-1);
                                grid[row][col-1] = '0';
                            }
                            //向下移动一个位置
                            if (col + 1 < nc && grid[row][col+1] == '1') {
                                neighbors.add(row * nc + col+1);
                                grid[row][col+1] = '0';
                            }
                        }
                    }
                }
            }

            return num_islands;
        }
    }




}
