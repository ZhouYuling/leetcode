package leetcode;

import java.util.Collections;
import java.util.LinkedList;

public class code_394 {

    //栈操作
    static class Solution {
        int ptr;

        //程序入口
        public String decodeString(String s) {
            LinkedList<String> stk = new LinkedList<String>();
            ptr = 0;

            while (ptr < s.length()) {
                char cur = s.charAt(ptr);
                if (Character.isDigit(cur)) {
                    // 获取一个数字并进栈
                    String digits = getDigits(s);
                    stk.addLast(digits);
                } else if (Character.isLetter(cur) || cur == '[') {
                    // 获取一个字母并进栈
                    stk.addLast(String.valueOf(s.charAt(ptr++)));
                } else {
                    // 遍历到了右括号
                    ++ptr;
                    //用来装字母
                    LinkedList<String> sub = new LinkedList<String>();
                    while (!"[".equals(stk.peekLast())) {
                        sub.addLast(stk.removeLast());
                    }
                    //栈中的字母是倒叙的
                    Collections.reverse(sub);
                    // 左括号出栈
                    stk.removeLast();
                    // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                    int repTime = Integer.parseInt(stk.removeLast());
                    StringBuilder t = new StringBuilder();
                    String o = getString(sub);
                    // 根据次数构造字符串
                    while (repTime-- > 0) {
                        t.append(o);
                    }
                    // 将构造好的字符串入栈
                    stk.addLast(t.toString());
                }
            }

            return getString(stk);
        }

        public String getDigits(String s) {
            StringBuffer ret = new StringBuffer();
            while (Character.isDigit(s.charAt(ptr))) {
                ret.append(s.charAt(ptr++));
            }
            return ret.toString();
        }

        public String getString(LinkedList<String> v) {
            StringBuffer ret = new StringBuffer();
            for (String s : v) {
                ret.append(s);
            }
            return ret.toString();
        }
    }

    //递归
    static class Solution2 {
        String src;
        //全局下标，用于记录当前位置
        int ptr;

        public String decodeString(String s) {
            src = s;
            ptr = 0;
            return getString();
        }

        public String getString() {
            if (ptr == src.length() || src.charAt(ptr) == ']') {
                // String -> EPS
                return "";
            }

            char cur = src.charAt(ptr);
            int repTime = 1;
            String ret = "";

            if (Character.isDigit(cur)) {
                // String -> Digits [ String ] String
                // 解析 Digits
                repTime = getDigits();
                // 过滤左括号
                ++ptr;
                // 递归解析 String，存在可能嵌套的关系
                String str = getString();
                // 过滤右括号
                ++ptr;
                // 构造字符串
                while (repTime-- > 0) {
                    ret += str;
                }
            } else if (Character.isLetter(cur)) {
                // String -> Char String
                // 解析 Char
                ret = String.valueOf(src.charAt(ptr++));
            }

            return ret + getString();
        }

        public int getDigits() {
            int ret = 0;
            while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
                ret = ret * 10 + src.charAt(ptr++) - '0';
            }
            return ret;
        }

    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        String s = "3[a]2[bc]";
        String s1 = solution.decodeString(s);
        System.out.println(s1);

    }

}
