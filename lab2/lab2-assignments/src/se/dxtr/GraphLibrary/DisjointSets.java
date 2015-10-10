package se.dxtr.graphlibrary;

/**
 * Utility class to implement the Disjoint Sets data structure
 * <p>
 * Authors: Ludvig Jansson and Dexter Gramfors
 */
public class DisjointSets {
    private int[] ids;
    private int[] heights;

    /**
     * Public constructor for the Disjoint Sets data structure
     *
     * @param n size of the set
     */
    public DisjointSets (int n) {
        ids = new int[n];
        heights = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            heights[i] = 1;
        }
    }

    /**
     * Performs a union of the sets containing the elements a and b.
     * Returns immediatelly if a and b already are in the same set.
     * Otherwise, attaches the smallest (by height) set to the largest.
     *
     * @param a An element
     * @param b An element
     */
    public void union (int a, int b) {
        int aRoot = root (a);
        int bRoot = root (b);
        if (aRoot == bRoot)
            return;
        if (heights[aRoot] < heights[bRoot]) {
            ids[aRoot] = bRoot;
        } else if (heights[aRoot] > heights[bRoot]) {
            ids[bRoot] = aRoot;
        } else { // heights equal, attaching the tree will increase height by 1
            ids[aRoot] = bRoot;
            heights[bRoot]++;
        }
    }

    /**
     * Decides if a and b are in the same set by finding their roots.
     *
     * @param a An element
     * @param b An element
     * @return True iff they are in the same element, False otherwise
     */
    public boolean inSameSet (int a, int b) {
        return root (a) == root (b);
    }

    /**
     * Recursive function to perform path compression while finding the root of a.
     */
    private int root (int a) {
        if (a != ids[a])
            ids[a] = root (ids[a]);
        return ids[a];
    }
}
