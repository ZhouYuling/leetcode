package leetcode;

public class code_925 {

    static class Solution {
        public boolean isLongPressedName(String name, String typed) {
            int i = 0, j = 0;
            while (j < typed.length()) {
                if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                    i++;
                    j++;
                } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                    j++;
                } else {
                    return false;
                }
            }
            return i == name.length();
        }
    }

    public static void main(String[] args) {

        String name = "alex", typed = "aaleex";
        Solution solution = new Solution();
        boolean longPressedName = solution.isLongPressedName(name, typed);
        System.out.println(longPressedName);

    }

}
