package dsa.stack_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

// https://www.geeksforgeeks.org/problems/next-greater-element/1
public class NextGreaterElementTwoCircular {
    public static void main(String[] args) {

        int[] arr = {0, 2, 3, 1, 1}; // 0, 2, 3, 1, 1, 0, 2, 3, 1, 1

        ArrayList<Integer> ans = ngeSolTwo(arr);

        for (int i : ans) {
            System.out.print(i+" ");
        }
    }

    private static ArrayList<Integer> ngeSolTwo(int[] arr) {

        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int[] ans = new int[arr.length];

        for (int i = 2*n -1; i>=0; i--) {
            while (!st.isEmpty() && st.peek()<= arr[i%n]) {
                st.pop();
            }

            if(i<n) {
                ans[i] = st.isEmpty() ? -1 : st.peek();
            }

            st.push(arr[i%n]);
        }


        return Arrays.stream(ans)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

/*
* Your solution uses a Monotonic Stack and handles the circular array requirement by virtually doubling the array size. It traverses this simulated array from right to left.

1. Simulated Circular Traversal

Instead of physically duplicating the array to handle wrap-around, you iterate from 2 * n - 1 down to 0.

You use the modulo operator (i % n) to continuously map the virtual indices back to the actual array elements.

2. Maintaining the Monotonic Stack

The stack keeps track of potential "next greater" candidates.

As you iterate backwards, you check the top of the stack. If the stack's top element is less than or equal to the current element (arr[i % n]), you pop() it.

Why? Because any element further to the left will encounter the current (larger) element before it ever reaches the popped (smaller) elements, making the popped elements useless.

3. Recording the Answer

You only save the results when you cross back into the original array's index bounds (when i < n).

Because you started at 2 * n - 1, by the time you reach i < n, the stack is already pre-loaded with the elements from the "right" side of the circular array.

The Next Greater Element is the one currently at the top of the stack. If the stack is empty, it means no greater element exists, so you record -1.

Lastly, you push the current element onto the stack so it can be evaluated for elements further to the left.

Complexity:

Time: O(N) - Although there is a while loop nested inside the for loop, every element is pushed and popped from the stack at most once, resulting in an amortized linear time complexity.

Space: O(N) - The maximum size of the stack and the size of the answer array are proportional to the length of the input array.
* */