package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static class Position {
        private int x;
        private int y;

        public Position(int px, int py) {
            this.x = px;
            this.y = py;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    /**
     * @param s The size of the hex
     * @param i The row numbers where i = 0 is the bottom row
     * @return the width of the certain row
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
     * @return the offset relative to the top left node (usually negative)
     */
    public static int hexRowOffset(int s, int i) {
        if (i < s) {
            return -i;
        } else {
            return i - 2 * s + 1;
        }
    }

    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi++) {
            world[p.x + xi][p.y] = TETile.colorVariant(t, 32, 32, 32, new Random());
        }
    }

    /**
     * Adds a hexagon to the world.
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int yi = 0; yi < 2 * s; yi++) {
            int rowStartX = p.getX() + hexRowOffset(s, yi);
            int rowStartY = p.getY() + yi;
            int rowWidth = hexRowWidth(s, yi);
            Position start = new Position(rowStartX, rowStartY);

            addRow(world, start, rowWidth, t);
        }
    }

    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }

    public static void formHexWorld(TETile[][] world, int s) {
        for (int i = 0; i < 5; i++) {
            Position start = new Position(3 * (s - 1) + 2 * s, i * 2 * s);
            addHexagon(world, start, s, Tileset.WALL);
        }

        for (int i = 0; i < 4; i++) {
            Position start = new Position(2 * (s - 1) + s, s + i * 2 * s);
            addHexagon(world, start, s, Tileset.FLOOR);
        }

        for (int i = 0; i < 4; i++) {
            Position start = new Position(4 * (s - 1) + 3 * s, s + i * 2 * s);
            addHexagon(world, start, s, Tileset.FLOOR);
        }

        for (int i = 0; i < 3; i++) {
            Position start = new Position(s - 1, 2 * s + i * 2 * s);
            addHexagon(world, start, s, Tileset.SAND);
        }

        for (int i = 0; i < 3; i++) {
            Position start = new Position(5 * (s - 1) + 4 * s, 2 * s + i * 2 * s);
            addHexagon(world, start, s, Tileset.SAND);
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        int WIDTH = 60, HEIGHT = 30;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        formHexWorld(world, 2);

        // draws the world to the screen
        ter.renderFrame(world);
    }

}
