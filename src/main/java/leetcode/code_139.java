package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class code_139 {

    public static class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        String s = "leetcode";
        List<String> wordDict = new ArrayList<String>(){
            {
                add("leet");
                add("code");
            }
        };
        boolean b = solution.wordBreak(s, wordDict);
        System.out.println(b);

    }

}
