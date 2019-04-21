/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.cost = board.dist;
            if (prev != null)
                this.cost += prev.moves + 1;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }
    }

    private class StateComparator implements Comparator<State>{

        // Overriding compare()method of Comparator
        // for descending order of cgpa
        public int compare(State s1, State s2) {
            if (s1.cost < s2.cost)
                return -1;
            else if (s1.cost > s2.cost)
                return 1;
            return 0;
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        State out = state;
        while(out.prev != null)
            out = out.prev;
        return out;
    }

    private LinkedList<Board> createPath(State state) {
        LinkedList<Board> out = new LinkedList<>();
        while (state.prev != null) {
            out.addFirst(state.board);
            state = state.prev;
        }

        if (state.board != null)
            out.addFirst(state.board);

        return out;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        solutionState = new State(initial, 0, null);
    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        if (!(isSolvable())) {
            return null;
        }

        LinkedList<Board> out = null;

        HashSet<Board> visited = new HashSet<>();
        visited.add(solutionState.board);

        PriorityQueue<State> queue = new PriorityQueue<>(2000, new StateComparator());
        queue.offer(solutionState);

        int counter = 0;

        while (!(queue.isEmpty())) {
            solutionState = queue.poll();

            //System.out.println("Current Board. Cost = " + solutionState.cost + ", heur = " + solutionState.board.dist);
            //solutionState.board.printBoard();

            if (solutionState.board.isGoal()) {
                out = createPath(solutionState);
                minMoves = solutionState.cost;
                solved = true;
                return out;
            }

            //System.out.println("Neighbors");
            for (Board b : solutionState.board.neighbors()) {
                if (!visited.contains(b)) {
                    visited.add(b);
                    //b.printBoard();
                    State s = new State(b, solutionState.moves + 1, solutionState);
                    //System.out.println("Cost of board above: " + s.cost  + ", heur = " + s.board.dist);
                    queue.offer(s);
                }
            }
            System.out.println();
            counter++;
        }
        return out;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */

    /*
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Board initial = new Board(initState);
        Solver solver = new Solver(initial);
    }
    */

}