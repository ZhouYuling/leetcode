package leetcode;

public class code_005 {

    public String longestPalindrome(String s) {
        if(s.equals("")) return "";
        String origin = s;
        String reverse = new StringBuilder(s).reverse().toString();
        int length = s.length();
        int[][] arr = new int[length][length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i ++){
            for (int j = 0; j < length; j++) {
                if (origin.charAt(i) == reverse.charAt(j)){
                    if(i == 0 || j == 0){
                        arr[i][j] = 1;
                    }else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }
                if (arr[i][j] > maxLen){
                    int beforeRev = length - 1 - j;
                    if(beforeRev + arr[i][j] - 1 == i) {
                        maxLen = arr[i][j];
                        maxEnd = i;
                    }
                }
            }
        }


        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);

    }

    public String longestPalindrome1(String s) {

        int len = s.length();
        if(len < 2){
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        for (int L = 0; L <= len ; L++) {
            for (int i = 0; i < len; i++) {
                int j = L + i - 1;
                if(j >= len) {
                    break;
                }

                if(charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                }else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

}
