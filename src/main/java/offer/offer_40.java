package offer;

import utils.Utils;

import javax.rmi.CORBA.Util;
import java.util.*;

public class offer_40 {

    static class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || arr.length == 0) {
                return new int[0];
            }
            // 统计每个数字出现的次数
            int[] counter = new int[10001];
            for (int num: arr) {
                counter[num]++;
            }
            // 根据counter数组从头找出k个数作为返回结果
            int[] res = new int[k];
            int idx = 0;
            for (int num = 0; num < counter.length; num++) {
                while (counter[num]-- > 0 && idx < k) {
                    res[idx++] = num;
                }
                if (idx == k) {
                    break;
                }
            }
            return res;
        }
    }

    static class Solution1 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || arr.length == 0) {
                return new int[0];
            }
            // TreeMap的key是数字, value是该数字的个数。
            // cnt表示当前map总共存了多少个数字。
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int cnt = 0;
            for (int num: arr) {
                // 1. 遍历数组，若当前map中的数字个数小于k，则map中当前数字对应个数+1
                if (cnt < k) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                    cnt++;
                    continue;
                }
                // 2. 否则，取出map中最大的Key（即最大的数字), 判断当前数字与map中最大数字的大小关系：
                //    若当前数字比map中最大的数字还大，就直接忽略；
                //    若当前数字比map中最大的数字小，则将当前数字加入map中，并将map中的最大数字的个数-1。
                Map.Entry<Integer, Integer> entry = map.lastEntry();
                if (entry.getKey() > num) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                    if (entry.getValue() == 1) {
                        map.pollLastEntry();
                    } else {
                        map.put(entry.getKey(), entry.getValue() - 1);
                    }
                }

            }

            // 最后返回map中的元素
            int[] res = new int[k];
            int idx = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int freq = entry.getValue();
                while (freq-- > 0) {
                    res[idx++] = entry.getKey();
                }
            }
            return res;
        }
    }

    // 保持堆的大小为K，然后遍历数组中的数字，遍历的时候做如下判断：
    // 1. 若目前堆的大小小于K，将当前数字放入堆中。
    // 2. 否则判断当前数字与大根堆堆顶元素的大小关系，如果当前数字比大根堆堆顶还大，这个数就直接跳过；
    //    反之如果当前数字比大根堆堆顶小，先poll掉堆顶，再将该数字放入堆中。
    static class Solution2 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || arr.length == 0) {
                return new int[0];
            }
            // 默认是小根堆，实现大根堆需要重写一下比较器。
            Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
            for (int num: arr) {
                if (pq.size() < k) {
                    pq.offer(num);
                } else if (num < pq.peek()) {
                    pq.poll();
                    pq.offer(num);
                }
            }

            // 返回堆中的元素
            int[] res = new int[pq.size()];
            int idx = 0;
            for(int num: pq) {
                res[idx++] = num;
            }
            return res;
        }
    }

    static class Solution3 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || arr.length == 0) {
                return new int[0];
            }
            // 最后一个参数表示我们要找的是下标为k-1的数
            return quickSearch(arr, 0, arr.length - 1, k - 1);
        }

        private int[] quickSearch(int[] nums, int lo, int hi, int k) {
            // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
            int j = partition(nums, lo, hi);
            if (j == k) {
                return Arrays.copyOf(nums, j + 1);
            }
            // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
            return j > k? quickSearch(nums, lo, j - 1, k): quickSearch(nums, j + 1, hi, k);
        }

        // 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
        private int partition(int[] nums, int lo, int hi) {
            int v = nums[lo];
            int i = lo, j = hi + 1;
            while (true) {
                while (++i <= hi && nums[i] < v);
                while (--j >= lo && nums[j] > v);
                if (i >= j) {
                    break;
                }
                Utils.swap(nums, i, j);
            }
            nums[lo] = nums[j];
            nums[j] = v;
            return j;
        }
    }

    // 手写堆排序
    // 保持堆的大小为K，然后遍历数组中的数字，遍历的时候做如下判断：
    // 1. 若目前堆的大小小于K，将当前数字放入堆中。
    // 2. 否则判断当前数字与大根堆堆顶元素的大小关系，如果当前数字比大根堆堆顶还大，这个数就直接跳过；
    //    反之如果当前数字比大根堆堆顶小，先poll掉堆顶，再将该数字放入堆中。
    static class Solution4 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (arr == null || arr.length == 0 || k == 0) return new int[0];
            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            for (int num :
                    arr) {
                if (queue.size() < k) {
                    queue.offer(num);
                } else if (queue.peek() > num) {
                    queue.poll();
                    queue.offer(num);
                }
            }
            int[] res = new int[queue.size()];
            int idx = 0;
            for (int num :
                    queue) {
                res[idx ++] = num;
            }

            return res;
        }
    }

    // 手写快排
    static class Solution5 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || arr.length == 0) {
                return new int[0];
            }
            // 最后一个参数表示我们要找的是下标为k-1的数
            return quickSearch(arr, 0, arr.length - 1, k - 1);
        }

        private int[] quickSearch(int[] nums, int lo, int hi, int k) {
            // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
            int j = partition(nums, lo, hi);
            if (j == k) return Arrays.copyOf(nums, j + 1);

            // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
            return j > k ? quickSearch(nums, lo, j - 1, k) : quickSearch(nums, j + 1, hi, k);
        }

        // 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
        private int partition(int[] nums, int lo, int hi) {
            int v = nums[lo];
            int i = lo, j = hi + 1;
            while (true) {
                while (++i <= hi && nums[i] < v);
                while (--j >= lo && nums[j] > v);
                if (i >= j) break;
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
            }
            nums[lo] = nums[j];
            nums[j] = v;

            return j;
        }

        private int partition2(int[] nums, int l, int r) {
            int pivot = nums[r];
            int i = l - 1;
            for (int j = l; j <= r - 1; ++j) {
                if (nums[j] <= pivot) {
                    i = i + 1;
                    Utils.swap(nums, i, j);
                }
            }
            Utils.swap(nums, i + 1, r);
            return i + 1;
        }

    }


    public static void main(String[] args) {



    }

}
