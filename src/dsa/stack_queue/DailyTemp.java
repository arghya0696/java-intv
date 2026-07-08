package dsa.stack_queue;

import java.util.Stack;

// https://leetcode.com/problems/daily-temperatures/description/
public class DailyTemp {

    public static void main(String[] args) {

        int[] arr = {30,60,90};

        int[] ans = dailyTemp(arr);

        for(int num:ans)
            System.out.print(num+" ");
    }

    private static int[] dailyTemp(int[] arr) {

        int[] ans = new int[arr.length];
        int[] ngeI = nextGreaterElementIndex(arr);

        for(int i=0;i< arr.length;i++) {
            int diff = ngeI[i] - i; // cz if no greater it should be zero
            ans[i] = diff;
        }

        return ans;
    }

    private static int[] nextGreaterElementIndex(int[] arr) {

        Stack<Integer> st = new Stack<>();

        int[] ans = new int[arr.length];
        int n = arr.length;

        for (int i=arr.length-1;i>=0;i--) {
            while (!st.isEmpty() && arr[st.peek()]<=arr[i])
                st.pop();

            ans[i] = st.isEmpty() ? i : st.peek();
            st.push(i);
        }

        return ans;
    }
}
