package dsa.stack_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/
public class MinRemoveToMakeParanthesisValid {

    public static void main(String[] args) {

        System.out.println(minRemoveToMakeValid("())()(("));
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
    }

    public static String minRemoveToMakeValid(String s) {

        List<Character> result = new ArrayList<>();

        int count = 0;

        for (Character ch : s.toCharArray()) {
            if(ch.equals('(')) {
                count++;
                result.add(ch);
            } else if (ch.equals(')')) {
                if(count >0) {
                    count--;
                    result.add(ch);
                }
            }
            else
                result.add(ch);
        }

        // for usecase lik : ))((
        result = result.reversed();

        List<Character> filtered = new ArrayList<>();

        for(Character ch : result) {
            if(ch.equals('(') && count>0)
                count--;
            else
                filtered.add(ch);
        }

        return filtered.reversed().stream().map(String::valueOf).collect(Collectors.joining());
    }
}

/*
* Here is a concise description of your solution for future revision:

### Approach: Two-Pass Counting (Left-to-Right, then Right-to-Left)

Your solution uses a clever two-pass approach with a counter to identify and remove unmatched parentheses without needing a Stack.

**1. First Pass (Left-to-Right) - Remove invalid `)**`

* Iterate through the string and use a `count` to keep track of unmatched open `(` parentheses.
* Append letters and `(` to a list (incrementing `count` for `(`).
* When you encounter a `)`, only append it if `count > 0` (meaning there is a matching open parenthesis available), and decrement the `count`.
* *Result:* This guarantees all closing `)` parentheses are valid, but you might still have too many open `(` parentheses (indicated by a `count` greater than 0).

**2. Second Pass (Right-to-Left) - Remove invalid `(**`

* Reverse the list to easily process it from right to left.
* The remaining `count` represents the exact number of unmatched `(` at the end of your string that need to be removed.
* Iterate through the reversed list. Whenever you see a `(` and `count > 0`, skip adding it and decrement `count`. Otherwise, keep the character.
* *Result:* This removes the excess `(` parentheses starting from the rightmost side.

**3. Final Step**

* Reverse the list one last time to restore the original order and join the characters into the final valid string.

**Complexity:**

* **Time:** O(N) - You do a few linear scans/reversals of the characters.
* **Space:** O(N) - To store the characters in the `ArrayList`.
*
* */
