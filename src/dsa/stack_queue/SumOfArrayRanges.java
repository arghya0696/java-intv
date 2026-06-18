package dsa.stack_queue;

import java.util.Stack;

public class SumOfArrayRanges {

    public static void main(String[] args) {

        int[] arr = {4,-2,-3,4,1};
        System.out.println(subArrayRanges(arr));
    }

    private static int subArrayRanges(int[] arr) {

        int mod = (int) (Math.pow(10, 9) + 7);

        int min = sumOfSubArrayMin(arr);
        int max = sumOfSubArrayMax(arr);

        return (max - min)%mod;
    }

    private static int sumOfSubArrayMax(int[] arr) {

        int mod = (int) (Math.pow(10, 9) + 7);


        int n = arr.length, sum = 0, left, right;

        int[] nge = prevGreaterAndEqualElement(arr,n);
        int[] pge = nextGreaterElement(arr,n);

        for(int i=0;i<n;i++) {
            // contribution
            left = i - pge[i];
            right = nge[i] - i;
            sum= (int) ((sum+ ((long) left * right * arr[i]) % mod) % mod);
        }

        return sum;
    }

    private static int[] nextGreaterElement(int[] arr, int n) {

        Stack<Integer> nge = new Stack<>();
        int[] ans = new int[n];

        for(int i=n-1;i>=0;i--) {
            while (!nge.isEmpty() && arr[nge.peek()]<=arr[i])
                nge.pop();

            ans[i] = nge.isEmpty() ? n : nge.peek();
            nge.add(i);
        }

        return ans;
    }

    private static int[] prevGreaterAndEqualElement(int[] arr, int n) {
        Stack<Integer> pge = new Stack<>();
        int[] ans = new int[n];

        for(int i=0;i<n;i++) {
            while (!pge.isEmpty() && arr[pge.peek()] < arr[i])
                pge.pop();

            ans[i] = pge.isEmpty() ? -1 : pge.peek();
            pge.add(i);
        }

        return ans;
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
