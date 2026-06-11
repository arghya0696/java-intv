package dsa.recursion;

import java.util.ArrayList;

public class PrintSubsequence {

    public static void main(String[] args) {

        int[] arr = {3,2,1};

        printSub(0, new ArrayList<Integer>(), arr);
    }

    private static void printSub(int i, ArrayList<Integer> data, int[] arr) {

        if(i>=arr.length) {
            System.out.println(data);
            return;
        }

        data.add(arr[i]);
        printSub(i+1, data, arr);

        data.remove(data.size() -1);
        printSub(i+1, data, arr);
    }
}
