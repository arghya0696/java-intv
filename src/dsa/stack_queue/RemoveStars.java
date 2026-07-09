package dsa.stack_queue;

import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

//https://leetcode.com/problems/removing-stars-from-a-string/description/
public class RemoveStars {

    public static void main(String[] args) {

        String str = "erase*****";

        System.out.println(removeStars(str));
    }

    private static String removeStars(String str) {

        Stack<Character> stack = new Stack<>();

        for(Character ch : str.toCharArray()) {
            if(!stack.isEmpty() && ch.equals('*')) {
                stack.pop();
                continue;
            }
            stack.push(ch);
        }

        return stack.stream().map(Objects::toString).collect(Collectors.joining());
    }
}
