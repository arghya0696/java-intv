package dsa.stack_queue;

import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args) {

        System.out.println(isValid("(({}()[])"));

        StringBuilder str = new StringBuilder("test");

        str.replace(1, 2, "");

        System.out.println(str);

    }

    public static boolean isValid(String s) {

        Stack<Character> st = new Stack<>();

        for(int i=0;i<s.length();i++) {
            Character ch = s.charAt(i);
            Character top = st.isEmpty() ? '_' : st.peek();
            if(checkEqual(ch, top)) {
                st.pop();
            }
            else
                st.add(ch);
        }
        return st.isEmpty();
    }

    private static boolean checkEqual(Character ch, Character top) {
        if(top.equals('(') && ch.equals(')'))
            return true;
        else if(top.equals('{') && ch.equals('}'))
            return true;
        else return top.equals('[') && ch.equals(']');
    }
}
