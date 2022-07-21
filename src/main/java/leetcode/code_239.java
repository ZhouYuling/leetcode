package leetcode;

import java.util.*;

public class code_239 {

    static class Solution {
        ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
        int [] nums;

        public void clean_deque(int i, int k) {
            // remove indexes of elements not from sliding window
            if (!deq.isEmpty() && deq.getFirst() == i - k)
                deq.removeFirst();

            // remove from deq indexes of all elements
            // which are smaller than current element nums[i]
            while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])
                deq.removeLast();
        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            if (n * k == 0) return new int[0];
            if (k == 1) return nums;

            // init deque and output
            this.nums = nums;
            int max_idx = 0;
            for (int i = 0; i < k; i++) {
                clean_deque(i, k);
                deq.addLast(i);
                // compute max in nums[:k]
                if (nums[i] > nums[max_idx]) max_idx = i;
            }
            int [] output = new int[n - k + 1];
            output[0] = nums[max_idx];

            // build output
            for (int i  = k; i < n; i++) {
                clean_deque(i, k);
                deq.addLast(i);
                output[i - k + 1] = nums[deq.getFirst()];
            }
            return output;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] ints = solution.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ints));

    }


    //优先队列
    //peek不弹出，查看对
    class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] pair1, int[] pair2) {
                    //先根据值进行降序排序，值相等的情况下根据下标进行降序排序
                    return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
                }
            });
            //构建第一个大根堆，因为窗口是k
            for (int i = 0; i < k; ++i) {
                //插入优先队列中
                pq.offer(new int[]{nums[i], i});
            }
            // 注意长度n-k+1
            int[] ans = new int[n - k + 1];
            ans[0] = pq.peek()[0];
            for (int i = k; i < n; ++i) {
                pq.offer(new int[]{nums[i], i});
                //手动弹出过期的数字(i - k为过期 )
                while (pq.peek()[1] <= i - k) {
                    //poll删除队列的头
                    pq.poll();
                }
                ans[i - k + 1] = pq.peek()[0];
            }
            return ans;
        }
    }

    //单调队列
    class Solution3 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            Deque<Integer> deque = new LinkedList<Integer>();
            for (int i = 0; i < k; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
            }

            int[] ans = new int[n - k + 1];
            ans[0] = nums[deque.peekFirst()];
            for (int i = k; i < n; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                //队列保存的是
                deque.offerLast(i);
                while (deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
            return ans;
        }
    }


    class Solution4 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] prefixMax = new int[n];
            int[] suffixMax = new int[n];
            for (int i = 0; i < n; ++i) {
                if (i % k == 0) {
                    prefixMax[i] = nums[i];
                }
                else {
                    prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
                }
            }
            for (int i = n - 1; i >= 0; --i) {
                if (i == n - 1 || (i + 1) % k == 0) {
                    suffixMax[i] = nums[i];
                } else {
                    suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
                }
            }

            int[] ans = new int[n - k + 1];
            for (int i = 0; i <= n - k; ++i) {
                ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
            }
            return ans;
        }
    }

    //手写优先队列
    class Solution5 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) return new int[0];
            int n = nums.length;
            PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1]);
            for (int i = 0; i < k; i++) {
                pq.offer(new int[]{nums[i], i});
            }
            int[] ans = new int[n - k + 1];
            ans[0] = pq.peek()[0];
            for (int i = k; i < n; i++) {
                pq.offer(new int[]{nums[i], i});
                while (pq.peek()[1] <= i - k) pq.poll();
                ans[i - k + 1] = pq.peek()[0];
            }
            return ans;
        }
    }

    // 手写单调队列
    class Solution6 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < k; i++) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) deque.pollLast();
                deque.offerLast(i);
            }
            int[] ans = new int[n - k + 1];
            ans[0] = nums[deque.peekFirst()];
            for (int i = k; i < n; i++) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) deque.pollLast();
                deque.offerLast(i);
                while (deque.peekFirst() <= i - k) deque.pollFirst();
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
            return ans;
        }
    }


}
