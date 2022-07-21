package leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class code_020 {

    public boolean isValid(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        Deque<Character> stack = new LinkedList<Character>();
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c))
                stack.addLast(c);
            else if (stack.isEmpty() || map.get(stack.removeLast()) != c)
                return false;
        }

        return stack.size() == 1;
    }

    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')');
    }};

    public static void main(String[] args) {
        code_020 code = new code_020();
        boolean valid = code.isValid("(){}}{");
        System.out.println(valid);
    }

    // 手写有效括号
    public boolean isValid2(String s) {
        if (s == null) return false;
        int n = s.length();
        if (n % 2 == 1) return false;

        HashMap<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        LinkedList<Character> stack = new LinkedList<>();
        char[] sc = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (pairs.containsKey(sc[i])){
                if (stack.isEmpty() || stack.peek() != pairs.get(sc[i])) return false;
                stack.pop();
            } else {
                stack.push(sc[i]);
            }
        }
        return stack.isEmpty();
    }

}
