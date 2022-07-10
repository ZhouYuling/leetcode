package offer;

public class offer_46 {

    class Solution {
        // 用f(i)表示以第i位结尾的前缀串翻译的方案数，考虑第i位单独翻译和与前一位连接起来再翻译对f(i)的贡献
        // f(i) = f(i - 1) + f(i - 2)，由于只和三个指标有关，那么使用滚动数组就可以了
        public int translateNum(int num) {
            String src = String.valueOf(num);
            //p是第一个变量、q是第二个、r是第三个
            int p = 0, q = 0, r = 1;
            for (int i = 0; i < src.length(); ++i) {
                p = q;
                q = r;
                r = 0;
                r += q;
                if (i == 0) {
                    continue;
                }
                //String.substring包左包右，所以以下为i-1到i的下标
                String pre = src.substring(i - 1, i + 1);
                if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                    r += p;
                }
            }
            return r;
        }
    }

}
