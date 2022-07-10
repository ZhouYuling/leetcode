package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class code_207 {

    //深度优先搜索
    class Solution {

        //储存边
        List<List<Integer>> edges;
        int[] visited;
        boolean valid = true;

        //程序入口
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            //初始化边
            edges = new ArrayList<List<Integer>>();
            //根据课程数量，初始化边
            for (int i = 0; i < numCourses; ++i) {
                edges.add(new ArrayList<Integer>());
            }
            //初始化访问
            visited = new int[numCourses];
            //初始化约束条件
            for (int[] info : prerequisites) {
                //课程info[1]依赖于info[0]课程完成以后才能学
                edges.get(info[1]).add(info[0]);
            }
            //遍历所有课程
            for (int i = 0; i < numCourses && valid; ++i) {
                //未访问
                if (visited[i] == 0) {
                    //则使用深度遍历
                    dfs(i);
                }
            }
            return valid;
        }

        //0-未搜索 1-搜索中 2-已完成
        public void dfs(int u) {
            //先把这个点标记为已经访问
            visited[u] = 1;
            //获取所有依赖课程
            for (int v: edges.get(u)) {
                //如果未访问
                if (visited[v] == 0) {
                    //开始搜索v
                    dfs(v);
                    //如果存在环，则返回
                    if (!valid) {
                        return;
                    }
                } else if (visited[v] == 1) {//判断存在一个还，返回错误，不能输出节点
                    valid = false;
                    return;
                }
            }
            //已完成
            visited[u] = 2;
        }
    }

    //广度优先遍历
    class Solution2 {
        List<List<Integer>> edges;
        //用于维护该下表课程还有多少依赖的课程数
        int[] indeg;

        //程序入口
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            edges = new ArrayList<List<Integer>>();
            //初始化依赖条件
            for (int i = 0; i < numCourses; ++i) {
                edges.add(new ArrayList<Integer>());
            }
            indeg = new int[numCourses];
            //赋值依赖条件
            for (int[] info : prerequisites) {
                edges.get(info[1]).add(info[0]);
                ++indeg[info[0]];
            }

            //定义一个队列
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; ++i) {
                //如果没有依赖的课程
                if (indeg[i] == 0) {
                    //放入队列
                    queue.offer(i);
                }
            }

            //总访问数量
            int visited = 0;
            while (!queue.isEmpty()) {
                ++visited;
                int u = queue.poll();
                for (int v: edges.get(u)) {
                    //该依赖减1
                    --indeg[v];
                    //如果v节点没有依赖的课程，考察v节点
                    if (indeg[v] == 0) {
                        queue.offer(v);
                    }
                }
            }

            //如果访问数，小于课程数，那么可能存在环那么不嫩完成所有课程的学习
            return visited == numCourses;
        }
    }



}
