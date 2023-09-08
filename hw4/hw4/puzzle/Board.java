package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private static final int BLANK = 0;
    private int[][] tiles;
    private int N;



    /** Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j
     */
    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.N = tiles.length;
    }

    /* Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        return tiles[i][j];
    }

    /* Returns the board size N */
    public int size() {
        return N;
    }

    /* Returns the neighbors of the current board */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /* Hamming estimate described below */
    public int hamming() {
        int estimatedValue = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (i == N - 1 && j == N - 1) {
                    break;
                }

                if (tiles[i][j] != N * i + j + 1) {
                    estimatedValue++;
                }
            }
        }
        return estimatedValue;
    }

    private int manhattanDistance(int value, int curX, int curY) {
        int goalX = (value - 1) / N;
        int goalY = (value - 1) % N;

        return Math.abs(goalX - curX) + Math.abs(goalY - curY);
    }

    /* Manhattan estimate described below */
    public int manhattan() {
        int estimatedValue = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == BLANK) {
                    continue;
                }
                estimatedValue += manhattanDistance(tiles[i][j], i, j);
            }
        }
        return estimatedValue;
    }


    /* The Manhattan estimate will always be greater than or equal to the Hamming estimate :
      When a tile is in the wrong position, the hamming estimate caused is 1,
      and the manhattan estimate caused is no less than 1.
     */

    /* Both estimates will always be less or than equal to the true distance:
      One move will only cause one value's position changing, so get to the right
      position will take no less than manhattan estimate moves.
     */

    /* Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope.
     */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /* Returns true if this board's tile values are the same
     position as y's
     */
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }

        Board other = (Board) y;
        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (this.tileAt(i, j) != other.tileAt(i, j)) {
                    return false;
                }
            }
        }

        return true;

    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
