package leetcode;

public class code_647 {

    public int countSubstrings(String s) {
        // 动态规划法
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        code_647 code = new code_647();
        int abc = code.countSubstrings("aaa");
        System.out.println(abc);
    }

    public int countSubstrings1(String s) {

        int ans = 0;
        for (int center = 0; center < 2 * s.length(); center++) {
            int left = center / 2;
            int right = left + center % 2;

            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                ans ++;
                left --;
                right ++;
            }
        }

        return ans;
    }

}
