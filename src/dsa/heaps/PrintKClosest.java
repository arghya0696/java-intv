package dsa.heaps;

import java.util.*;
import java.util.stream.Collectors;

// good prob

// https://www.geeksforgeeks.org/problems/k-closest-elements3619/1

// solved uisng PQ but best solt will be from Binary Search

public class PrintKClosest {

    public static void main(String[] args) {

        int [] arr = {10, 20, 30, 40, 50};
        int k = 3, x = 25;

        int[] ans = printKClosest(arr, k, x);
        for(int el : ans)
            System.out.print(el +" ");
    }

    private static int[] printKClosest(int[] arr, int k, int x) {

        // using sorting
        /*
        int[] ans = Arrays.stream(arr).boxed().filter(i -> !i.equals(x)).sorted((o1, o2) -> {
                    int v1 = Math.abs(o1 - x);
                    int v2 = Math.abs(o2 - x);

                    if (v1 == v2) {
                        return o2 - o1;
                    }
                    return v1 - v2;
                }).mapToInt(Integer::intValue)
                .toArray();

         */


        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> {
           int v1 = Math.abs(a - x);
           int v2 = Math.abs(b - x);

           if(v1 == v2) {
              return b - a;
           }
           return v1 - v2;
        });


        for(int el : arr) {
            if(el == x) continue;
            pq.add(el);
        }

        int[] ans = new int[k];
        for(int i=0;i<k && !pq.isEmpty();i++) {
            ans[i] = pq.remove();
        }

        return ans;
    }
}
