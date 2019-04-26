import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        int numBoards = M-1;
        Arrays.sort(occupied);
        int numCovered = occupied[occupied.length-1] - (occupied[0]) + 1;
//        ArrayList<Gap> gap = findGaps(occupied);  //version 1
        PriorityQueue<Integer> gap = gapQueue(occupied);
        while (numBoards > 0){
//            numCovered = findMaxRange(numCovered, occupied, gap); //version 1
            if (gap.isEmpty()){
                return numCovered;
            }
            numCovered -= gap.poll();
            numBoards--;
        }
        return numCovered;
    }

    private static PriorityQueue<Integer> gapQueue(int[] occupied){
        PriorityQueue<Integer> gs = new PriorityQueue<>(1, new gapSort());
        for (int i = 0; i < occupied.length-1; i++) {
            int temp = findDiff(occupied[i], occupied[i+1]);
            gs.offer(temp);
        }
        return gs;
    }

    private static int findDiff(int s, int e){
        return e - s - 1;
    }

    static class gapSort implements Comparator<Integer>{
        public int compare(Integer a, Integer b){
            if (b == a){
                return 1;
            }
            return b - a;
        }
    }

//    //given list containing indices of all occupied stalls, find max number of white spaces to delete
//    private static int findMaxRange(int numCovered, int[] occupied, ArrayList<Gap> gaps){
//        int greatestDiff = 0;
//        int tempgap=0;
//        for (int i = 0; i < gaps.size(); i++) {
//            int diff = gaps.get(i).diff;
//            if (diff > greatestDiff && !gaps.get(i).hasBoard){
////                System.out.printf("before: %d, after %d\n", greatestDiff, diff);
//                greatestDiff = diff;
//                tempgap = i;
//            }
//        }
////        System.out.printf("\nadding post at %d\nsubtracting %d from %d\n\n", gaps.get(tempgap).end, greatestDiff, numCovered);
//        gaps.get(tempgap).hasBoard = true;
//        return numCovered - greatestDiff;
//    }
//
//    private static ArrayList<Gap> findGaps(int[] occupied){
//        ArrayList<Gap> gs = new ArrayList<>();
////        System.out.printf("\n{");
//        for (int i = 0; i < occupied.length-1; i++) {
////            System.out.printf("%d, ", occupied[i]);
//            Gap temp = new Gap(occupied[i], occupied[i+1]);
//            gs.add(temp);
//        }
////        System.out.printf("%d}\n", occupied[occupied.length-1]);
//        return gs;
//    }
//
//    public static class Gap{
//        int diff;
//        boolean hasBoard;
//        //Takes start and end, stores difference and whether a post has been added
//        Gap(int s, int e){
//            diff = e - s - 1;
//            hasBoard = false;
//        }
//    }
}