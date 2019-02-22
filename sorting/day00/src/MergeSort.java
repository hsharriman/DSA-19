import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime: O(nlgn)
     * Average-case runtime: O(nlgn)
     *
     * Space-complexity: Every divide = copy of array O(n) and O(lgn) divides. O(nlgn)? space complexity
     */
    @Override
    public int[] sort(int[] array) {
        int[] left, right;
        if (array.length <= INSERTION_THRESHOLD){
            InsertionSort temp = new InsertionSort();
            array = temp.sort(array);
            return array;
        }

        if (array.length % 2 != 0){
            left = Arrays.copyOfRange(array, 0, array.length/2 + 1);
            right = Arrays.copyOfRange(array, array.length/2 + 1, array.length);
        } else {
            left = Arrays.copyOfRange(array, 0, array.length/2);
            right = Arrays.copyOfRange(array, array.length/2, array.length);
        }
        int[] sortL = sort(left);
        int[] sortR = sort(right);
        array = merge(sortL, sortR);

        return array;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] array = new int[a.length + b.length];
        while (k < array.length){
            if (i<a.length && j<b.length && a[i] <= b[j]) {
                array[k] = a[i];
                i++;
            } else if (i<a.length && j<b.length && a[i] > b[j]){
                array[k] = b[j];
                j++;
            } else if (i<a.length) {
                array[k] = a[i];
                i++;
            } else if (j<b.length){
                array[k] = b[j];
                j++;
            }
            k++;
        }
        return array;
    }

}
