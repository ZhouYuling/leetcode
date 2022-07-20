package offer;

import java.util.Stack;

public class offer_33 {

    // 逆后续遍历+单调栈
    class Solution {
        public boolean verifyPostorder(int[] postorder) {
            Stack<Integer> stack = new Stack<>();
            int root = Integer.MAX_VALUE;
            for(int i = postorder.length - 1; i >= 0; i--) {
                if(postorder[i] > root) return false;
                // 单调递增栈
                while(!stack.isEmpty() && stack.peek() > postorder[i])
                    root = stack.pop();
                stack.add(postorder[i]);
            }
            return true;
        }
    }

    class Solution2 {
        public boolean verifyPostorder(int[] postorder) {
            return recur(postorder, 0, postorder.length - 1);
        }
        boolean recur(int[] postorder, int i, int j) {
            if (i >= j) return true;
            int p = i;
            while (postorder[p] < postorder[j]) p ++;
            int m = p;
            // 以下两种写法不一样，注意一下
            // while (postorder[p ++] > postorder[j]);
            while (postorder[p] > postorder[j]) p ++;
            return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1); // m == j,那么会导致下一层i < j,最终小于0
        }
    }


    public static void main(String[] args) {

        offer_33 code = new offer_33();
        Solution solution = code.new Solution();
        int[] postorder = {1, 3, 2, 6, 5};
        System.out.println(solution.verifyPostorder(postorder));

        postorder = new int[]{1,2,5,10,6,9,4,3};
        Solution2 solution2 = code.new Solution2();
        System.out.println(solution2.verifyPostorder(postorder));

    }


}
