package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class code_076 {

    // 该题中的字符可以重复，所以不能使用diff判断字符
    static class Solution {
        // 存放模式串t串所有的字符，key是字符，value是字符出现的次数
        Map<Character, Integer> ori = new HashMap<Character, Integer>();
        // 存放元串s的所有字符
        Map<Character, Integer> cnt = new HashMap<Character, Integer>();

        public String minWindow(String s, String t) {
            int tLen = t.length();
            for (int i = 0; i < tLen; i++) {
                char c = t.charAt(i);
                ori.put(c, ori.getOrDefault(c, 0) + 1);
            }
            int l = 0, r = -1;
            int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
            int sLen = s.length();
            while (r < sLen) {
                //右移窗口，把母串的字符放到cnt中
                ++r;
                if (r < sLen && ori.containsKey(s.charAt(r))) {
                    cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
                }
                // 如果模式串都包含在窗口中，窗口左移
                while (check() && l <= r) {
                    if (r - l + 1 < len) {
                        // 最小覆盖子串的长度
                        len = r - l + 1;
                        // 最小覆盖子串的左侧
                        ansL = l;
                        // 最小覆盖子串的右侧
                        ansR = l + len;
                    }
                    // 窗口左移字符计数减一
                    if (ori.containsKey(s.charAt(l))) {
                        cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                    }
                    // 光标左移
                    ++l;
                }
            }
            return ansL == -1 ? "" : s.substring(ansL, ansR);
        }

        // 这个方法的时间复杂度是n，n是t串的长度；看起来也不是很费尽的方法
        public boolean check() {
            Iterator iter = ori.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Character key = (Character) entry.getKey();
                Integer val = (Integer) entry.getValue();
                if (cnt.getOrDefault(key, 0) < val) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String s1 = solution.minWindow(s, t);
        System.out.println(s1);

    }

}
