package dsa.heaps;

import java.util.PriorityQueue;

// https://www.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1
public class MinCostRopes {

    public static void main(String[] args) {

        int[] arr = {4,2,6,7,9};

        System.out.println(minCostRopes(arr));
    }

    private static int minCostRopes(int[] arr) {

        int minCost = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // mon heap

        if(arr.length == 1) return 0;

        for(int el: arr)
            pq.add(el);

        while (pq.size()>1) {
            int x = pq.remove();
            int y = pq.remove();

            int newEl = x+y;

            minCost += newEl;
            pq.add(newEl);
        }

        return minCost;
    }
}
