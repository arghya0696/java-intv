package dsa.heaps;

import java.util.*;

// https://www.geeksforgeeks.org/problems/top-k-frequent-elements-in-array/1

// for any order :
// https://leetcode.com/problems/top-k-frequent-elements/submissions/1973485101/
public class TopKFreq {

    public static void main(String[] args) {

       int[] arr = {7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9}; int k = 4;

        System.out.println(topKFreq2(arr, k));

    }

    private static ArrayList<Integer> topKFreq(int[] arr, int k) {
        // Code here

        Map<Integer, Integer> hmp = new HashMap<>();

        for (int el : arr) {
            hmp.put(el, hmp.getOrDefault(el, 0)+1);
        }

        PriorityQueue<Holder> pq = new PriorityQueue<>((a,b)-> {
            if(a.freq == b.freq) {
                return b.num - a.num;
            }
            return b.freq - a.freq;
        });

        for (Map.Entry<Integer, Integer> val : hmp.entrySet()) {
            pq.add(new Holder(val.getKey(), val.getValue()));
        }

        ArrayList<Integer> ans = new ArrayList<>();

       for(int i=0;i<k && !pq.isEmpty();i++) {
           ans.add(pq.remove().num);
       }

        return ans;
    }

    private record Holder(int num , int freq){}

    // for any order if req
    // although u can do Coolection.reverse and it will be in above soltn format
    private static ArrayList<Integer> topKFreq2(int[] arr, int k) {
        // Code here

        Map<Integer, Integer> hmp = new HashMap<>();

        for (int el : arr) {
            hmp.put(el, hmp.getOrDefault(el, 0)+1);
        }

        PriorityQueue<Holder> pq = new PriorityQueue<>((a,b)-> {
            if(a.freq == b.freq) {
                return a.num - b.num;
            }
            return a.freq - b.freq;
        });

        for (Map.Entry<Integer, Integer> val : hmp.entrySet()) {
            pq.add(new Holder(val.getKey(), val.getValue()));
            if(pq.size() > k)
                pq.remove();
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<k && !pq.isEmpty();i++) {
            ans.add(pq.remove().num);
        }

        return ans;
    }
}
