import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0, sorted, 0, asciis.length);

        for (int i = sorted.length - 1; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }

        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int radix = 257;
        int[] counts = new int[radix];
        int[] starts = new int[radix];
        /* index i = 0 indicates there is a placeholder
        * and all the other character's index plus 1 */
        for (String s : asciis) {
            if (index >= s.length()) {
                counts[0]++;
            }
            else {
                counts[(int) s.charAt(index) + 1]++;
            }
        }

        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];

        for (String item : asciis) {
            int itemIndex = 0;
            if (index < item.length()) {
                itemIndex = (int) item.charAt(index) + 1;
            }
            int place = starts[itemIndex];
            sorted[place] = item;
            starts[itemIndex]++;
        }

        System.arraycopy(sorted, 0, asciis, 0, sorted.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] s = new String[]{"a", "b", "c"};
        System.out.println(Arrays.toString(sort(s)));
    }
}
