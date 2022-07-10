package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class code_301 {

    static class Solution {

        private Set<String> validExpressions = new HashSet<String>();
        private int minimumRemoved;

        private void reset() {
            this.validExpressions.clear();
            this.minimumRemoved = Integer.MAX_VALUE;
        }

        private void recurse(
                String s,
                int index,
                int leftCount,
                int rightCount,
                StringBuilder expression,
                int removedCount) {

            // If we have reached the end of string.
            if (index == s.length()) {

                // If the current expression is valid.
                if (leftCount == rightCount) {

                    // If the current count of removed parentheses is <= the current minimum count
                    if (removedCount <= this.minimumRemoved) {

                        // Convert StringBuilder to a String. This is an expensive operation.
                        // So we only perform this when needed.
                        String possibleAnswer = expression.toString();

                        // If the current count beats the overall minimum we have till now
                        if (removedCount < this.minimumRemoved) {
                            this.validExpressions.clear();
                            this.minimumRemoved = removedCount;
                        }
                        this.validExpressions.add(possibleAnswer);
                    }
                }
            } else {

                char currentCharacter = s.charAt(index);
                int length = expression.length();

                // If the current character is neither an opening bracket nor a closing one,
                // simply recurse further by adding it to the expression StringBuilder
                if (currentCharacter != '(' && currentCharacter != ')') {
                    expression.append(currentCharacter);
                    this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                    expression.deleteCharAt(length);
                } else {

                    // Recursion where we delete the current character and move forward
                    this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                    expression.append(currentCharacter);

                    // If it's an opening parenthesis, consider it and recurse
                    if (currentCharacter == '(') {
                        this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                    } else if (rightCount < leftCount) {
                        // For a closing parenthesis, only recurse if right < left
                        this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                    }

                    // Undoing the append operation for other recursions.
                    expression.deleteCharAt(length);
                }
            }
        }

        public List<String> removeInvalidParentheses(String s) {

            this.reset();
            this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
            return new ArrayList(this.validExpressions);
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        List<String> strings = solution.removeInvalidParentheses("()())()");
        System.out.println(strings);

//        Solution2 solution2 = new Solution2();
//        List<String> strings2 = solution2.removeInvalidParentheses("()())()");
//        System.out.println(strings2);

    }

    class Solution2 {
        private List<String> res = new ArrayList<String>();

        //程序入口
        public List<String> removeInvalidParentheses(String s) {
            int lremove = 0;
            int rremove = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    lremove++;
                } else if (s.charAt(i) == ')') {
                    if (lremove == 0) {
                        rremove++;
                    } else {
                        lremove--;
                    }
                }
            }
            helper(s, 0, lremove, rremove);

            return res;
        }

        private void helper(String str, int start, int lremove, int rremove) {
            if (lremove == 0 && rremove == 0) {
                if (isValid(str)) {
                    res.add(str);
                }
                return;
            }

            for (int i = start; i < str.length(); i++) {
                // 避免重复
                if (i != start && str.charAt(i) == str.charAt(i - 1)) {
                    continue;
                }
                // 如果剩余的字符无法满足去掉的数量要求，直接返回
                if (lremove + rremove > str.length() - i) {
                    return;
                }
                // 尝试去掉一个左括号
                if (lremove > 0 && str.charAt(i) == '(') {
                    helper(str.substring(0, i) + str.substring(i + 1), i, lremove - 1, rremove);
                }
                // 尝试去掉一个右括号
                if (rremove > 0 && str.charAt(i) == ')') {
                    helper(str.substring(0, i) + str.substring(i + 1), i, lremove, rremove - 1);
                }
            }
        }

    }

    class Solution3 {
        public List<String> removeInvalidParentheses(String s) {
            List<String> ans = new ArrayList<String>();
            Set<String> currSet = new HashSet<String>();

            currSet.add(s);
            while (true) {
                //判断集合中的元素是否是有效字符串
                for (String str : currSet) {
                    if (isValid(str)) {
                        ans.add(str);
                    }
                }
                //删除最小数量的无效括号
                if (ans.size() > 0) {
                    return ans;
                }
                Set<String> nextSet = new HashSet<String>();
                for (String str : currSet) {
                    for (int i = 0; i < str.length(); i++) {
                        //去重
                        if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
                            continue;
                        }

                        //删除i位置的左括号或者右括号
                        if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                            nextSet.add(str.substring(0, i) + str.substring(i + 1));
                        }
                    }
                }
                currSet = nextSet;
            }
        }

    }

    //从左到右遍历，1.总数cnt!=0异常 2.cnt<0异常
    private boolean isValid(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                cnt++;
            } else if (str.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }

        return cnt == 0;
    }

    //枚举状态子集
    class Solution4 {
        public List<String> removeInvalidParentheses(String s) {
            int lremove = 0;
            int rremove = 0;
            List<Integer> left = new ArrayList<Integer>();
            List<Integer> right = new ArrayList<Integer>();
            List<String> ans = new ArrayList<String>();
            Set<String> cnt = new HashSet<String>();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left.add(i);
                    lremove++;
                } else if (s.charAt(i) == ')') {
                    right.add(i);
                    if (lremove == 0) {
                        rremove++;
                    } else {
                        lremove--;
                    }
                }
            }

            int m = left.size();
            int n = right.size();
            List<Integer> maskArr1 = new ArrayList<Integer>();
            List<Integer> maskArr2 = new ArrayList<Integer>();
            for (int i = 0; i < (1 << m); i++) {
                if (Integer.bitCount(i) != lremove) {
                    continue;
                }
                maskArr1.add(i);
            }
            for (int i = 0; i < (1 << n); i++) {
                if (Integer.bitCount(i) != rremove) {
                    continue;
                }
                maskArr2.add(i);
            }
            for (int mask1 : maskArr1) {
                for (int mask2 : maskArr2) {
                    if (checkValid(s, mask1, left, mask2, right)) {
                        cnt.add(recoverStr(s, mask1, left, mask2, right));
                    }
                }
            }
            for (String v : cnt) {
                ans.add(v);
            }

            return ans;
        }

        private boolean checkValid(String str, int lmask, List<Integer> left, int rmask, List<Integer> right) {
            int pos1 = 0;
            int pos2 = 0;
            int cnt = 0;

            for (int i = 0; i < str.length(); i++) {
                if (pos1 < left.size() && i == left.get(pos1)) {
                    if ((lmask & (1 << pos1)) == 0) {
                        cnt++;
                    }
                    pos1++;
                } else if (pos2 < right.size() && i == right.get(pos2)) {
                    if ((rmask & (1 << pos2)) == 0) {
                        cnt--;
                        if (cnt < 0) {
                            return false;
                        }
                    }
                    pos2++;
                }
            }

            return cnt == 0;
        }

        private String recoverStr(String str, int lmask, List<Integer> left, int rmask, List<Integer> right) {
            StringBuilder sb = new StringBuilder();
            int pos1 = 0;
            int pos2 = 0;

            for (int i = 0; i < str.length(); i++) {
                if (pos1 < left.size() && i == left.get(pos1)) {
                    if ((lmask & (1 << pos1)) == 0) {
                        sb.append(str.charAt(i));
                    }
                    pos1++;
                } else if (pos2 < right.size() && i == right.get(pos2)) {
                    if ((rmask & (1 << pos2)) == 0) {
                        sb.append(str.charAt(i));
                    }
                    pos2++;
                } else {
                    sb.append(str.charAt(i));
                }
            }

            return sb.toString();
        }
    }

}
