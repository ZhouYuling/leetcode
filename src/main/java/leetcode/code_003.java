package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class code_003 {

    //hash
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            //边界值
            if (s.length()==0) return 0;
            //key存放的是字符，value存放的是下标
            HashMap<Character, Integer> map = new HashMap<Character, Integer>();
            //最长子串的长度
            int max = 0;
            //无重复字串左边界下标
            int left = 0;
            for(int i = 0; i < s.length(); i ++){
                //i下标下的字符
                char c = s.charAt(i);
                //之前字符串存在，则更新左下标
                if(map.containsKey(c)){
                    left = Math.max(left,map.get(c) + 1);
                }
                //更新hash值
                map.put(c,i);
                //获取最大值
                max = Math.max(max,i-left+1);
            }
            return max;

        }
    }

    //滑动窗口
    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<Character>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int rk = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符
                    // 窗口中不会存在重复字符，所以左指针可以使用这种方式
                    occ.remove(s.charAt(i - 1));
                }
                while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                    // 不断地移动右指针
                    occ.add(s.charAt(rk + 1));
                    ++rk;
                }
                // 第 i 到 rk 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, rk - i + 1);
            }
            return ans;
        }
    }


}
