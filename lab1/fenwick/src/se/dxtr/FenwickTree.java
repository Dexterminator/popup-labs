package se.dxtr;

/**
 * Created by dexter on 14/09/15.
 */
public class FenwickTree {
    int[] treeSums;

    public FenwickTree (int n) {
        treeSums = new int[n+1];
    }

    public void add (int index, int delta) {
        int n = index;
        while (n < treeSums.length) {
            treeSums[n] += delta;
            n += (n & -n);
        }
    }

    public int sum (int end) {
        int sum = 0;
        int n = end;
        while (n != 0) {
            sum += treeSums[n];
            n -= (n & -n);
        }
        return sum;
    }
}
