package dsa.stack_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

// https://www.geeksforgeeks.org/problems/previous-smaller-element/1
public class PreviousSmallerElement {

    public static void main(String[] args) {

        int[] arr = {4,5,2,10,8}; //  -1 4 -1 2 2

        ArrayList<Integer> ans = pseSol(arr);

        for (int i : ans) {
            System.out.print(i+" ");
        }
    }

    private static ArrayList<Integer> pseSol(int[] arr) {

        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        int[] res = new int[n];


        for(int i=0;i<n;i++) {
           while (!st.isEmpty() && arr[i] <= st.peek())
               st.pop();

           res[i] = st.isEmpty() ? -1 : st.peek();

           st.push(arr[i]);
        }

        return Arrays.stream(res)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
