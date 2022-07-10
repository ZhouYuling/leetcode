package leetcode;

import java.util.ArrayList;
import java.util.List;

public class code_022 {

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0,combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result){
        if (pos == current.length){
            if (valid(current)){
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {

        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                ++ balance;
            }
            if (c == ')') {
                -- balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    public static void main(String[] args) {
        code_022 code = new code_022();
        List<String> str = code.generateParenthesis(3);
        System.out.println(str);
    }


    public List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max){

        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }

    }

}
