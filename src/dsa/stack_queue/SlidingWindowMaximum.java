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
