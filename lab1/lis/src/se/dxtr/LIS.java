package se.dxtr;

/**
 * Created by dexter on 09/09/15.
 */
public class LIS {

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
     * Binary searches for the largest value in last s.t. objects[last[l]] < object
     * @param objects Array of objects to binary search over
     * @param last int array keeping indices of last element in the I.S.
     * @param l Current length of the I.S.
     * @param object The key object
     * @return The new length of the I.S. that ends with object
     */
    private static <T extends Comparable<T>> int binarySearch (T[] objects, int[] last, int l, T object) {
        int lo = 1;
        int hi = l;
        int mid;
        while (lo <= hi) {
            mid = (int) Math.ceil ((lo + hi)/2);
            if (objects[last[mid]].compareTo (object) < 0)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return lo;
    }
}
