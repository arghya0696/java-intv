package dsa.heaps;

import java.util.PriorityQueue;

// https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1

public class NearlySortedArray {

    public static void main(String[] args) {

        int[] arr = {6,5,3,2,8,9,10};

        int k = 4;

        nearlySort(arr, k);

        for(int el : arr) {
            System.out.print(el+" ");
        }
    }

    private static void nearlySort(int[] arr, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap

        int i = 0;

        for(int el: arr) {
            pq.add(el);

            if(pq.size() > k) {
                // remove and replace
                int top = pq.remove();
                arr[i++] = top;
            }
        }

        // for remaining element
        while (!pq.isEmpty()) {
            int top = pq.remove();
            arr[i++] = top;
        }
    }
}
