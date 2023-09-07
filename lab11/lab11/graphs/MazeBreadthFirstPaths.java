package lab11.graphs;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private Maze maze;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(s);
        marked[s] = true;
        announce();

        if (s == t) {
            return;
        }

        while (!queue.isEmpty()) {
            int removedValue = queue.remove();
            for (int w : maze.adj(removedValue)) {
                if (!marked[w]) {
                    edgeTo[w] = removedValue;
                    distTo[w] = distTo[removedValue] + 1;
                    marked[w] = true;
                    announce();
                    if (w == t) {
                        return;
                    }
                    queue.add(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

