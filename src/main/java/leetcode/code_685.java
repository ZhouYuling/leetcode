package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class code_685 {

    static class Solution {
        public int[] findRedundantDirectedConnection(int[][] edges) {
            int nodesCount = edges.length;
            UnionFind uf = new UnionFind(nodesCount + 1);
            int[] parent = new int[nodesCount + 1];
            for (int i = 1; i <= nodesCount; ++i) {
                parent[i] = i;
            }
            int conflict = -1;
            int cycle = -1;
            for (int i = 0; i < nodesCount; ++i) {
                int[] edge = edges[i];
                int node1 = edge[0], node2 = edge[1];
                if (parent[node2] != node2) {
                    conflict = i;
                } else {
                    parent[node2] = node1;
                    if (uf.find(node1) == uf.find(node2)) {
                        cycle = i;
                    } else {
                        uf.union(node1, node2);
                    }
                }
            }
            if (conflict < 0) {
                int[] redundant = {edges[cycle][0], edges[cycle][1]};
                return redundant;
            } else {
                int[] conflictEdge = edges[conflict];
                if (cycle >= 0) {
                    int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                    return redundant;
                } else {
                    int[] redundant = {conflictEdge[0], conflictEdge[1]};
                    return redundant;
                }
            }
        }
    }

    static class UnionFind {
        int[] ancestor;

        public UnionFind(int n) {
            ancestor = new int[n];
            for (int i = 0; i < n; ++i) {
                ancestor[i] = i;
            }
        }

        public void union(int index1, int index2) {
            ancestor[find(index1)] = find(index2);
        }

        public int find(int index) {
            if (ancestor[index] != index) {
                ancestor[index] = find(ancestor[index]);
            }
            return ancestor[index];
        }
    }

    public static void main(String[] args) {

        int[][] edges = {{1,2}, {1,3}, {2,3}};
        Solution solution = new Solution();
        int[] redundantDirectedConnection = solution.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(redundantDirectedConnection));
        System.out.println(redundantDirectedConnection);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(list);

        Student student = new Student(101, "李四");
        System.out.println(student);
        System.out.println(student.toString());

    }

    static class Student{
        private int classId;
        private String name;

        public Student(int classId, String name) {
            this.classId = classId;
            this.name = name;
        }

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "student{" +
                    "classId=" + classId +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
