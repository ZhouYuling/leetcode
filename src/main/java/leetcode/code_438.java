package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class code_438 {

    static class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            char[] arrS = s.toCharArray();
            char[] arrP = p.toCharArray();

            // 接收最后返回的结果
            List<Integer> ans = new ArrayList<>();

            // 定义一个 needs 数组来看 arrP 中包含元素的个数
            int[] needs = new int[26];
            // 定义一个 window 数组来看滑动窗口中是否有 arrP 中的元素，并记录出现的个数
            int[] window = new int[26];

            // 先将 arrP 中的元素保存到 needs 数组中
            for (int i = 0; i < arrP.length; i++) {
                needs[arrP[i] - 'a'] += 1;
            }

            // 定义滑动窗口的两端
            int left = 0;
            int right = 0;

            // 右窗口开始不断向右移动
            while (right < arrS.length) {
                int curR = arrS[right] - 'a';
                right++;
                // 将右窗口当前访问到的元素 curR 个数加 1
                window[curR] += 1;

                // 当 window 数组中 curR 比 needs 数组中对应元素的个数要多的时候就该移动左窗口指针
                while (window[curR] > needs[curR]) {
                    int curL = arrS[left] - 'a';
                    left++;
                    // 将左窗口当前访问到的元素 curL 个数减 1
                    window[curL] -= 1;
                }

                // 这里将所有符合要求的左窗口索引放入到了接收结果的 List 中
                if (right - left == arrP.length) {
                    ans.add(left);
                }
            }
            return ans;
        }
    }

    //滑动窗口
    static class Solution1 {
        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length(), pLen = p.length();

            //字串比母串长，则肯定没有异位词
            if (sLen < pLen) {
                return new ArrayList<Integer>();
            }

            List<Integer> ans = new ArrayList<Integer>();
            int[] sCount = new int[26];
            int[] pCount = new int[26];
            for (int i = 0; i < pLen; ++i) {
                ++sCount[s.charAt(i) - 'a'];
                ++pCount[p.charAt(i) - 'a'];
            }

            if (Arrays.equals(sCount, pCount)) {
                ans.add(0);
            }

            //开始让窗口进行滑动
            for (int i = 0; i < sLen - pLen; ++i) { //i是滑动前的首位
                --sCount[s.charAt(i) - 'a']; //将滑动前首位的词频删去
                ++sCount[s.charAt(i + pLen) - 'a']; //增加滑动后最后为的词频

                if (Arrays.equals(sCount, pCount)) {
                    ans.add(i + 1);
                }
            }

            return ans;
        }
    }


    //优化后的滑动窗口
    static class Solution3 {
        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length(), pLen = p.length();

            if (sLen < pLen) {
                return new ArrayList<Integer>();
            }

            List<Integer> ans = new ArrayList<Integer>();
            int[] count = new int[26];
            for (int i = 0; i < pLen; ++i) {
                ++count[s.charAt(i) - 'a'];
                --count[p.charAt(i) - 'a'];
            }

            int differ = 0;
            for (int j = 0; j < 26; ++j) {
                if (count[j] != 0) {
                    ++differ;
                }
            }

            if (differ == 0) {
                ans.add(0);
            }

            for (int i = 0; i < sLen - pLen; ++i) {
                char c = s.charAt(i);
                if (count[c - 'a'] == 1) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从不同变得相同
                    --differ;
                } else if (count[c - 'a'] == 0) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从相同变得不同
                    ++differ;
                }
                --count[c - 'a'];

                char c1 = s.charAt(i + pLen);
                if (count[c1 - 'a'] == -1) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从不同变得相同
                    --differ;
                } else if (count[c1 - 'a'] == 0) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从相同变得不同
                    ++differ;
                }
                ++count[c1 - 'a'];

                if (differ == 0) {
                    ans.add(i + 1);
                }
            }

            return ans;
        }
    }


    public static void main(String[] args) {

        Solution solution = new Solution();
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> anagrams = solution.findAnagrams(s, p);
        System.out.println(anagrams);


        Solution1 solution1 = new Solution1();
        System.out.println(solution1.findAnagrams(s, p));

        List<Integer> anagrams1 = new Solution3().findAnagrams("cbaaaebabacd", "aaa");
        System.out.println(anagrams1);

    }

}
