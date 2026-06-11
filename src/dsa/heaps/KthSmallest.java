package dsa.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallest {

    public static void main(String[] args) {

        int[] arr = {7,10,4,3,20,15,13,1,6,8};

        int k = 4;

        System.out.println(kthSmallest(arr, k));
    }

    private static Integer kthSmallest(int[] arr, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // max heap

        // idea is to remove all the max nums and just be with k small nums , top one will always be kth smallest ,
        // thats why we used max heap
        for(int num : arr) {
            pq.add(num);
            if(pq.size() > k) {
                pq.remove();
            }
        }
        return pq.peek();
    }
}
