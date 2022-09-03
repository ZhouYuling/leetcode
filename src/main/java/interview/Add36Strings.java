package interview;

import java.util.Scanner;

public class Add36Strings {
    class Solution {
        /**
         * 代码与 LC 415 字符串相加 基本一致
         *
         * @param num1
         * @param num2
         * @return
         */
        public String add36Strings(String num1, String num2) {
            int i = num1.length() - 1, j = num2.length() - 1;
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            while (i >= 0 || j >= 0 || carry > 0) {
                int x = i >= 0 ? getInt(num1.charAt(i)) : 0;
                int y = j >= 0 ? getInt(num2.charAt(j)) : 0;
                int sum = x + y + carry;
                sb.append(getChar(sum % 36));
                carry = sum / 36;
                i--;
                j--;
            }
            return sb.reverse().toString();
        }

        /**
         * 将十进制整数转化为 36进制字符
         *
         * @param n
         * @return
         */
        public char getChar(int n) {
            if (n <= 9) {
                return (char) (n + '0');
            } else {
                return (char) (n - 10 + 'a');
            }
        }

        /**
         * 将36 进制字符转化为 10进制整数
         *
         * @param c
         * @return
         */
        public int getInt(char c) {
            if (c <= '9') {
                return c - '0';
            } else {
                return c - 'a' + 10;
            }
        }

    }

    static public String add36Strings(String num1, String num2) {
        if (num1 == null || num2 == null) return num1 != null ? num1 : num2;
        num1 = num1.toLowerCase();num2 = num2.toLowerCase();
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            int x = i >= 0 ? value.indexOf(num1.charAt(i)) : 0;
            int y = j >= 0 ? value.indexOf(num2.charAt(j)) : 0;
            int sum = x + y + carry;
            sb.append(value.charAt(sum % 36));
            carry = sum / 36;
            i--;
            j--;
        }
        return sb.reverse().toString();
    }

    // 不允许把36进制数字整体转为10进制数字，计算出10进制数字的相加结果再转回为36进制
    // https://blog.nowcoder.net/n/4847a38e4b0f426c9c5b6c71ddf3880a
    final static String value = "0123456789abcdefghijklmnopqrstuvwxyz";

    static String f(String num1, String num2) {
        if (num1 == null || num2 == null) return num1 != null ? num1 : num2;
        num1 = num1.toLowerCase();num2 = num2.toLowerCase();
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();
        int i = s1.length - 1;
        int j = s2.length - 1;
        int tmp = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 && j >=0) {
            char c1 = s1[i];
            char c2 = s2[j];
            int sum = value.indexOf(c1) + value.indexOf(c2) + tmp;
            if (sum >= 36) {
                tmp = 1;
                sb.append(value.charAt(sum % 36));
            } else {
                sb.append(value.charAt(sum));
                tmp = 0;
            }
            i--;
            j--;
        }
        //第一个数位数多于第二个数的位数
        while (i >= 0) {
            int sum = value.indexOf(s1[i]) + tmp;
            if (sum >= 36) {
                tmp = 1;
                sb.append(value.charAt(sum % 36));
            } else {
                sb.append(value.charAt(sum));
                tmp = 0;
            }
            i--;
        }
        //第二个数位数多于第一个数的位数
        while (j >= 0) {
            int sum = value.indexOf(s2[j]) + tmp;
            if (sum >= 36) {
                tmp = 1;
                sb.append(value.charAt(sum % 36));
            } else {
                sb.append(value.charAt(sum));
                tmp = 0;
            }
            j--;
        }
        //temp!=0说明有进位，这是需要把进位的1拼接
        if (tmp != 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Add36Strings().new Solution();
        String a = "1b", b = "2x", c;
        c = solution.add36Strings(a, b);
        System.out.println(c);

//        Scanner scan = new Scanner(System.in);
//        String str1 = scan.next();
//        String str2 = scan.next();
        String r = f("1B","2X");
        System.out.println(r);

        String temp = add36Strings("1B","2X");
        System.out.println(temp);
    }

}

