package dsa.two_pointer;

public class RemoveDuplicateFromSortedArray {

    public static void main(String[] args) {

        int[] arr = {0,0,1,1,1,2,2,3,3,4};

        System.out.println(removeDuplicate(arr));
    }

    private static int removeDuplicate(int[] arr) {

       int i = 0;
       int j = 0;

       while (j<arr.length) {
           if(arr[i]!=arr[j]) {
               i++;
               arr[i] = arr[j];
           }
           j++;
       }
        return i+1;
    }
}
