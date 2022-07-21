package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class code_232 {

    class MyQueue {

        Deque<Integer> inStack;
        Deque<Integer> outStack;

        public MyQueue() {
            inStack = new LinkedList<>();
            outStack = new LinkedList<>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            if (outStack.isEmpty()) in2out();
            return outStack.pop();
        }

        public int peek() {
            if (outStack.isEmpty()) in2out();
            return outStack.peek();
        }

        public boolean empty() {
            return outStack.isEmpty() && inStack.isEmpty();
        }


        private void in2out() {
            while (!inStack.isEmpty()) outStack.push(inStack.pop());
        }
    }

}
