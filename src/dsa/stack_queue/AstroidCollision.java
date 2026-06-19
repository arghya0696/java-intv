package dsa.stack_queue;

import java.util.Stack;

// https://leetcode.com/problems/asteroid-collision/description/
public class AstroidCollision {

    public static void main(String[] args) {

        int[] arr = {5,10,-5};

        int[] ans = astroidCollision(arr);

        for (int a: ans)
            System.out.print(a+" ");
    }

    private static int[] astroidCollision(int[] arr) {

        Stack<Integer> st = new Stack<>();
        if(arr.length == 1)
            return new int[0];

        for (int j=0 ;j<arr.length; j++) {
            boolean exploded = false;
            while (!st.isEmpty() && haveOppositeSigns(st.peek(), arr[j])) {
                int top = st.peek();
                if(Math.abs(top) < Math.abs(arr[j]))
                    st.pop();
                else if (Math.abs(top) == Math.abs(arr[j])) {
                    st.pop();
                    exploded = true;
                    break;
                }
                else {
                    exploded = true;
                    break;
                }
            }
            if(!exploded)
                st.push(arr[j]);
        }

        return st.isEmpty() ? new int[0] : st.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static boolean haveOppositeSigns(int x, int y) {
        return (x>0 && y<0);
    }
}

/*
* Keep State Local: By moving the exploded boolean inside the for loop, we guarantee a fresh slate for every new asteroid being evaluated.

* Simplified Conditionals: Instead of a separate helper method, checking st.peek() > 0 && arr[j] < 0 is the definitive and only way a collision happens.
* If they are [-2, -2], they are both going left and will never touch. If they are [2, 2], they are both going right at the same speed and will never touch.
* */
