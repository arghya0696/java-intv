package dsa.stack_queue;

import java.util.Stack;

// https://neetcode.io/solutions/simplify-path
public class SimplifyPath {

    public static void main(String[] args) {

        String path = "/neetcode/practice//...///../courses";

        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");

        for (String cur : paths) {
            if (cur.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!cur.isEmpty() && !cur.equals(".")) {
                stack.push(cur);
            }
        }

        String ans = "/" + String.join("/", stack);

        System.out.println(ans);
    }
}
