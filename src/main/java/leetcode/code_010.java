package leetcode;

public class code_010 {

    public boolean isMatch(String s, String p){
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m ; ++i) {
            for (int j = 0; j < n; j++) {
                if (p.charAt(j - 1) == '*'){
                    //我们用 f[i][j] 表示 ss 的前 ii 个字符与 pp 中的前 jj 个字符是否能够匹配
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1))
                        f[i][j] = f[i][j] || f[i - 1][j];
                } else {
                    if (matches(s, p, i, j)){
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j){
        if(i == 0){
            return false;
        }
        if(p.charAt(j - 1) == '.'){
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
