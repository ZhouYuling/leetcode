package leetcode;

import java.util.ArrayList;
import java.util.List;

public class code_784 {

    public class Solution {

        public List<String> letterCasePermutation(String S) {
            List<String> res = new ArrayList<>();
            char[] charArray = S.toCharArray();
            dfs(charArray, 0, res);
            return res;
        }

        private void dfs(char[] charArray, int index, List<String> res) {
            if (index == charArray.length) {
                res.add(new String(charArray));
                return;
            }

            dfs(charArray, index + 1, res);
            if (Character.isLetter(charArray[index])) {
                // 大写变小写，小写变大写，这个地方非常巧妙
                charArray[index] ^= 1 << 5;
                dfs(charArray, index + 1, res);
            }
        }
    }


}
