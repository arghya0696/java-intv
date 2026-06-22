package dsa.stack_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/online-stock-span/description/
public class StockSpan {

    public static void main(String[] args) {

        int[] arr  = {29,91,62,76,51};

        StockSpanner stockSpanner = new StockSpanner();

        for(int i: arr) {
            System.out.println("span:: "+ stockSpanner.next(i));
        }

    }

}

class StockSpanner {

    List<Integer> list = new ArrayList<>();
    Stack<Holder> stack = new Stack<>();

    int currentIndex = 0;
    public StockSpanner() {

    }

    public int next(int price) {
        // 1. Pop all previous smaller or equal elements
        while (!stack.isEmpty() && stack.peek().val() <= price) {
            stack.pop();
        }

        // 2. Calculate the span
        // If stack is empty, it means this is a new all-time high,
        // so span is all days up to now (currentIndex + 1)
        int ans = stack.isEmpty() ? currentIndex + 1 : currentIndex - stack.peek().index();

        // 3. Push current element and its index
        stack.push(new Holder(price, currentIndex));

        // 4. Increment index for the next day's call
        currentIndex++;

        return ans;
    }

    record Holder(int val, int index){}
}

/*
* Here is the concise description for your newly optimized solution to the Online Stock Span problem. You can save this version for your future review!

### Approach: Optimized Monotonic Stack (0-Based Indexing)

This solution refines the Previous Greater Element (PGE) approach by dropping unnecessary data structures and edge-case checks. By maintaining a single `currentIndex` state and storing the value directly in the stack, the code becomes much leaner.

**1. Streamlined State Tracking**

* Instead of keeping a `List` of all historical prices, you only maintain a global `currentIndex` integer (starting at `0`) and the `Stack<Holder>`.
* The `Holder` record neatly packages both the price `val` and its `index` together.

**2. Maintaining the Monotonic Stack**

* When `next(price)` is called, you immediately look at the top of the stack.
* You use a `while` loop to `pop()` any past prices that are less than or equal to today's `price`.
* *Why?* Because today's price is both higher and more recent, so any smaller past prices are entirely obsolete for calculating future spans. By using `stack.peek().val()`, you compare values directly without needing array/list lookups.

**3. Calculating the Span**

* **If the stack is empty:** Every price we've seen so far was popped. This means today is a new all-time high! The span is `currentIndex + 1` (to account for 0-based indexing).
* **If the stack is not empty:** The top element is exactly the Previous Greater Element. The span is simply the distance between today and that PGE: `currentIndex - stack.peek().index()`.

**4. Update and Advance**

* Finally, you push today's `new Holder(price, currentIndex)` onto the stack.
* You increment `currentIndex++` at the very end of the method so the next incoming price gets the correct next day's index. This careful ordering completely eliminates the need for an `if(first_element)` edge case!

**Complexity:**

* **Time:** Amortized O(1) per `next()` call. Each price is pushed onto the stack exactly once and popped at most once.
* **Space:** O(N) in the worst case (e.g., if prices strictly decrease every day, like `[100, 90, 80]`, every element stays on the stack). However, it uses strictly less memory than the previous approach because the `List<Integer>` was removed.
* */
