package dsa.heaps;
// https://www.geeksforgeeks.org/problems/is-binary-tree-heap/1
// https://leetcode.com/problems/check-completeness-of-a-binary-tree/submissions/1974154036/
class Node {
    int data;
    Node left,right;

    public Node(int data) {
        this.data = data;
        left  = right = null;
    }
}
class IsBTMaxHeap {

    static int s;

    public boolean isHeap(Node node) {

        s = sizeOfTree(node);

        return isMaxHeap(node) && isCBT(node, 1);

    }

    private boolean isCBT(Node node, int idx) {
        if(node == null) return true;
        if(idx > s) return false;
        return isCBT(node.left, 2*idx) && isCBT(node.right, 2*idx+1);
    }

    private boolean isMaxHeap(Node node) {
        if(node == null) return true;
        int leftVal = node.left!=null ? node.left.data : Integer.MIN_VALUE; // for leaf nodes , we can put Integer.MIN_VALUE as parent cannot be smaller than child
        int rightVal = node.right!=null ? node.right.data : Integer.MIN_VALUE;

        if(node.data<= leftVal || node.data<=rightVal) return false;
        return isMaxHeap(node.left) && isMaxHeap(node.right);
    }

    private int sizeOfTree(Node node) {
        if(node==null) return 0;
        return 1 + sizeOfTree(node.left) + sizeOfTree(node.right);
    }
}
