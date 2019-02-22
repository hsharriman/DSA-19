public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int largest = i;

        if (leftChild(i) < size && heap[largest] < heap[leftChild(i)]){
            largest = leftChild(i);
        }
        if (rightChild(i) < size && heap[largest] < heap[rightChild(i)]){
            largest = rightChild(i);
        }

        if (largest != i){
            i = swap(i, largest);
            sink(i);
        }
    }
    //Swaps original value with value at second index
    // i is OG index, a is new index
    //returns new index of sinking value
    private int swap(int i, int a){
        int temp = heap[a];
        heap[a] = heap[i];
        heap[i] = temp;
        return a;
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            swap(heap, 0, i);
            size--;
            sink(0);
        }
        for (int entry : heap){
            System.out.printf(" %d", entry);
        }
        System.out.printf("\n");

        return heap;
    }
}
