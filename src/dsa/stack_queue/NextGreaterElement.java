package dsa.stack_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

// https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1
public class NextGreaterElement {

    public static void main(String[] args) {

        int[] arr = {6, 8, 0, 1, 3}; // 6 8 0 1 3 6 8 0 1 3

        ArrayList<Integer> ans = ngeSol1(arr);

        for (int i : ans) {
            System.out.print(i+" ");
        }
    }

    private static ArrayList<Integer> ngeSol1(int[] arr) {

        int[] ans = new int[arr.length];

        Stack<Integer> st = new Stack<>();

        int n = arr.length;

        for(int i=n-1;i>=0;i--) {
            while (!st.isEmpty() && st.peek()<=arr[i])
                st.pop();

            ans[i] = st.isEmpty() ? -1 : st.peek();

            st.push(arr[i]);
        }

        return Arrays.stream(ans)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
