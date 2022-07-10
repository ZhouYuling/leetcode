package leetcode;

import java.util.Arrays;

public class code_179 {

    class Solution {
        public String largestNumber(int[] nums) {
            int n = nums.length;
            // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
            Integer[] numsArr = new Integer[n];
            for (int i = 0; i < n; i++) {
                numsArr[i] = nums[i];
            }

            Arrays.sort(numsArr, (x, y) -> {
                // 字符串拼接，根据字符顺序拼接，没有交换律
                long x1 = Long.parseLong(x.toString() + y.toString());
                long y1 = Long.parseLong(y.toString() + x.toString());
                return (int) (y1 - x1);
            });

            if (numsArr[0] == 0) {
                return "0";
            }
            StringBuilder ret = new StringBuilder();
            for (int num : numsArr) {
                ret.append(num);
            }
            return ret.toString();
        }
    }

    public static void main(String[] args) {

        Integer x = 1;
        Integer y = 101;

        System.out.println(x.toString());
        System.out.println(y.toString());

        long x1 = Long.parseLong(x.toString() + y.toString());
        long y1 = Long.parseLong(y.toString() + x.toString());

        System.out.println(x1);
        System.out.println(y1);

    }


}
