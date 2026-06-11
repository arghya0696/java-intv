package dsa.heaps;

import java.util.*;

public class KClosestPointToOrigin {

    public static void main(String[] args) {

        int[][] points = {
                {1, 3}, {-2, 2}, {5, 8}, {0, 1}
        };

        int k = 2;

        ArrayList<ArrayList<Integer>> res = kClosestPointToOrigin(points, k);

        System.out.println(res);
    }

    private static ArrayList<ArrayList<Integer>> kClosestPointToOrigin(int[][] points, int k) {

        // max heap with custom impl
        PriorityQueue<PointsHolder> pq = new PriorityQueue<>((a1,a2)-> {
            int sum1 = ((a1.x * a1.x) + (a1.y * a1.y));
            int sum2 = ((a2.x * a2.x) + (a2.y * a2.y));

            return sum2 - sum1;
        });

        for (int[] point : points) {
            int a1 = point[0];
            int a2 = point[1];

            pq.add(new PointsHolder(a1, a2));
            if (pq.size() > k)
                pq.remove();
        }


       ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

       while (!pq.isEmpty()) {
           PointsHolder pointsHolder = pq.remove();
           ArrayList<Integer> temp = new ArrayList<>();
           temp.add(pointsHolder.x);
           temp.add(pointsHolder.y);

           ans.add(temp);
       }

        return ans;
    }

    private record PointsHolder(int x, int y){}


    // another solution , based on sorting
    public int[][] kClosest(int[][] points, int k) {
        // Sort points by squared distance to origin
        Arrays.sort(points, (a, b) ->
                (a[0]*a[0] + a[1]*a[1]) - (b[0]*b[0] + b[1]*b[1])
        );

        // Copy first k points
        return Arrays.copyOfRange(points, 0, k);
    }
}
