import java.util.concurrent.ThreadLocalRandom;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        int[] sorted = sort(arr);
        int count = 0;
        for (int i = 0; i < sorted.length-2; i++) {
            int sec = i + 1;
            int thr = sorted.length - 1;
            while (sec < thr){
                if (sorted[i] + sorted[sec] + sorted[thr] == sum){
                    count++;
                    sec++;
                } else if (sorted[i] + sorted[sec] + sorted[thr] < sum){
                    sec++;
                } else if (sorted[i] + sorted[sec] + sorted[thr] > sum){
                    thr--;
                }
                System.out.println(count);
            }
        }
        return count;
    }

    private static void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }


    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int[] sort(int[] array) {
        shuffleArray(array);
        quickSort(array, 0, array.length-1);
        return array;
    }

    public static void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p - 1);
            quickSort(a, p + 1, hi);
        }
    }

    public static int partition(int[] array, int lo, int hi) {
        int pivot = lo;
        int i = hi;
        while (i != pivot){
            if (array[i] < array[pivot]){
                swap(array, i, pivot+1);
                swap(array, pivot+1, pivot);
                pivot++;
            } else if (array[i] >= array[pivot]) {
                i--;
            }
        }
        return pivot;
    }

}

