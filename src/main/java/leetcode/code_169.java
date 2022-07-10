package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class code_169 {

    //哈希表
    class Solution1 {
        private Map<Integer, Integer> countNums(int[] nums) {
            Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
            for (int num : nums) {
                counts.put(num, counts.getOrDefault(num, 0) + 1);
            }
            return counts;
        }

        public int majorityElement(int[] nums) {
            Map<Integer, Integer> counts = countNums(nums);

            Map.Entry<Integer, Integer> majorityEntry = null;
            //遍历数组，获取最大值
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                    majorityEntry = entry;
                }
            }

            return majorityEntry.getKey();
        }
    }

    //排序
    class Solution2 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            //题目给出:多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素
            return nums[nums.length / 2];
        }
    }

    //随机化
    class Solution3 {
        //在[min, max]之间随机获取一个整数
        private int randRange(Random rand, int min, int max) {
            //rand.nextInt(100)获取[0, 99]中随机的一个数
            return rand.nextInt(max - min) + min;
        }

        private int countOccurences(int[] nums, int num) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == num) {
                    count++;
                }
            }
            return count;
        }

        public int majorityElement(int[] nums) {
            Random rand = new Random();

            int majorityCount = nums.length / 2;

            while (true) {
                //随机取数组nums中的一个值
                int candidate = nums[randRange(rand, 0, nums.length)];
                //统计数组nums中出现多少次，如果大于n / 2则为多数元素
                if (countOccurences(nums, candidate) > majorityCount) {
                    return candidate;
                }
            }
        }
    }


    //分治
    class Solution4 {
        //判断下表[lo, hi]中num出现的次数
        private int countInRange(int[] nums, int num, int lo, int hi) {
            int count = 0;
            for (int i = lo; i <= hi; i++) {
                if (nums[i] == num) {
                    count++;
                }
            }
            return count;
        }

        private int majorityElementRec(int[] nums, int lo, int hi) {
            //终止条件
            if (lo == hi) {
                return nums[lo];
            }

            //快速排序
            int mid = (hi - lo) / 2 + lo;
            int left = majorityElementRec(nums, lo, mid);
            int right = majorityElementRec(nums, mid + 1, hi);

            //左侧众数等于右侧众数
            if (left == right) {
                return left;
            }

            //计算左侧众数
            int leftCount = countInRange(nums, left, lo, hi);
            //计算右侧众数
            int rightCount = countInRange(nums, right, lo, hi);

            //取最大的数作为众数返回
            return leftCount > rightCount ? left : right;
        }

        public int majorityElement(int[] nums) {
            //定义入参
            return majorityElementRec(nums, 0, nums.length - 1);
        }
    }


}
