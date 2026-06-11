package dsa.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/last-stone-weight/submissions/1972226491/
public class LastStoneWeight {

    public static void main(String[] args) {

        int[] stones = {2,7,4,1,8,1};

        System.out.println(lastStoneWeight(stones));

    }

    private static int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // max heap

        for(int el  : stones)
            pq.add(el);


        // start of algo
        // pop two top el , x , y -> if x==y dont do anything , else do -> y-x and insert back to heap

        while (!pq.isEmpty()) {
            if(pq.size() == 1) break;

            int x = pq.remove();
            int y = pq.remove();

            if(x!=y) {
                int newEl = x - y;
                pq.add(newEl);
            }
        }

        if(pq.isEmpty()) return 0;

        return pq.peek();
    }
}
