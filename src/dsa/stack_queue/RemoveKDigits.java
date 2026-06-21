package dsa.stack_queue;

import java.util.Stack;
import java.util.stream.Collectors;

// https://leetcode.com/problems/remove-k-digits/description/
public class RemoveKDigits {

    public static void main(String[] args) {

        String num = "33526221184202197273";
        int k = 19;

        System.out.println(minNumAfterKRemoval(num, k));

    }

    private static String minNumAfterKRemoval(String num, int k) {

        Stack<Character> st = new Stack<>();
        StringBuilder ans;

        if(k == num.length())
            return "0";

        // keep on removing k bigger digits to form small num
        for(Character ch : num.toCharArray()) {
            int val = ch - '0';
            while(!st.isEmpty() && val< st.peek() - '0' && k>0) {
                st.pop();
                k--;
            }
            st.push(ch);
        }

        ans = new StringBuilder(st.stream().map(String::valueOf).collect(Collectors.joining()));

        // could be a situation where k is still there , then remove last k digits from ans
        while (k>0) {
            ans.deleteCharAt(ans.length()-1);
            k--;
        }

        // strip initial zero to form valid num : eg-> 0100 --> 100
        while (ans.length() > 1 && ans.charAt(0) == '0') {
            ans.deleteCharAt(0);
        }

        return ans.toString();
    }
}

