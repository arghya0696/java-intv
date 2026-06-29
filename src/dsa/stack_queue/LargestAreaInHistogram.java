package dsa.stack_queue;

import java.util.Stack;

// https://leetcode.com/problems/largest-rectangle-in-histogram/description/
public class LargestAreaInHistogram {

    public static void main(String[] args) {

        int[] arr = {2,1,5,6,2,3};

        System.out.println(largestAreaInHistogram(arr));
    }

    private static int largestAreaInHistogram(int[] arr) {

        int[] pse = getPrevSmallerElement(arr);
        int[] nse = getNextSmallerElement(arr);

        int max = Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++) {
            int area = (nse[i] - pse[i] - 1) * arr[i];
            max = Math.max(max, area);
        }

        return max;
    }

    private static int[] getPrevSmallerElement(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();

        for(int i=0;i<arr.length;i++) {
            while (!st.isEmpty() && arr[st.peek()]>=arr[i])
                st.pop();

            ans[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return ans;
    }

    private static int[] getNextSmallerElement(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();

        for(int i=arr.length-1;i>=0;i--) {
            while (!st.isEmpty() && arr[st.peek()]>=arr[i])
                st.pop();

            ans[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }
        return ans;
    }
}

/*
* Here is a concise description of your solution for the Largest Rectangle in Histogram problem. You can save this for your future study notes!

### Approach: Boundary Finding using Monotonic Stacks

Your solution is based on a core realization: **For any given bar treated as the full height of a rectangle, how far left and right can that rectangle expand?** It can only expand until it hits a bar that is *shorter* than itself.

**1. Finding Boundaries (PSE and NSE)**
To calculate the maximum width for each bar, you need to find the indices of the first strictly smaller bars to its left and right:

* **Previous Smaller Element (PSE):** The closest bar to the left that is shorter. If none exists, the boundary naturally extends to the very beginning (index `-1`).
* **Next Smaller Element (NSE):** The closest bar to the right that is shorter. If none exists, the boundary naturally extends to the very end (index `arr.length`).

**2. Utilizing Monotonic Stacks**
You use two separate helper methods to find these boundaries efficiently.

* Both methods use a strictly increasing Monotonic Stack (storing indices) to find the smaller elements in $O(N)$ time.
* *Smart trick:* By defaulting empty stack evaluations to `-1` for PSE and `arr.length` for NSE, you ensure the width calculation math works flawlessly even for bars that stretch all the way to the edges.

**3. Calculating Maximum Area**
Once you have the arrays containing the PSE and NSE indices, you iterate through the histogram one last time.

* For each bar at index `i`, you calculate the maximum valid width it can span using the formula: `nse[i] - pse[i] - 1`.
* You multiply this width by the height of the current bar `arr[i]` to get the area.
* You keep track of the absolute `max` area across all bars.

**Complexity:**

* **Time:** $O(N)$ — You make three separate linear passes over the array (one for PSE, one for NSE, one for the area calculation). Since elements are pushed and popped from the stack at most once, it runs in linear time.
* **Space:** $O(N)$ — You use three auxiliary arrays (`pse`, `nse`, and the stack) which scale linearly with the size of the input.
* */
