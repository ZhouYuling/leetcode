package offer;

public class offer_10 {

    static class Solution {
        public int fib(int n) {
            int a = 0, b = 1, sum;
            for(int i = 0; i < n; i++){
                sum = (a + b) % 1000000007;
                a = b;
                b = sum;
            }
            return a;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int fib = solution.fib(2);
        System.out.println(fib);

    }

}
