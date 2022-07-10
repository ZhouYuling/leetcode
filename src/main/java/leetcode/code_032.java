package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class code_032 {

    //不使用额外的空间
    public static class Solution {

        public int longestValidParentheses(String s) {
            int left = 0, right = 0, maxlength = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxlength = Math.max(maxlength, 2 * right);
                } else if (right > left) {
                    left = right = 0;
                }
            }
            //以上没有办法求出(()这个字符串的结果
            left = right = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxlength = Math.max(maxlength, 2 * left);
                } else if (left > right) {
                    left = right = 0;
                }
            }
            return maxlength;
        }

    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int i = solution.longestValidParentheses(")()())");

    }


    //动态规划
    class Solution1 {
        public int longestValidParentheses(String s) {
            int maxans = 0;
            int[] dp = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;
        }
    }

    //栈
    class Solution2 {
        public int longestValidParentheses(String s) {
            int maxans = 0;
            Deque<Integer> stack = new LinkedList<Integer>();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else { // 匹配了当前右括号
                    stack.pop();
                    if (stack.isEmpty()) {
                        //最后一个没有被匹配的右括号的下标
                        stack.push(i);
                    } else {
                        //以该右括号为结尾的最长有效括号的长度
                        maxans = Math.max(maxans, i - stack.peek());
                    }
                }
            }
            return maxans;
        }
    }


}
