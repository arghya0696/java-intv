package dsa.stack_queue;

import java.util.Stack;

// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

public class RemoveKIdentical {

    public static void main(String[] args) {
        String str = "deeedbbcccbdaa";
        int k = 3;

        System.out.println(removeKIdentical(str, k));
    }

    private static String removeKIdentical(String str, int k) {
        Stack<Holder> st = new Stack<>();

        for (char ch : str.toCharArray()) {
            // If stack is not empty and current char matches the top char
            if (!st.isEmpty() && st.peek().ch == ch) {
                st.peek().val++; // Increment the adjacent streak count

                // If the streak hits 'k', explode it!
                if (st.peek().val == k) {
                    st.pop();
                }
            } else {
                // Otherwise, start a brand new streak
                st.push(new Holder(ch, 1));
            }
        }

        // Reconstruct the final string
        StringBuilder ans = new StringBuilder();
        for (Holder holder : st) {
            int times = holder.val;
            for (int i = 0; i < times; i++) {
                ans.append(holder.ch);
            }
        }

        return ans.toString();
    }

    // Simplified to primitive types for better performance
    public static class Holder {
        char ch;
        int val;

        public Holder(char ch, int val) {
            this.ch = ch;
            this.val = val;
        }
    }
}

/*

Here is a concise description of your solution for the Remove All Adjacent Duplicates in String II problem:

### Approach: Stack with Adjacency Counting

Your solution effectively uses a Stack to track characters and their consecutive counts. This allows you to handle the "snapping together" of the left and right sides seamlessly when a middle substring is deleted.

**1. Tracking Streaks with a Custom Object**

* Instead of just storing characters in the stack, you created a custom `Holder` class that acts as a pair: `(character, count)`.
* This prevents you from having to push the same character $k$ times onto the stack. You just push it once and increment its counter.

**2. Processing the String**

* You iterate through each character in the string from left to right.
* **Match:** If the current character matches the character at the top of the stack, you increment the streak count (`st.peek().val++`).
* **Explosion:** If that streak count reaches exactly `k`, you immediately `pop()` it off the stack. This perfectly mimics the deletion process and instantly exposes the previous character in the stack, allowing future characters to correctly evaluate adjacency.
* **No Match:** If the stack is empty or the current character is different from the top, you push a brand new streak onto the stack: `new Holder(ch, 1)`.

**3. Reconstructing the Result**

* After the loop finishes, the stack only contains the surviving characters and their final frequencies.
* Because Java's `Stack` class extends `Vector`, you can conveniently iterate through it from bottom to top (using a standard for-each loop) and use a `StringBuilder` to append each character `val` times to form the final string.

**Complexity:**

* **Time:** O(N) — You traverse the string exactly once. In the worst case, iterating through the stack to build the final string takes linear time as well.
* **Space:** O(N) — In the worst-case scenario (e.g., all unique characters or no streaks reaching `k`), the stack will store an entry for every character sequence in the string.
* */