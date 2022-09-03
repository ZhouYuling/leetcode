package offer;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class offer_49 {

    class Ugly {
        public int[] nums = new int[1690];
        Ugly() {
            HashSet<Long> seen = new HashSet();
            PriorityQueue<Long> heap = new PriorityQueue<Long>();
            seen.add(1L);
            heap.add(1L);

            long currUgly, newUgly;
            int[] primes = new int[]{2, 3, 5};
            for(int i = 0; i < 1690; ++i) {
                currUgly = heap.poll();
                nums[i] = (int)currUgly;
                for(int j : primes) {
                    newUgly = currUgly * j;
                    if (!seen.contains(newUgly)) {
                        seen.add(newUgly);
                        heap.add(newUgly);
                    }
                }
            }
        }
    }

    class Solution {
        public Ugly u = new Ugly();
        public int nthUglyNumber(int n) {
            return u.nums[n - 1];
        }
    }

    public int nthUglyNumber2(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            if (i == n - 1) System.out.println(curr);
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) heap.offer(next);
            }
        }
        return ugly;
    }

    public int nthUglyNumber4(int n) {
        int[] factors = {2, 3, 5};
        Set<Integer> seen = new HashSet<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        seen.add(1);
        heap.offer(1);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            int curr = heap.poll();
            if (curr < 0) {
                System.out.println(100);
            }
            ugly = (int) curr;
            if (i == 1363) {
                System.out.println(110);
            }
            // 因为在枚举的过程中，n太大会导致中间元素可能超出int的最大值。
            // 具体理解是，乘以5这个倍数很大了，题目给的假设，刚好就比Integer最大值大一点点，那么*5很有可能越界成为负数，负值最小，在堆顶
            for (int factor : factors) {
                int next = curr * factor;
                if (seen.add(next)) heap.offer(next);
            }
        }
        return ugly;
    }

    public static void main(String[] args) {
        offer_49 code = new offer_49();
        int i = code.nthUglyNumber2(1365);
        System.out.println(i);
        System.out.println(code.nthUglyNumber4(1365));
    }

    public int nthUglyNumber3(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) p2 ++;
            if (dp[i] == num3) p3 ++;
            if (dp[i] == num5) p5 ++;
        }
        return dp[n];
    }

}
