public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int k = 0;
        for (int num : A){
            if (num > k){
                k = num;
            }
        }
        int[] counts = new int[k+1];
        for (int e : A){
            counts[e]++;
        }
        int i = 0;
        for (int j=0; j<k+1; j++){
            while (counts[j]>0){
                A[i] = j;
                counts[j]--;
                i++;
            }
        }
    }

}
