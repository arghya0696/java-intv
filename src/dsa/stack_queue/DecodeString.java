package dsa.stack_queue;

import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

// https://leetcode.com/problems/decode-string/description/
public class DecodeString {

    public static void main(String[] args) {

        String str = "3[ab]2[bc2[ef]]";

        System.out.println(decodeString(str));
    }

    private static String decodeString(String str) {

        Stack<Character> stack = new Stack<>();

        for(Character ch : str.toCharArray()) {
            if(']' != ch) {
                stack.push(ch);
            } else {
                StringBuilder temp = new StringBuilder();

                while (!stack.isEmpty() && '['!= stack.peek()) {
                    Character popped = stack.pop();

                    temp.insert(0, popped.toString());
                }

                stack.pop();
                StringBuilder numString = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    numString.insert(0, stack.pop());
                }

                int num = Integer.parseInt(numString.toString());
                for (int i=0; i< num; i++) {
                    for(int j=0;j<temp.length();j++) {
                        stack.push(temp.charAt(j));
                    }
                }
            }
        }

        return stack.stream().map(Objects::toString).collect(Collectors.joining());

    }
}
