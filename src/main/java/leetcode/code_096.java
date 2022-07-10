package leetcode;

public class code_096 {

    // G(n): 长度为 n 的序列能构成的不同二叉搜索树的个数。
    // F(i,n): 以 i 为根、序列长度为 n 的不同二叉搜索树个数 (1≤i≤n)。
    class Solution {
        public int numTrees(int n) {
            int[] G = new int[n + 1];
            G[0] = 1;
            G[1] = 1;

            // 以i作为根节点
            for (int i = 2; i <= n; ++i) {
                for (int j = 1; j <= i; ++j) {
                    G[i] += G[j - 1] * G[i - j];
                }
            }
            return G[n];
        }
    }

}
