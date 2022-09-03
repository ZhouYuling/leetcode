package leetcode;

import interview.exam;

import java.util.Arrays;
import java.util.List;

public class code_524 {

    // 考试自己手写的代码，没有考虑s的顺序不可以改变
    public String findLongestWord3(String s, List<String> dictionary) {
        int[] sChr = new int[26];
        for (char c : s.toCharArray()) {
            sChr[c - 'a'] ++;
        }
        String longestWord = "";
        int longestLength = Integer.MIN_VALUE;
        for(String word : dictionary) {
            int[] temp = Arrays.copyOf(sChr, sChr.length);
            boolean flag = true;
            for(char c : word.toCharArray()) {
                int i = c - 'a';
//                System.out.println(Arrays.toString(temp));
                temp[i] --;
//                System.out.println(Arrays.toString(temp));
                if (temp[i] < 0) {
                    flag = false;
                    break;
                };
            }
            if (flag && longestLength <= word.length()) {
                if (longestLength == word.length() && longestWord.compareTo(word) < 0) continue;
                longestWord = word;
                longestLength = word.length();
            }
        }
        return longestWord;
    }

    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for(String word : dictionary) {
            int i = 0, j = 0;
            while (j < s.length() && i < word.length()) {
                if (s.charAt(j) == word.charAt(i)) {
                    i ++;
                }
                j ++;
            }
            if (i == word.length() && (res.length() < word.length() || (res.length() == word.length() && res.compareTo(word) > 0))) {
                res = word;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        String[] dict = new String[]{"ale","apple","monkey","plea"};
        code_524 exam = new code_524();
        String abpcplea = exam.findLongestWord("abpcplea", Arrays.asList(dict));
        System.out.println(abpcplea);

        String abpcplea2 = exam.findLongestWord("abce", Arrays.asList("abe","abc"));
        System.out.println(abpcplea2);

        code_524 code = new code_524();
        String abpcplea3 = code.findLongestWord("abpcplea", Arrays.asList("ale","apple","monkey","plea"));
        System.out.println(abpcplea3);

    }

}
