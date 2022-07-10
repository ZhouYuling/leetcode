package leetcode;

public class code_844 {

    // 模拟法
    class Solution {
        public boolean backspaceCompare(String s, String t) {
            if (s == null && t == null) return Boolean.TRUE;
            if (s == null || t == null) return Boolean.FALSE;
            return build(s).equals(build(t));
        }

        private String build(String t) {
            StringBuilder str = new StringBuilder();
            char[] chars = t.toCharArray();
            for (char aChar : chars) {
                if (aChar != '#') str.append(aChar);
                else if(str.length() > 0) str.deleteCharAt(str.length() - 1);
            }

            return str.toString();
        }
    }

    // 双指针
    class Solution2 {

        public boolean backspaceCompare(String s, String t) {
            int i = s.length() - 1,j = t.length() - 1;
            int skipS = 0,skipT = 0;
            while (i >= 0 || j >= 0) {
                while (i >= 0) {
                    if (s.charAt(i) == '#') {
                        skipS ++;
                        i --;
                    } else if (skipS > 0){
                        skipS --;
                        i --;
                    } else {
                        break;
                    }
                }
                while (j >= 0) {
                    if (t.charAt(j) == '#') {
                        skipT ++;
                        j --;
                    } else if (skipT > 0){
                        skipT --;
                        j --;
                    } else {
                        break;
                    }
                }
                if (i >= 0 && j >= 0) {
                    if (s.charAt(i) != t.charAt(j)) return false;
                } else {
                    if (i >= 0 || j >= 0) return false;
                }
                i --;
                j --;

            }


            return true;
        }

    }

}
