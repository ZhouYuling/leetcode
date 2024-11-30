package dailychallenge;

public class d20241130_AliceAndBobGame {

    public static boolean canAliceWin(int[] nums) {
        int singleDigitSum = 0, doubleDigitSum = 0;
        int bobOtherSum1 = 0, bobOtherSum2 = 0;

        for (int num : nums) {
            if (num >= 10 && num <= 99) {
                doubleDigitSum += num;
            } else {
                bobOtherSum1 += num;
            }
            if (num >= 0 && num <= 9) {  // 两位数或个位数
                singleDigitSum += num;
            } else {
                bobOtherSum2 += num;
            }
        }

        return doubleDigitSum > bobOtherSum1 || singleDigitSum > bobOtherSum2;
    }

    public static void main(String[] args) {
        int[] example1 = {3, 25, 78, 1};
        System.out.println(canAliceWin(example1));  // 输出应为true

        int[] example2 = {100, 200, 300, 400};
        System.out.println(canAliceWin(example2));  // 输出应为false

        int[] example3 = {1,2,3,4,10};
        System.out.println(canAliceWin(example3));  // 输出应为false
    }

}
