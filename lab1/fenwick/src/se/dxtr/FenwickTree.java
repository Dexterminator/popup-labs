package se.dxtr;

/**
 * Utility class which implements the Fenwick Tree data structure
 *
 * Authors: Dexter Gramfors and Ludvig Jansson
 */
public class FenwickTree {
    long[] treeSums;

    /**
     * Public constructor of the Fenwick Tree
     * @param n Size of the tree
     */
    public FenwickTree (int n) {
        treeSums = new long[n+1];
    }

    /**
     * Adds a number to an element in the tree
     * @param index Index in the tree of the desired element
     * @param delta Number to add to the element
     */
    public void add (int index, int delta) {
        int n = index;
        while (n < treeSums.length) {
            treeSums[n] += delta;
            n += (n & -n);
        }
    }

    /**
     * Sums up the tree up to a specific index (inclusive)
     * @param end Index to stop at
     * @return The sum of elements [0,1,...,end]
     */
    public long sum (int end) {
        if (end < 1)
            return 0;
        long sum = 0;
        int n = end;
        while (n > 0) {
            sum += treeSums[n];
            n -= (n & -n);
        }
        return sum;
    }
}
