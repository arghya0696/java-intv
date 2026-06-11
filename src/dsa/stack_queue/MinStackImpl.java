package dsa.stack_queue;

import java.util.Stack;

record Holder(int val, int minVal){}

// https://leetcode.com/problems/min-stack/description/
class MinStack {

    Stack<Holder> st = new Stack<>();

    public MinStack() {}

    public void push(int val) {

        if(st.isEmpty()) {
            st.push(new Holder(val, val));
        } else {
            int minSoFar = st.peek().minVal();
            st.push(new Holder(val, Math.min(minSoFar, val)));
        }
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek().val();
    }

    public int getMin() {
        return st.peek().minVal();
    }

}

public class MinStackImpl {

    public static void main(String[] args) {

    }
}
