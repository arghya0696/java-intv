package dsa.heaps;

// min heap
class Heap {
    private final int[] arr;
    private final int capacity;

    int idx = 1; // starting point of our heap , and we are maxing 1 based indexing

    public Heap(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity+1]; // as 1 based indexing
    }

    int size(){
        return idx-1;
    }

    void display() {
        for(int i=1;i<idx;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    void add(int data) {
        if(arr.length == idx) throw new RuntimeException("Heap size is full");
        arr[idx] = data;

        // rearrangement
        // from bottom nde we will goto parent

        int nodeIdx = idx;

        while (nodeIdx!=1) {
            int parentIdx = nodeIdx/2;
            if(arr[nodeIdx]< arr[parentIdx]) { // swap parent with node child
                swap(nodeIdx, parentIdx);
                // make node new parent
                nodeIdx = parentIdx;
            }
            else break;
        }

        idx++;
    }

    int remove() {
        int min = arr[1];

        if(size()==0) throw new RuntimeException("No data to remove");

        int root = 1;

        // swap
        arr[root] = arr[idx-1];
        idx--; // reduce the array

        // rearrange
        while (root<=size()) {
            int leftIdx = root*2;
            int rightIdx = root*2 + 1;

            int leftVal = (leftIdx<=size()) ? arr[leftIdx] : Integer.MAX_VALUE;
            int rightVal = (rightIdx<=size()) ? arr[rightIdx] : Integer.MAX_VALUE;

            if(arr[root] < leftVal && arr[root] < rightVal) break;

            if(leftVal < rightVal) {
                swap(root, leftIdx);
                root = leftIdx; // as we are going down
            }
            else {
                swap(root, rightIdx);
                root = rightIdx; // as we are going down
            }
        }

        return min;
    }

    private void swap(int nodeIdx, int parentIdx) {
        int temp = arr[nodeIdx];
        arr[nodeIdx] = arr[parentIdx];
        arr[parentIdx] = temp;
    }
}

public class MinHeapImpl {

    public static void main(String[] args) {

        Heap heap = new Heap(10);

        heap.add(10); heap.add(15); heap.add(8); heap.add(9); heap.add(4);

        heap.display();

        System.out.println(heap.remove());

        heap.display();

        heap.add(2);
        heap.display();

        System.out.println(heap.remove());
        heap.display();



    }
}
