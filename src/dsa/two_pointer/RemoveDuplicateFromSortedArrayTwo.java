package dsa.two_pointer;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
public class RemoveDuplicateFromSortedArrayTwo {

    public static void main(String[] args) {

        int[] arr = {1,1,1,2,2,2,3,3};

        System.out.println(removeDuplicateTwo(arr));
    }

    private static int removeDuplicateTwo(int[] arr) {

       int i = 0;
       int j = 0;

        while (j < arr.length) {

            int count = 1;

            while (j+1< arr.length && arr[j]==arr[j+1]) {
                count++;
                j++;
            }

            count = Math.min(count, 2);

            for(int k = 0;k<count;k++) {
                arr[i] = arr[j];
                i++;
            }
            j++;
        }

        return i;
    }
}

/*
* Here is a concise description of your solution for the Remove Duplicates from Sorted Array II problem:

### Approach: Two-Pointer (Read and Write) with Frequency Counting

Your solution uses a clever two-pointer technique to traverse the array while overwriting it in-place to keep only up to two of each duplicate.

**1. Pointer Setup**

* You maintain two pointers: `j` acts as a "read" pointer to scan through the array, and `i` acts as a "write" pointer to place the accepted elements.



**2. Grouping and Counting**

* As `j` iterates through the array in your outer `while` loop, you initialize a `count` to 1 for the current element.


* An inner `while` loop checks if the next element (`arr[j+1]`) is identical to the current element (`arr[j]`).


* If it is, you increment `count` and advance `j` to group all identical elements together.



**3. Capping the Duplicates**

* You elegantly cap the frequency by applying `count = Math.min(count, 2)`. This ensures that even if a number appears five times, you only prepare to write it a maximum of two times.



**4. In-Place Writing**

* Using a small `for` loop, you write `arr[j]` to the `i` pointer's position exactly `count` times.


* You increment the write pointer `i` for each placement.


* Finally, you increment `j` to move on to the next unique number group.


* By the end of the traversal, `i` naturally represents the correct length `k` of the modified array, which is returned.



**Complexity:**

* **Time:** `O(N)` — Even with nested loops, the `j` pointer strictly moves forward and visits each element in the array exactly once.
* **Space:** `O(1)` — The modification happens entirely in-place without allocating extra arrays or data structures.
* */
