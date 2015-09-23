package se.dxtr;

/**
 * Utility class that provides a method for finding the longest increasing subsequence.
 * <p/>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class LIS {

    /**
     * Finds the longest increasing subsequence in an array of objects that are comparable to each other.
     *
     * @param objects An array of objects that are comparable to each other, in which to find the longest increasing
     *                subsequence
     * @return An array containing the indicies of the elements that make up the longest increasing subsequence
     */
    public static <T extends Comparable<T>> int[] lis (T[] objects) {
        int[] last = new int[objects.length + 1];
        int[] parent = new int[objects.length];
        int longest = 0;

        for (int i = 0; i < objects.length; i++) {
            int newLength = binarySearch (objects, last, longest, objects[i]);
            parent[i] = last[newLength - 1];
            last[newLength] = i;
            if (newLength > longest)
                longest = newLength;
        }

        int[] lis = new int[longest];
        int currIndex = last[longest];
        for (int i = longest - 1; i >= 0; i--) {
            lis[i] = currIndex;
            currIndex = parent[currIndex];
        }

        return lis;
    }

    /**
     * Binary searches for the largest value in last such that objects[last[l]] < object
     */
    private static <T extends Comparable<T>> int binarySearch (T[] objects, int[] last, int l, T object) {
        int lo = 1;
        int hi = l;
        int mid;
        while (lo <= hi) {
            mid = (int) Math.ceil ((lo + hi) / 2);
            if (objects[last[mid]].compareTo (object) < 0)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return lo;
    }
}
