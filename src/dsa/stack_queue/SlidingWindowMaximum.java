package dsa.stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/sliding-window-maximum/description/
public class SlidingWindowMaximum {

    public static void main(String[] args) {

        int[] arr = {1,3,-1,-3,5,3,6,7};

        int k = 3;

        int[] ans  = slidingWindowMaximum(arr, k);
        for(int i : ans)
            System.out.print(i+" ");
    }

    private static int[] slidingWindowMaximum(int[] arr, int k) {
        int n = arr.length;
        int[] ans = new int[n-k+1];
        int p = 0;

        Deque<Integer> dq = new ArrayDeque<>();

        for(int i=0;i<n;i++) {
            // first element in the ( which was either initial or last element pushed ) cannot be less than the range thats why pop
            if(!dq.isEmpty() && dq.peekFirst()<=(i-k))
                dq.removeFirst();

            while (!dq.isEmpty() && arr[dq.peekLast()]<=arr[i]) // remove all small element as we want front element to be max
                dq.removeLast();

            dq.addLast(i); // always add it

            // for the first 0-2 ( 0 - k ) range and so on that why i>=k-1
            if(i >= k-1)
                ans[p++] = arr[dq.peekFirst()];
        }

        return ans;
    }
}

/*
* Here is a concise description of your solution for the Sliding Window Maximum problem.

### Approach: Monotonic Deque (Double-Ended Queue)

Your solution efficiently uses a Deque to store **indices** (not the actual values) in a way that keeps the maximum element of the current window always at the very front. It maintains a strictly decreasing Monotonic Deque.

**1. Evicting Out-of-Bounds Elements**

* As the window slides to the right, the element at the front of the Deque might no longer be part of the current window of size `k`.
* You check this using `dq.peekFirst() <= (i - k)`. If the index at the front is too old, you remove it using `dq.removeFirst()`.

**2. Maintaining the Monotonic Property (The Core Logic)**

* Before adding the current element `arr[i]` to the Deque, you compare it with the elements at the back of the Deque.
* If the current element is greater than or equal to `arr[dq.peekLast()]`, you `removeLast()`.
* *Why?* Because a smaller element that appears *before* a larger element will **never** be the maximum for any current or future window. It is completely overshadowed by the new, larger, and more recent element.

**3. Adding and Recording**

* After clearing out the useless smaller elements, you always add the current index to the back: `dq.addLast(i)`.
* Finally, you check `if (i >= k - 1)`. This ensures you only start recording answers once the first full window of size `k` is actually formed. Because of your cleanup steps, the absolute maximum for the current window is guaranteed to be sitting right at the front: `arr[dq.peekFirst()]`.

**Complexity:**

* **Time:** $O(N)$ - Even with the inner `while` loop, every index from the array is added to the Deque exactly once and removed at most once. The amortized time is strictly linear.
* **Space:** $O(K)$ - The Deque will store at most `k` indices at any given time (if the elements are strictly decreasing). The answer array requires $O(N - K + 1)$ space, but auxiliary space is just $O(K)$.
* */
