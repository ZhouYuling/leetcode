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

}
