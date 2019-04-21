
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;
    int dist;
    int x;
    int y;

    // Create a 2D array representing the solved board state
    //private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        this.tiles = b;
        this.n = b.length;
        int[] temp = findEmpty();
        this.x = temp[0];
        this.y = temp[1];
        this.dist = manhattan();
    }

    public Board(int[][] b, int newx, int newy, int newdist){
        tiles = b;
        n = b.length;
        x = newx;
        y = newy;
        dist = newdist;

    }
    /*
     * Size of the board
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return n;
    }

    private int[] findEmpty(){
        int[] indices = new int[2];
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 9){
                    indices[0] = i;
                    indices[1] = j;
                }
            }
        }
        return indices;
    }

    private int manhattanhelp(int[][] board, int i, int j){
        int val = this.tiles[i][j];
        int desiredRow = (val-1)/3;
        int desiredCol = (val-1)%3;
        if(val < 9) {
            //System.out.println("i=" + i + " j=" + j + " val=" + val);
            //System.out.println("h=" + (Math.abs(i-desiredRow) + Math.abs(j - desiredCol)));
            return Math.abs(i-desiredRow) + Math.abs(j - desiredCol);
        }

        else
            return 0;

    }
    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j=0; j<n; j++){
                dist += manhattanhelp(this.tiles, i, j);
            }
        }
        return dist;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        return (dist == 0);
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        int[] b = new int[(int)Math.pow(n, 2)];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[index] = tiles[i][j];
                index++;
            }
        }
        int inversions = 0;
        for (int i = 1; i < b.length; i++) {
            int key = b[i];
            int j = i-1;

            while (j>=0 && b[j] > key){
                if (b[j] != 9 && key != 9)
                    inversions++;
                b[j+1] = b[j];
                j--;
            }
            b[j+1] = key;
        }
        return (inversions % 2 == 0);
    }

    private boolean[] canMove(int i, int j){
        boolean up = true, down = true, left = true, right = true;

        if (i == 0) up = false;
        if (i == n-1) down = false;
        if (j == 0) left = false;
        if (j == n-1) right = false;
        boolean[] res = {up, down, left, right};
        return res;
    }

    private int[][] copyOf(){
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(tiles[i], 0, res[i], 0, n);
        }
        return res;
    }
    private Board swap(int x1, int y1){
        int newdist = dist - (manhattanhelp(tiles, x, y) - manhattanhelp(tiles, x1, y1));
        int[][] out = copyOf();
        out[x][y] = tiles[x1][y1];
        out[x1][y1] = 9;
        newdist += manhattanhelp(out, x, y) + manhattanhelp(out, x1, y1);
        return new Board(out);

    }
    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();
        boolean[] dir = canMove(x, y);
        if (dir[0]) boards.add(swap(x-1, y));
        if (dir[1]) boards.add(swap(x+1, y));
        if (dir[2]) boards.add(swap(x, y-1));
        if (dir[3]) boards.add(swap(x, y+1));
        return boards;
    }

    public void printBoard() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(tiles[i]));
        }
        System.out.println("--------------");
    }
    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 9, 6}, {7, 8, 5}};
        Board board = new Board(initState);
        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
    */
}