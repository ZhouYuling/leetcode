package leetcode;

public class code_1768 {

    // 双指针
    class Solution {
        public String mergeAlternately(String word1, String word2) {
            int len1=word1.length();
            int len2=word2.length();
            char[]res=new char[len1+len2];
            int idx=0;
            for(int i=0;i<len1||i<len2;i++){

                if(i<len1)     res[idx++]=word1.charAt(i);
                if(i<len2)     res[idx++]=word2.charAt(i);

            }
            return new String(res);

        }
    }


}
