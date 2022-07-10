package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class code_387 {

    class Solution {
        public int firstUniqChar(String s) {
            Map<Character, Integer> frequency = new HashMap<Character, Integer>();
            for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
            }
            for (int i = 0; i < s.length(); ++i) {
                if (frequency.get(s.charAt(i)) == 1) {
                    return i;
                }
            }
            return -1;
        }
    }

    class Solution2 {
        public int firstUniqChar(String s) {
            Map<Character, Integer> position = new HashMap<Character, Integer>();
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                char ch = s.charAt(i);
                if (position.containsKey(ch)) {
                    position.put(ch, -1);
                } else {
                    position.put(ch, i);
                }
            }
            int first = n;
            for (Map.Entry<Character, Integer> entry : position.entrySet()) {
                int pos = entry.getValue();
                if (pos != -1 && pos < first) {
                    first = pos;
                }
            }
            if (first == n) {
                first = -1;
            }
            return first;
        }
    }


    class Solution3 {
        public int firstUniqChar(String s) {
            Map<Character, Integer> position = new HashMap<Character, Integer>();
            Queue<Pair> queue = new LinkedList<Pair>();
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                char ch = s.charAt(i);
                if (!position.containsKey(ch)) {
                    position.put(ch, i);
                    queue.offer(new Pair(ch, i));
                } else {
                    position.put(ch, -1);
                    while (!queue.isEmpty() && position.get(queue.peek().ch) == -1) {
                        queue.poll();
                    }
                }
            }
            return queue.isEmpty() ? -1 : queue.poll().pos;
        }

        class Pair {
            char ch;
            int pos;

            Pair(char ch, int pos) {
                this.ch = ch;
                this.pos = pos;
            }
        }
    }


}
