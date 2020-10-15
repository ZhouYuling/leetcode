package offer;

public class offer_43 {

    static class Solution {
        public int countDigitOne(int n) {
            int count = 0;
            //依次考虑个位、十位、百位...是 1
            //k = 1000, 对应于上边举的例子
            for (int k = 1; k <= n; k *= 10) {
                // xyzdabc
                int abc = n % k;
                int xyzd = n / k;
                int d = xyzd % 10;
                int xyz = xyzd / 10;
                count += xyz * k;
                if (d > 1) {
                    count += k;
                }
                if (d == 1) {
                    count += abc + 1;
                }
                //如果不加这句的话，虽然 k 一直乘以 10，但由于溢出的问题
                //k 本来要大于 n 的时候，却小于了 n 会再次进入循环
                //此时代表最高位是 1 的情况也考虑完成了
                if(xyz == 0){
                    break;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int i = solution.countDigitOne(10);
        System.out.println(i);

    }

}
