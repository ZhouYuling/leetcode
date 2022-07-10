package leetcode;

import java.util.HashSet;
import java.util.Set;

//最长连续序列
public class code_128 {

    //哈希表
    public int longestConsecutive(int[] nums) {
        //定义一个hash表
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        //遍历所有数字，获取最长连续序列
        for (int num : num_set) {
            //从最小数字开始计算长度
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                //一直循环，直到找到最大的数字
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                //求最大长度和当前最长连续序列最大值
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

}
