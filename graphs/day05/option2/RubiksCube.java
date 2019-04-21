import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// this is our implementation of a rubiks cube. It is your job to use A* or some other search algorithm to write a
// solve() function
public class RubiksCube {

    private BitSet cube;
    private ArrayList<Character> rotations = new ArrayList<>();
    private int cost;
    private int heur = 1;

    // initialize a solved rubiks cube
    public RubiksCube() {
        // 24 colors to store, each takes 3 bits
        cube = new BitSet(24 * 3);
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                setColor(side * 4 + i, side);
            }
        }
    }

    // initialize a rubiks cube with the input bitset
    private RubiksCube(BitSet s) {
        cube = (BitSet) s.clone();
    }

    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        cube = (BitSet) r.cube.clone();
        this.rotations.addAll(r.rotations);
        this.heur = r.heur;
        cost = r.rotations.size() + this.heur;
    }

    public RubiksCube(RubiksCube r, Character lastRotation) {
        cube = (BitSet) r.cube.clone();
        this.rotations.addAll(r.rotations);
        this.rotations.add(lastRotation);
        this.heur = this.getHeur();
        cost = r.rotations.size() + this.heur;
    }

    private void printCube(BitSet c){
        System.out.printf("\n[");
        for (int i = 0; i < 72; i++) {
            if (c.get(i)){
                System.out.printf("1");
            } else {
                System.out.printf("0");
            }
            if ((i+1) % 3 == 0){
                System.out.printf(", ");
            }
        }
        System.out.printf("]\n");
    }
    private int getHeur(){
        int count = 0;
        for (int i = 0; i < 24; i++) {
            BitSet trip = new BitSet(3);
            if (cube.get(i*3))
                trip.set(0);
            if (cube.get(i*3 + 1))
                trip.set(1);
            if (cube.get(i*3 + 2))
                trip.set(2);

            int color = bitsetToInt(trip);

            if (Math.abs(i/4 - color) == 3)
                count += 2;
            else if (Math.abs(i/4 - color) == 0)
                ;
            else
                count++;
        }
        return count/12;
    }

    private int getHeur2(){
        int dist = 0;
        for (int i = 0; i < 24; i++) {
//            int temp =
        }
        return 0;
    }
    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return other.cube.equals(cube);
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return cube.hashCode();
    }

    public boolean isSolved() {
        return this.equals(new RubiksCube());
    }


    // takes in 3 bits where bitset.get(0) is the MSB, returns the corresponding int
    private static int bitsetToInt(BitSet s) {
        int i = 0;
        if (s.get(0)) i |= 4;
        if (s.get(1)) i |= 2;
        if (s.get(2)) i |= 1;
        return i;
    }

    // takes in a number 0-5, returns a length-3 bitset, where bitset.get(0) is the MSB
    private static BitSet intToBitset(int i) {
        BitSet s = new BitSet(3);
        if (i % 2 == 1) s.set(2, true);
        i /= 2;
        if (i % 2 == 1) s.set(1, true);
        i /= 2;
        if (i % 2 == 1) s.set(0, true);
        return s;
    }

    // index from 0-23, color from 0-5
    private void setColor(int index, int color) {
        BitSet colorBitset = intToBitset(color);
        for (int i = 0; i < 3; i++)
            cube.set(index * 3 + i, colorBitset.get(i));
    }


    // index from 0-23, returns a number from 0-5
    private int getColor(int index) {
        return bitsetToInt(cube.get(index * 3, (index + 1) * 3));
    }

    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        int[] faceFrom = null;
        int[] faceTo = null;
        int[] sidesFrom = null;
        int[] sidesTo = null;
        // colors move from the 'from' variable to the 'to' variable
        switch (c) {
            case 'u': // clockwise
            case 'U': // counterclockwise
                faceFrom = new int[]{0, 1, 2, 3};
                faceTo = new int[]{1, 2, 3, 0};
                sidesFrom = new int[]{4, 5, 8, 9, 17, 16, 21, 20};
                sidesTo = new int[]{21, 20, 4, 5, 8, 9, 17, 16};
                break;
            case 'r':
            case 'R':
                faceFrom = new int[]{8, 9, 10, 11};
                faceTo = new int[]{9, 10, 11, 8};
                sidesFrom = new int[]{6, 5, 2, 1, 17, 18, 13, 14};
                sidesTo = new int[]{2, 1, 17, 18, 13, 14, 6, 5};
                break;
            case 'f':
            case 'F':
                faceFrom = new int[]{4, 5, 6, 7};
                faceTo = new int[]{5, 6, 7, 4};
                sidesFrom = new int[]{3, 2, 8, 11, 14, 15, 23, 20};
                sidesTo = new int[]{8, 11, 14, 15, 23, 20, 3, 2};
                break;
            default:
//                System.out.println(c);
                assert false;
        }
        // if performing a counter-clockwise rotation, swap from and to
        if (Character.isUpperCase(c)) {
            int[] temp;
            temp = faceFrom;
            faceFrom = faceTo;
            faceTo = temp;
            temp = sidesFrom;
            sidesFrom = sidesTo;
            sidesTo = temp;
        }
        RubiksCube res = new RubiksCube(cube);
        res.rotations = this.rotations;
        for (int i = 0; i < faceFrom.length; i++) res.setColor(faceTo[i], this.getColor(faceFrom[i]));
        for (int i = 0; i < sidesFrom.length; i++) res.setColor(sidesTo[i], this.getColor(sidesFrom[i]));
        return res;
    }

    public List<RubiksCube> getNeighbors() {
        char[] rotations = {'u', 'U', 'r', 'R', 'f', 'F'};
        LinkedList<RubiksCube> neighbors = new LinkedList<>();
        for (char c : rotations) {
            neighbors.add(new RubiksCube(this.rotate(c), c));
        }
        return neighbors;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r= r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }

    void printRotations(){
        System.out.print("\n[ ");
        for (int i = 0; i < rotations.size(); i++) {
            System.out.printf("%s, ", (char)rotations.get(i));
        }
        System.out.print("]\n");
    }

    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        ArrayList<Character> out = null;

        HashSet<BitSet> visited = new HashSet<>();
        visited.add(this.cube);

        PriorityQueue<RubiksCube> queue = new PriorityQueue<>(1, new CubeComparator());
        queue.offer(new RubiksCube(this));

        while (!queue.isEmpty()){
            RubiksCube curr = queue.poll();
//            curr.printRotations();
            if (curr.isSolved()){
//                System.out.println("solved: ");
                curr.printRotations();
                out = curr.rotations;
                return out;
            }

            for (RubiksCube r : curr.getNeighbors()){
                if (!visited.contains(r.cube)){
                    visited.add(r.cube);
                    queue.offer(r);
                }
            }

        }
        return out;
    }

    private class CubeComparator implements Comparator<RubiksCube>{
        public int compare(RubiksCube r1, RubiksCube r2){
            if (r1.cost < r2.cost){
                return -1;
            } else if (r1.cost > r2.cost){
                return 1;
            }
            return 0;
        }
    }

}