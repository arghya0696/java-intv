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

/*
* Here is a concise description of your solution for the Decode String problem to add to your study notes!

### Approach: Single Stack for Inner-to-Outer Resolution

Your solution uses a single `Stack<Character>` to naturally handle the nested structure of the encoded string. By pushing everything and only reacting when you see a closing bracket `]`, you ensure that the innermost brackets are always resolved first.

**1. Building the Context**

* You iterate through the string and push every character, digit, and opening bracket `[` directly onto the stack.
* You do absolutely nothing else until you encounter a closing bracket `]`.

**2. Resolving a Bracket (The `]` Trigger)**

* Hitting a `]` means you have reached the end of an encoded sequence. Now, you work backward.
* **Extract the String:** You pop characters off the stack until you hit the `[`. Since a stack is LIFO (Last-In-First-Out), popping reverses the order. You cleverly handle this by using `temp.insert(0, popped)` to reconstruct the string in its correct original order.
* **Pop the `[`:** You pop one more time to discard the opening bracket.
* **Extract the Multiplier:** The characters immediately before the `[` are guaranteed to be digits. You pop them while they are digits, again using `insert(0, ...)` to handle multi-digit numbers (like "12" or "300") correctly.

**3. Re-pushing to the Stack (The Magic Step)**

* Once you have the `num` and the `temp` string, you multiply the string and push every single character of it **back onto the stack**.
* *Why is this brilliant?* By pushing the decoded substring back, it seamlessly becomes part of the outer bracket's context. For example, in `3[a2[c]]`, the `2[c]` is resolved to `cc` and pushed back. The stack now looks exactly as if the input was `3[acc]`, allowing the outer multiplier to process it effortlessly.

**4. Final Output**

* Once the loop is finished, all brackets have been resolved. The stack contains only the final decoded characters, which you stream and join into the final string.

---

### 💡 A Quick Optimization Tip for Your Future Review!

Your logic is completely correct, but there is a small performance bottleneck to be aware of: `StringBuilder.insert(0, ...)` is an $O(K)$ operation because it has to shift all existing characters to the right every single time you insert.

When pulling characters off the stack, a much faster approach is to use standard `append(...)` (which is $O(1)$) and then simply call `.reverse()` on the `StringBuilder` once at the end before using it.

**Complexity:**

* **Time:** O(Maximum Output Length) — You push and pop characters multiple times based on the multipliers. In the worst case (e.g., massive multipliers), the time scales linearly with the size of the final decoded string.
* **Space:** O(Maximum Output Length) — Because you push the fully expanded substrings back onto the stack, the stack will eventually hold the entire decoded string in memory before joining it.
* */
