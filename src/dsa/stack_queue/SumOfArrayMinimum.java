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
