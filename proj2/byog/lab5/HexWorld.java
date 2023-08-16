package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /**
     * @param s The size of the hex
     * @param i The row numbers where i = 0 is the bottom row
     * @return a positive number represents offset number
     */
    public static int hexRowWidth(int s, int i) {
        if (i < s) {
            return s + i * 2;
        } else {
            int maxRowNumber = s + (s - 1) * 2;
            int rowGap = i - s;
            return maxRowNumber - 2 * rowGap;
        }
    }

    /**
     * @param s The size of the hex
     * @param i The row numbers where i = 0 is the bottom row
     */
    public static int hexRowOffset(int s, int i) {
        if (i < s) {
            return (s - 1) - i;
        } else {
            return i - s;
        }
    }

    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {

    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

    }
}
