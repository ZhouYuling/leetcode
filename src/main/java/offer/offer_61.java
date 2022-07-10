package offer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class offer_61 {

    class Solution {
        public boolean isStraight(int[] nums) {
            Set<Integer> repeat = new HashSet<>();
            int max = 0, min = 14;
            for(int num : nums) {
                if(num == 0) continue; // 跳过大小王
                max = Math.max(max, num); // 最大牌
                min = Math.min(min, num); // 最小牌
                if(repeat.contains(num)) return false; // 若有重复，提前返回 false
                repeat.add(num); // 添加此牌至 Set
            }
            return max - min < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
        }
    }

    class Solution1 {
        public boolean isStraight(int[] nums) {
            int joker = 0;
            Arrays.sort(nums); // 数组排序
            for(int i = 0; i < 4; i++) {
                if(nums[i] == 0) joker++; // 统计大小王数量
                else if(nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
            }
            return nums[4] - nums[joker] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
        }
    }

    class Solution2 {
        public boolean isStraight(int[] nums) {
            int joker = 0;
            Arrays.sort(nums); // 数组排序
            for(int i = 0; i < 4; i++) {
                if(nums[i] == 0) joker++; // 统计大小王数量
                else if(nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
            }
            return nums[4] - nums[joker] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
        }
    }

}
