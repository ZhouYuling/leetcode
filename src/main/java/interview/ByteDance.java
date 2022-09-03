package interview;

import java.util.Stack;

// 蜡笔小松面试字节，将手机号码转化为a-zA-z
// https://blog.csdn.net/u012027907/article/details/77683813
public class ByteDance {
    private static final String numStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //10进制转为其他进制，除留取余，逆序排列
    public static String _10_to_N(Long number, int N) {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            // 直接result.append即可，多此一举
            stack.add(numStr.charAt((int)(rest % N)));
            rest = rest / N;
        }
        for (; !stack.isEmpty();) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "":result.toString();

    }

    // 其他进制转为10进制，按权展开
    public static long N_to_10(String number, int N) {
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        // 从len - 1开始遍历
        for (int i = len - 1; i >= 0; i--) {
            int index = numStr.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }

        return result;
    }


    public static void main(String[] args) {


        String s = _10_to_N(15080934399L, 52);
        System.out.println(s);

        System.out.println(N_to_10(s, 52));



    }

}
