package offer;

import java.util.LinkedHashMap;
import java.util.Map;

public class offer_50 {

    static class Solution {
        public char firstUniqChar(String s) {
            Map<Character, Boolean> dic = new LinkedHashMap<>();
            char[] sc = s.toCharArray();
            for(char c : sc)
                dic.put(c, !dic.containsKey(c));
            for(Map.Entry<Character, Boolean> d : dic.entrySet()){
                if(d.getValue()) return d.getKey();
            }
            return ' ';
        }
    }

    public static void main(String[] args) {


        Solution solution = new Solution();


    }

}
