import java.util.Arrays;

/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    private static int properOffSet(int[] arr) {
        int min = Integer.MAX_VALUE;

        for (int i : arr) {
            min = Math.min(min, i);
        }

        if (min >= 0) {
            return 0;
        } else {
            return -min;
        }
    }
    private static int offSetValue(int offSet, int originalValue) {
        return originalValue + offSet;
    }

    private static int originalValue(int offSet, int offSetValue) {
        return offSetValue - offSet;
    }
    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        int offSet = properOffSet(arr);

        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, offSetValue(offSet, i));
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[offSetValue(offSet, i)]++;
        }

        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[offSetValue(offSet, item)];
            sorted[place] = item;
            starts[offSetValue(offSet, item)] += 1;
        }

        // return the sorted array
        return sorted;
    }

    public static void main(String[] args) {
        //int[] nonNegative = {1, 2, 3};
        int[] nonNegative = {9, 5, 2, 1, 5, 3, 0, 3, 1, 1};
        int[] someNegative = {9, 5, -4, 2, 1, -2, 5, 3, 0, -2, 3, 1, 1};

        System.out.println(Arrays.toString(betterCountingSort(someNegative)));
    }
}
