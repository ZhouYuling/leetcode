package offer;

import java.util.Arrays;

public class offer_17 {

    static class Solution {
        StringBuilder res;
        int count = 0, n;
        char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        public String printNumbers(int n) {
            this.n = n;
            res = new StringBuilder(); // 数字字符串集
            num = new char[n]; // 定义长度为 n 的字符列表
            dfs(0); // 开启全排列递归
            res.deleteCharAt(res.length() - 1); // 删除最后多余的逗号
            return res.toString(); // 转化为字符串并返回
        }
        void dfs(int x) {
            if(x == n) { // 终止条件：已固定完所有位
                res.append(String.valueOf(num) + ","); // 拼接 num 并添加至 res 尾部，使用逗号隔开
                return;
            }
            for(char i : loop) { // 遍历 ‘0‘ - ’9‘
                num[x] = i; // 固定第 x 位为 i
                dfs(x + 1); // 开启固定第 x + 1 位
            }
        }
    }

    static class Solution2 {
        int[] res;
        int nine = 0,count = 0, start, n;
        char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        public int[] printNumbers(int n) {
            this.n = n;
            res = new int[(int)Math.pow(10, n) - 1]; // 返回数组
            num = new char[n]; // 定义长度为 n 的字符列表
            start = n - 1;
            dfs(0); // 开启全排列递归
            return res; // 转化为字符串并返回
        }
        void dfs(int x) {
            if(x == n) { // 终止条件：已固定完所有位
                String s = String.valueOf(num).substring(start);
                if(!s.equals("0")) res[count++] = Integer.parseInt(s); // 拼接 num 并添加至 res 尾部，使用逗号隔开
                if (n - start == nine) start --; // 得到通知，需要截取长度+1
                return;
            }
            for(char i : loop) { // 遍历 ‘0‘ - ’9‘
                if (i == '9') nine ++;
                num[x] = i; // 固定第 x 位为 i
                dfs(x + 1); // 开启固定第 x + 1 位
            }
            nine --; // 不是9就不用加1了
        }
    }

    public static void main(String[] args) {

        Solution2 solution = new Solution2();
        int[] s = solution.printNumbers(3);
        System.out.println(Arrays.toString(s));


    }

    // 手写，考虑大数定律
    static class Solution3 {

        int[] res;
        int n,start,nine,count = 0;
        char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        public int[] printNumbers(int n) {
            this.n = n;
            res = new int[(int)Math.pow(10, n) - 1];
            num = new char[n];
            start = n - 1;
            dfs(0);
            return res;
        }

        void dfs(int x){
            if (x == n) {
                String s = String.valueOf(num).substring(start);
                if (!"0".equals(s)) res[count ++] = Integer.parseInt(s);
                if (n - start == nine) start --;
                return;
            }
            for (char i : loop) {
                if (i == '9') nine ++;
                num[x] = i;
                dfs(x + 1);
            }
            nine --;
        }
    }

}
