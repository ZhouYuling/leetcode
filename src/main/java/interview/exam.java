package interview;

import java.util.*;

public class exam {


    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] tmp : items1) {
            map.put(tmp[0], tmp[1]);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int[] tmp : items2) {
            if (map.containsKey(tmp[0])) {
                int value = tmp[1] + map.get(tmp[0]);
                res.add(Arrays.asList(tmp[0], value));
                map.remove(tmp[0]);
            } else {
                res.add(Arrays.asList(tmp[0], tmp[1]));
            }
        }
        if (!map.isEmpty()) {
            Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
            for (Map.Entry<Integer, Integer> entry : entries) {
                res.add(Arrays.asList(entry.getKey(), entry.getValue()));
            }
        }
        res.sort((o1, o2) -> o1.get(0) - o2.get(0));
        return res;
    }

    public long countBadPairs(int[] nums) {
        long res = 0L;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (j - i != nums[j] - nums[i]) res ++;
            }
        }
        return res;
    }

    // nums[i] - i != nums[j] - j
    public long countBadPairs2(int[] nums) {
        long res = 0L;
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            m.put(nums[i] - i, m.getOrDefault(nums[i] - i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            long v = entry.getValue();
            res += v * (v - 1) / 2;
        }

        return (long) nums.length * (nums.length - 1) / 2 - res;
    }

    public static long factorial(long number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }

    public long taskSchedulerI(int[] tasks, int space) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        // 最多的执行次数
        int maxDays = 0;
        for (int ch : tasks) {
            int day = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, day);
            maxDays = Math.max(maxDays, day);
        }

        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Integer, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxDays) {
                ++maxCount;
            }
        }

        return Math.max((maxDays - 1) * (space + 1) + maxCount, tasks.length);

    }

    public long taskSchedulerII(int[] tasks, int space) {

        Map<Integer, Integer> rest = new HashMap<>();
        long res = 0;
        for (int task : tasks) {
            if (okTask(rest, task)) {
                res++;
                restTodayTask(rest, task, space);
                continue;
            }
            while (!okTask(rest, task)) {
                res++;
                restOneDay(rest);
            }
            res++;
            restTodayTask(rest, task, space);
        }

        return res;
    }

    private void restTodayTask(Map<Integer, Integer> rest, int task, int space) {
        restOneDay(rest);
        rest.put(task, space);
    }

    private boolean okTask(Map<Integer, Integer> rest, int task) {
        if (rest.isEmpty()) return true;
        if (!rest.containsKey(task)) return true;
        for (Map.Entry<Integer, Integer> entry : rest.entrySet()) {
            if (entry.getValue() <= 0) return true;
        }
        return false;
    }

    private void restOneDay(Map<Integer, Integer> rest) {
        for (Map.Entry<Integer, Integer> entry : rest.entrySet()) {
            if (entry.getValue() > 0) rest.put(entry.getKey(), entry.getValue() - 1);
        }
    }

    public int arithmeticTriplets(int[] nums, int diff) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            while (left < nums.length && nums[left] - nums[i] < diff) {
                left ++;
            }
            int right = left + 1;
            while (right < nums.length && nums[right] - nums[left] < diff) {
                right ++;
            }
            if (left < nums.length && right < nums.length && nums[left] - nums[i] == diff && nums[right] - nums[left] == diff) {
                res ++;
            }
        }

        return res;
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Set<Integer> rSet = new HashSet<>();
        for (int res : restricted) {
            rSet.add(res);
        }

        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            if (rSet.contains(edge[0]) || rSet.contains(edge[1])) continue;
            if (map.containsKey(edge[0])) {
                HashSet<Integer> set = map.get(edge[0]);
                set.add(edge[1]);
            } else {
                HashSet<Integer> set = new HashSet<>();
                set.add(edge[1]);
                map.put(edge[0], set);
            }
            if (map.containsKey(edge[1])) {
                HashSet<Integer> list = map.get(edge[1]);
                list.add(edge[0]);
            } else {
                HashSet<Integer> list = new HashSet<>();
                list.add(edge[0]);
                map.put(edge[1], list);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> zeroList = map.get(0);
        HashSet<Integer> visited = new HashSet<>();
        int level = 0;
        level ++;
        visited.add(0);
        if (zeroList == null || zeroList.size() == 0) return 1;
        for (Integer z : zeroList) {
            queue.offer(z);
            visited.add(z);
            level ++;
        }

        while (visited.size() <= n && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                HashSet<Integer> tmp = map.get(queue.poll());
                if (tmp == null) continue;
                for (Integer z : tmp) {
                    if (visited.contains(z)) continue;
                    queue.offer(z);
                    visited.add(z);
                    level ++;
                }
            }
        }
        return level;
    }

    public int longestIdealString(String s, int k) {
        int[] f = new int[s.length()];
        f[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                int c1 = s.charAt(i) - 'a';
                int c2 = s.charAt(j) - 'a';
                if (Math.abs(c1 - c2) <= k) {
                    int tmp = f[j] + 1;
                    max = Math.max(max, tmp);
                }
            }
            f[i] = max;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = Math.max(res, f[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        exam exam = new exam();
//        int[][] edges = {{0, 1}, {1, 2}, {3, 1}, {4, 0}, {0, 5}, {5, 6}};
//        int[] restricted = {4, 5};
//        long l = exam.reachableNodes(7, edges, restricted);
//        System.out.println(l);
//
//        int[][] edges1 = {{0, 1}};
//        int[] restricted1 = {1};
//        long l1 = exam.reachableNodes(7, edges1, restricted1);
//        System.out.println(l1);
//        int i = exam.longestIdealString("pvjcci", 4);
//        System.out.println(i);
//        System.out.println(exam.longestIdealString("acfgbd", 2));
//        System.out.println(exam.longestIdealString("abcd", 3));
        System.out.println(exam.longestIdealString("lkpkxcigcs", 6));


    }


    public boolean validPartition(int[] nums) {



        return false;
    }

    private boolean check(int[] nums, int left, int right) {
        int length = right - left;
        // 恰由2个相等
        if (length == 2) {
            return nums[left] == nums[right];
        }
        if (length == 3) {
            if (nums[left] == nums[left + 1] && nums[left + 1] == nums[right]) return true;
            return nums[left] == nums[left + 1] - 1 && nums[left + 1] == nums[right] - 1;
        }
        return false;
    }


}
