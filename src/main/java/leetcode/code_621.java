package leetcode;

import java.util.*;

public class code_621 {

    public int leastInterval(char[] tasks, int n) {

        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (char ch : tasks) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        // 任务总数
        int m = freq.size();
        //下一次有效执行时间
        List<Integer> nextValid = new ArrayList<Integer>();
        //剩余执行次数
        List<Integer> rest = new ArrayList<Integer>();
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            nextValid.add(1);
            rest.add(value);
        }

        int time = 0;
        for (int i = 0; i < tasks.length; ++i) {
            ++time;
            int minNextValid = Integer.MAX_VALUE;
            //获取所有任务的最小冷却时间
            for (int j = 0; j < m; ++j) {
                if (rest.get(j) != 0) {
                    minNextValid = Math.min(minNextValid, nextValid.get(j));
                }
            }
            //添加待命的时间
            time = Math.max(time, minNextValid);
            int best = -1;
            //获取剩余执行次数最多的那个任务
            for (int j = 0; j < m; ++j) {
                //获取能够执行的任务
                if (rest.get(j) != 0 && nextValid.get(j) <= time) {
                    //选择剩余执行次数最多的那个任务
                    if (best == -1 || rest.get(j) > rest.get(best)) {
                        best = j;
                    }
                }
            }
            //设置下一次可执行时间
            nextValid.set(best, time + n + 1);
            //减少剩余执行次数
            rest.set(best, rest.get(best) - 1);
        }

        return time;
    }

    public static void main(String[] args) {

        code_621 code = new code_621();
        char[] ints = {'A', 'A', 'A', 'B', 'B', 'B'};
        int i = code.leastInterval(ints, 2);
        System.out.println(i);

    }

    public int leastInterval2(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        // 最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }

        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }

}
