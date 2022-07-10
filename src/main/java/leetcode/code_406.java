package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class code_406 {

    //从低到高
    static class Solution {
        public int[][] reconstructQueue(int[][] people) {
            //排序，首先按照升高排序，接着按照下标排序
            Arrays.sort(people, new Comparator<int[]>() {
                public int compare(int[] person1, int[] person2) {
                    if (person1[0] != person2[0]) {
                        return person1[0] - person2[0];
                    } else {
                        return person2[1] - person1[1];
                    }
                }
            });
            int n = people.length;
            int[][] ans = new int[n][];
            for (int[] person : people) {
                int spaces = person[1] + 1;
                for (int i = 0; i < n; ++i) {
                    if (ans[i] == null) {
                        --spaces;
                        if (spaces == 0) {
                            ans[i] = person;
                            break;
                        }
                    }
                }
            }
            return ans;
        }
    }


    //从高到低
    static class Solution2 {
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, new Comparator<int[]>() {
                public int compare(int[] person1, int[] person2) {
                    if (person1[0] != person2[0]) {
                        return person2[0] - person1[0];
                    } else {
                        return person1[1] - person2[1];
                    }
                }
            });
            List<int[]> ans = new ArrayList<int[]>();
            for (int[] person : people) {
                ans.add(person[1], person);
            }
            return ans.toArray(new int[ans.size()][]);
        }
    }

    public static void main(String[] args) {

        int[][] ints = new Solution().reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
        for (int[] t: ints) {
            System.out.println(Arrays.toString(t));
        }

    }


}
