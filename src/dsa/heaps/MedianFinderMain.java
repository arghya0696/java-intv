package dsa.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

// https://leetcode.com/problems/find-median-from-data-stream/description/

// next imp problem : 3422. Minimum Operations to Make Subarray Elements Equal
// check
class MedianFinder {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());


    public MedianFinder() {

    }

    public void addNum(int num) {

        if(maxHeap.isEmpty()) {
            maxHeap.add(num);
        } else {
            if(num < maxHeap.peek()) {
                maxHeap.add(num);
            }
            else {
                minHeap.add(num);
            }
        }
        // after addition rearrange
        if(maxHeap.size() == minHeap.size()+2) {
            minHeap.add(maxHeap.remove());
        }
        if (minHeap.size() == maxHeap.size()+2) {
            maxHeap.add(minHeap.remove());
        }
    }

    public double findMedian() {

        if(maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek())/2.0;
        }
        else {
            if(maxHeap.size() == minHeap.size()+1) {
                return maxHeap.peek();
            }
            else
                return minHeap.peek();
        }
    }
}

public class MedianFinderMain {

    public static void main(String[] args) {

    }
}
