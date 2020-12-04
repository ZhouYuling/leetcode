package leetcode;

class code_097 {
    public boolean isInterleave(String s1, String s2, String s3) {

        int n = s1.length(), m = s2.length(), t = s3.length();
        if(m + n != t){
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;

        for(int i = 0; i <= n ; ++i){
            for(int j = 0; j <= m ; ++j){
                int p = i + j -1;
                if(i > 0){
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if(j > 0){
                    f[i][j] = f[i][j] || (f[i][j -1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {

        int n = s1.length(), m = s2.length(), t = s3.length();
        if(m + n != t){
            return false;
        }

        boolean[] f = new boolean[m + 1];

        f[0] = true;
        for(int i = 0; i <= n; ++i){
            for(int j = 0; j <= m ; ++j){
                int p = i + j -1;
                if(i > 0){
                    f[j] = f[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if(j > 0){
                    f[j] = f[j] || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[m];

    }

    public static void main(String[] args) {

        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        code_097 code_097 = new code_097();
        boolean interleave2 = code_097.isInterleave2(s1, s2, s3);
        System.out.println(interleave2);



    }

}