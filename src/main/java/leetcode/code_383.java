package leetcode;

public class code_383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] cnt = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            cnt[magazine.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            cnt[ransomNote.charAt(i) - 'a'] --;
            if (cnt[ransomNote.charAt(i) - 'a'] < 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        code_383 code = new code_383();
        boolean b = code.canConstruct("aa", "aab");
        System.out.println(b);
    }

}
