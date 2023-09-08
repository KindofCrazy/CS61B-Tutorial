package lab11.graphs;

import edu.princeton.cs.algs4.In;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        int minimumIndex = 0;
        for (int i = 0; i < distTo.length; i++) {
            if (!marked[i] && distTo[i] != Integer.MAX_VALUE) {
                minimumIndex = i;
                break;
            }
        }

        for (int i = 0; i < distTo.length; i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                continue;
            }

            if (!marked[i]) {
                int currentMinPriority = distTo[minimumIndex] + distTo[minimumIndex];
                int potentialMinPriority = distTo[i] + distTo[i];
                if (currentMinPriority > potentialMinPriority) {
                    minimumIndex = i;
                }
            }
        }

        return minimumIndex;
        /* You do not have to use this method. */
    }

    void changePriority(int v) {
        for (int w : maze.adj(v)) {
            if (distTo[w] > distTo[v] + 1) {
                distTo[w] = distTo[v] + 1;
                edgeTo[w] = v;
            }
        }
    }


    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(s);
        marked[s] = true;
        announce();

        if (s == t) {
            return;
        }

        while (!queue.isEmpty()) {
            int removedValue = queue.remove();
            changePriority(removedValue);
            int nextNode = findMinimumUnmarked();
            marked[nextNode] = true;
            announce();
            if (nextNode == t) {
                return;
            }
            queue.add(nextNode);
        }

    }

    @Override
    public void solve() {
        astar(s);
    }

}

