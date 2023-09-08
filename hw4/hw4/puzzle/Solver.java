package hw4.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdRandom;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solver {

    private int moves;
    private List<WorldState> solution;
    private SearchNode lastSearchNode;
    public int enqueueCount = 0;
    private HashMap<WorldState, Integer> estimatedDistance = new HashMap<>();


    private class SearchNode implements Comparable<SearchNode>{
        private final WorldState worldState;
        private final int moves;
        private final SearchNode previous;

        public SearchNode(WorldState worldState, int moves, SearchNode previous) {
            this.worldState = worldState;
            this.moves = moves;
            this.previous = previous;
        }

        public WorldState getWorldState() {
            return worldState;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPrevious() {
            return previous;
        }

        @Override
        public int compareTo(SearchNode o) {
            if (!estimatedDistance.containsKey(this.worldState)) {
                estimatedDistance.put(this.worldState, this.worldState.estimatedDistanceToGoal());
            }
            if (!estimatedDistance.containsKey(o.worldState)) {
                estimatedDistance.put(o.worldState, o.worldState.estimatedDistanceToGoal());
            }
            int currentPriority = this.moves + estimatedDistance.get(this.worldState);
            int otherPriority = o.moves + estimatedDistance.get(o.worldState);
            return currentPriority - otherPriority;
        }

        public boolean isParent(SearchNode node, SearchNode neighbor) {
            if (neighbor == null) {
                return false;
            }

            if (node == null) {
                return false;
            }

            if (node.previous == null) {
                return false;
            }
            return neighbor.worldState.equals(node.previous.worldState);
        }
    }

    private void getAnswer(SearchNode lastState) {
        moves = lastState.getMoves();

        solution = new LinkedList<>();
        SearchNode node = lastSearchNode;
        while (node != null) {
            solution.add(0, node.getWorldState());
            node = node.previous;
        }
    }

    private SearchNode getLastSearchNode(MinPQ<SearchNode> queue) {
        while (!queue.isEmpty()) {
            SearchNode X = queue.delMin();

            if (X.getWorldState().isGoal()) {
                return X;
            }

            for (WorldState neighbor : X.getWorldState().neighbors()) {
                SearchNode newSearchNode = new SearchNode(neighbor, X.getMoves() + 1, X);
                if (X.isParent(X, newSearchNode)) {
                    continue;
                }
                queue.insert(newSearchNode);
                enqueueCount++;
            }
        }
        return null;
    }

    /** Constructor which solves the puzzle, computing
     everything necessary for moves() and solution() to
     not have to solve the problem again. Solves the
     puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        MinPQ<SearchNode> queue = new MinPQ<>();
        SearchNode initialSearchNode = new SearchNode(initial, 0, null);
        queue.insert(initialSearchNode);

        lastSearchNode = getLastSearchNode(queue);

        if (lastSearchNode != null) {
            getAnswer(lastSearchNode);
        }
    }

    /** Returns the minimum number of moves to solve the puzzle starting
       at the initial WorldState.
    */
    public int moves() {
        return moves;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
     to the solution.
     */
    public Iterable<WorldState> solution() {
        return solution;
    }
}
