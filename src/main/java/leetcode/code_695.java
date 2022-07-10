package leetcode;

import utils.TreeNode;

import java.util.*;

public class code_695 {

    // 深度优先搜索
    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int ans = 0;
            for (int i = 0; i != grid.length; ++i) {
                for (int j = 0; j != grid[0].length; ++j) {
                    // 求最大面积的岛屿
                    ans = Math.max(ans, dfs(grid, i, j));
                }
            }
            return ans;
        }

        public int dfs(int[][] grid, int cur_i, int cur_j) {
            // 边界，并且当且位置不为1
            if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                return 0;
            }
            grid[cur_i][cur_j] = 0;
            //i:0->4 上 下 右 左
            int[] di = {0, 0, 1, -1};
            int[] dj = {1, -1, 0, 0};
            int ans = 1;
            for (int index = 0; index != 4; ++index) {
                int next_i = cur_i + di[index], next_j = cur_j + dj[index];
                ans += dfs(grid, next_i, next_j);
            }
            return ans;
        }
    }


    // 使用栈，代替方法调用
    class Solution2 {
        public int maxAreaOfIsland(int[][] grid) {
            int ans = 0;
            for (int i = 0; i != grid.length; ++i) {
                for (int j = 0; j != grid[0].length; ++j) {
                    int cur = 0;
                    // 使用两个栈，同时保存x轴和y轴的坐标
                    Deque<Integer> stacki = new LinkedList<Integer>();
                    Deque<Integer> stackj = new LinkedList<Integer>();
                    //当前位置(i, j)压入栈
                    stacki.push(i);
                    stackj.push(j);
                    while (!stacki.isEmpty()) {
                        int cur_i = stacki.pop(), cur_j = stackj.pop();
                        if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                            continue;
                        }
                        ++cur;
                        grid[cur_i][cur_j] = 0;
                        //i:0->4 上 下 右 左
                        int[] di = {0, 0, 1, -1};
                        int[] dj = {1, -1, 0, 0};
                        for (int index = 0; index != 4; ++index) {
                            int next_i = cur_i + di[index], next_j = cur_j + dj[index];
                            //压入栈
                            stacki.push(next_i);
                            stackj.push(next_j);
                        }
                    }
                    ans = Math.max(ans, cur);
                }
            }
            return ans;
        }
    }


    // 广度优先搜索
    class Solution3 {
        public int maxAreaOfIsland(int[][] grid) {
            int ans = 0;
            for (int i = 0; i != grid.length; ++i) {
                for (int j = 0; j != grid[0].length; ++j) {
                    int cur = 0;
                    Queue<Integer> queuei = new LinkedList<Integer>();
                    Queue<Integer> queuej = new LinkedList<Integer>();
                    queuei.offer(i);
                    queuej.offer(j);
                    while (!queuei.isEmpty()) {
                        int cur_i = queuei.poll(), cur_j = queuej.poll();
                        if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                            continue;
                        }
                        ++cur;
                        grid[cur_i][cur_j] = 0;
                        int[] di = {0, 0, 1, -1};
                        int[] dj = {1, -1, 0, 0};
                        for (int index = 0; index != 4; ++index) {
                            int next_i = cur_i + di[index], next_j = cur_j + dj[index];
                            queuei.offer(next_i);
                            queuej.offer(next_j);
                        }
                    }
                    ans = Math.max(ans, cur);
                }
            }
            return ans;
        }
    }



}
