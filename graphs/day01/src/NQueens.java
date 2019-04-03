import java.util.ArrayList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }
    //Checks the current row
    public static boolean checkRow(char[][] board, int r){
        for (int i = 0; i < board.length; i++) {
            if (board[r][i] == 'Q') return true;
        }
        return false;
    }
    //Checks the current column
    public static boolean checkCol(char[][] board, int c){
        for (int i = 0; i < board.length; i++) {
            if (board[i][c] == 'Q') return true;
        }
        return false;
    }

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        List<char[][]> answers = new ArrayList<>();
        char[][] tempBoard = new char[n][n];
        for (int i=0; i<tempBoard.length; i++){
            for (int j=0; j<tempBoard[0].length; j++){
                tempBoard[i][j] = '.';
            }
        }

        solve(answers, tempBoard, 0, 0, n);
        return answers;
    }
    //O(n^2) to check if a valid position, O(n!) to check all permutations of the board, each branch can have n branches
    private static void solve(List<char[][]> sols, char[][] board, int r, int c, int numQ){
        if (numQ == 0){
            sols.add(copyOf(board));
        } else{
            for (int i=0; i < board[0].length; i++){
                if (!checkCol(board, i) && !checkRow(board, r) && !checkDiagonal(board, r, i)){
                    board[r][i] = 'Q';
                    solve(sols, board, r+1, i, numQ-1);
                    board[r][i] = '.';
                }
            }
        }
    }

}
