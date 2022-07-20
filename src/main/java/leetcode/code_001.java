package leetcode;

import java.util.HashMap;

public class code_001 {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]))
                // 注意返回的是下标还是值，数组中的类型要保持一致
                return new int[]{i, map.get(target - nums[i])};
            else map.put(nums[i], i);
        }

        return new int[0];
    }

}
