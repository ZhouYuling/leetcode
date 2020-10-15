package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class code_1002 {

    static class Solution {
        public List<String> commonChars(String[] A) {
            int[] hash = new int[26];
            int[] temp = new int[26];
            for(int i = 0; i < 26; i ++)
                hash[i] = 100;

            for(int i = 0; i < A.length; i ++){
                for(char c : A[i].toCharArray())
                    temp[c - 'a'] ++;
                for(int j = 0; j < 26; j++){
                    hash[j] = Math.min(hash[j], temp[j]);
                    temp[j] = 0;
                }
            }
            List<String> list = new LinkedList<String>();
            for(int i = 0;i < 26; i ++){
                while(hash[i] > 0){
                    list.add(String.valueOf((char)(i + 'a')));
                    hash[i]--;
                }
            }

            return list;
        }
    }

    public static void main(String[] args) {

        String[] words = {"bella","label","roller"};
        Solution solution = new Solution();
        List<String> strings = solution.commonChars(words);
        System.out.println(strings);

    }


}
