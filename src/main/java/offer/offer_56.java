package offer;

import java.util.HashMap;
import java.util.Map;

public class offer_56 {

    // 剑指 Offer 56 - I. 数组中数字出现的次数
    // 使用hash，存储频次，也可以存储下标
    public int[] singleNumbers(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        for (int ch : nums) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        int[] res = new int[2];
        int flag = 0;
        for (int num : nums) {
            if (frequency.get(num) == 1 && flag <= 1) {
                res[flag++] = num;
            }
        }
        return res;
    }

    // 剑指 Offer 56 - I. 数组中数字出现的次数
    // 使用位运算
    public int[] singleNumbers2(int[] nums) {
        int ret = 0;
        for (int n : nums) ret ^= n;
        int div = 1;
        while ((div & ret) == 0) div <<= 1;
        int a = 0,b = 0;
        for (int n : nums) {
            if ((n & div) != 0) a ^= n;
            else b ^= n;
        }

        return new int[]{a, b};
    }

    // 剑指 Offer 56 - II. 数组中数字出现的次数 II
    //
    public int singleNumbers3(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        for (int ch : nums) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int num : nums) {
            if (frequency.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }

}
