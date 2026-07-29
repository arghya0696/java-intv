package dsa.two_pointer;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
public class RemoveDuplicateFromSortedArray {

    public static void main(String[] args) {

        int[] arr = {0,0,1,1,1,2,2,3,3,4};

        System.out.println(removeDuplicate(arr));
    }

    private static int removeDuplicate(int[] arr) {

       int i = 0;
       int j = 0;

       while (j<arr.length) {
           if(arr[i]!=arr[j]) {
               i++;
               arr[i] = arr[j];
           }
           j++;
       }
        return i+1;
    }
}

/*
* Here is a concise description of your solution for the Remove Duplicates from Sorted Array problem:

### Approach: Two-Pointer (Read and Write)

Your solution uses a straightforward two-pointer technique to overwrite duplicate values in-place, taking advantage of the fact that the array is already sorted.

**1. Pointer Setup**

* You use two pointers starting at index 0: `j` acts as the "read" pointer exploring the array, and `i` acts as the "write" pointer holding the position of the last confirmed unique element.



**2. Finding Unique Elements**

* The `while` loop moves `j` through the array.


* You compare the element at the current read position (`arr[j]`) with the last recorded unique element (`arr[i]`).


* If they are the same, `j` simply moves forward (skipping the duplicate).



**3. In-Place Writing**

* When `arr[i] != arr[j]`, it means you have discovered a brand new unique number.


* You first increment `i` to move the write pointer to the next available slot, and then overwrite that slot with the new unique value: `arr[i] = arr[j]`.


* The `j` pointer continues to increment unconditionally to keep checking the rest of the array.



**4. Returning the Length**

* Because `i` represents the 0-based index of the last unique element, the total count of unique elements (and the new length of the valid array) is `i + 1`.



**Complexity:**

* **Time:** O(N) — The read pointer `j` traverses the array exactly once.
* **Space:** O(1) — The array is modified entirely in-place without the need for extra memory structures.
* */