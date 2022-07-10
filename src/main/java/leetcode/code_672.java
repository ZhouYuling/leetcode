package leetcode;

import java.util.*;

public class code_672 {

    class Solution {
        // 大根堆
        public List<String> topKFrequent(String[] words, int k) {
            // 创建大根堆
            PriorityQueue<String[]> pq = new PriorityQueue<>(new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    if (!Objects.equals(o1[1], o2[1])) return  Integer.parseInt(o2[1]) - Integer.parseInt(o1[1]);
                    else return o1[0].compareTo(o2[0]);
                }
            });
            // 计算频次
            HashMap<String, Integer> wordMap = new HashMap<>();
            for (String word :
                    words) {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
            // 把所有元素都放到大根堆里面
            Set<Map.Entry<String, Integer>> entries = wordMap.entrySet();
            for (Map.Entry<String, Integer> entry:
                 entries) {
                pq.offer(new String[]{entry.getKey(), entry.getValue() + ""});
            }
            // 取出前k个元素
            ArrayList<String> res = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                if (!pq.isEmpty()) res.add(pq.poll()[0]);
            }

            return res;
        }

        public List<String> topKFrequent2(String[] words, int k) {
            // 计算频次
            HashMap<String, Integer> wordMap = new HashMap<>();
            for (String word :
                    words) {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
            // 创建小根堆
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    if (!Objects.equals(o1.getValue(), o2.getValue())) return  o1.getValue() - o2.getValue();
                    // 字典序的反序
                    else return o2.getKey().compareTo(o1.getKey());
                }
            });
            // 把所有元素都放到大根堆里面
            Set<Map.Entry<String, Integer>> entries = wordMap.entrySet();
            for (Map.Entry<String, Integer> entry:
                    entries) {
                pq.offer(entry);
                if (pq.size() > k) pq.poll();
            }
            // 取出前k个元素
            ArrayList<String> res = new ArrayList<>();
            while (!pq.isEmpty()) res.add(pq.poll().getKey());
            // 这个千万别忘了
            Collections.reverse(res);
            return res;
        }
    }

    public static void main(String[] args) {
        code_672 code = new code_672();
        Solution solution = code.new Solution();
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        System.out.println(solution.topKFrequent(words, 4));
        System.out.println(solution.topKFrequent2(words, 4));


    }

}
