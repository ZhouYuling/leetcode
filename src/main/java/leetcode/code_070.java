package leetcode;

public class code_070 {

    public int climbStairs(int n) {
        int[][] q = {{1, 1},{1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n){
        int[][] ret = {{1, 0}, {0, 1}};
        while(n > 0){
            if((n & 1) == 1){
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b){
        int[][] c = new int[2][2];
        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < 2; j++){
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }

        return c;
    }

    public static void main(String[] args) {
        code_070 code_070 = new code_070();
        int i = code_070.climbStairs(5);
        System.out.println(i);

        int[][] ret = {{1, 0}, {0, 1}};
        System.out.println(ret[0][0] + "  " + ret[0][1]+ "  " + ret[1][0]+ "  " + ret[1][1]);
    }

    // 手写爬楼梯
    public int climbStairs1(int n) {
        int p = 0,q = 0, r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = q + p;
        }

        return r;
    }

}
