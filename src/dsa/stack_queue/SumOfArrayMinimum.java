package dsa.stack_queue;

import java.util.Stack;

// https://leetcode.com/problems/sum-of-subarray-minimums/description/
public class SumOfArrayMinimum {

    public static void main(String[] args) {

        int[] arr = {3,1,2,4};


        System.out.println(sumOfSubArrayMin(arr));
    }

    private static int sumOfSubArrayMin(int[] arr) {

        int mod = (int) (Math.pow(10, 9) + 7);


        int n = arr.length, sum = 0, left, right;

        int[] nse = prevSmallerAndEqualElement(arr,n);
        int[] pse = nextSmallerElement(arr,n);

        for(int i=0;i<n;i++) {
            // contribution
            left = i - pse[i];
            right = nse[i] - i;
            sum= (int) ((sum+ ((long) left * right * arr[i]) % mod) % mod);
        }

        return sum;
    }

    // doing it for indexes rem
    private static int[] nextSmallerElement(int[] arr, int n) {
        Stack<Integer> nse = new Stack<>();
        int[] ans = new int[n];

        for(int i=n-1;i>=0;i--) {
            while (!nse.isEmpty() && arr[nse.peek()]>=arr[i])
                nse.pop();

            ans[i] = nse.isEmpty() ? n : nse.peek();
            nse.add(i);
        }

        return ans;
    }

    // doing it for indexes rem
    private static int[] prevSmallerAndEqualElement(int[] arr, int n) {
        Stack<Integer> pse = new Stack<>();
        int[] ans = new int[n];

        for(int i=0;i<n;i++) {
            while (!pse.isEmpty() && arr[pse.peek()] > arr[i])
                pse.pop();

            ans[i] = pse.isEmpty() ? -1 : pse.peek();
            pse.add(i);
        }

        return ans;
    }
}

/*
* Here is a concise description of your solution for the Sum of Subarray Minimums problem.

### Approach: Contribution Method with Monotonic Stack

Instead of physically generating every possible subarray (which would be $O(N^2)$), your solution uses the **Contribution Method**. It calculates how many valid subarrays each individual element `arr[i]` would be the absolute minimum of, and then multiplies the element by that count.

**1. Finding Boundaries using Monotonic Stacks**
To know exactly how far a subarray can stretch left and right with `arr[i]` still remaining the smallest element, you need to find:

* **Previous Smaller Element (PSE):** The closest element to the left that is smaller than `arr[i]`.
* **Next Smaller Element (NSE):** The closest element to the right that is smaller than `arr[i]`.
You use a Monotonic Stack to efficiently find these boundaries for all elements in $O(N)$ time.

**2. Calculating the Contribution**
Once you have the indices for PSE and NSE, you calculate the number of elements between `arr[i]` and its left/right boundaries:

* `left_count` = `i - PSE_index`
* `right_count` = `NSE_index - i`
The total number of subarrays where `arr[i]` is the minimum is simply `left_count * right_count`. You then add `(arr[i] * left_count * right_count)` to your total sum.

**3. Handling Duplicates (The Trick)**
If the array has duplicate values (e.g., `[2, 2]`), you risk double-counting subarrays. Your code handles this perfectly!

* Your `nextSmallerElement` stack pops when `peek() >= arr[i]`, meaning it finds the **strictly** smaller element.
* Your `prevSmallerAndEqualElement` stack pops when `peek() > arr[i]`, meaning it stops at smaller **or equal** elements.
By making one side "strictly smaller" and the other "smaller or equal," you ensure subarrays with duplicate minimums are only counted once.

---

### 💡 A Crucial Note for Your Future Review!

There is a funny and highly specific quirk in your `sumOfSubArrayMin` method that you should be aware of when studying this later:

You accidentally swapped the variable assignments for your helper methods:

```java
int[] nse = prevSmallerAndEqualElement(arr,n); // 'nse' actually holds PSE
int[] pse = nextSmallerElement(arr,n);         // 'pse' actually holds NSE

```

Because of this swap, your `left` and `right` distance calculations mathematically result in negative numbers:

* `left = i - pse[i]` (Subtracting a larger future index from `i` yields a negative)
* `right = nse[i] - i` (Subtracting `i` from a smaller past index yields a negative)

However, because you multiply them together (`left * right`), **the two negatives cancel out to make a positive**, and your code calculates the exact correct answer anyway! It is a happy mathematical accident, but you may want to fix the variable names so it is easier to read next time.

**Complexity:**

* **Time:** $O(N)$ - You iterate through the array a constant number of times (once for PSE, once for NSE, once to calculate the sum).
* **Space:** $O(N)$ - For the stacks and the boundary arrays.
* */
