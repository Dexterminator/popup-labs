package se.dxtr.numbertheorylibrary;

/**
 * Utility class providing methods for modular arithmetic.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class ModularOperations {
    public static long IMPOSSIBLE = -1;

    /**
     * Returns the result of adding x and y modulo n.
     */
    public static long add(long x, long y, long n) {
        return (x + y) % n;
    }

    /**
     * Returns the result of subtracting y from x modulo n.
     */
    public static long subtract(long x, long y, long n) {
        long res = x - y;
        while (res < 0)
            res += n;
        return res;
    }

    /**
     * Returns the result of multiplying x with y modulo n.
     */
    public static long multiply(long x, long y, long n) {
        return (x * y) % n;
    }

    /**
     * Returns the result of dividing x with y modulo n.
     */
    public static long divide(long x, long y, long n) {
        long yInverse = multiplicativeInverse(y, n);
        if (yInverse == IMPOSSIBLE)
            return IMPOSSIBLE;
        return multiply(x, yInverse, n);
    }

    /**
     * Returns the multiplicative inverse a^-1 modulo n.
     */
    private static long multiplicativeInverse(long a, long n) {
        long t = 0;
        long newT = 1;
        long r = n;
        long newR = a;
        while (newR != 0) {
            long quotient = r / newR;
            long tmp = t;
            t = newT;
            newT = tmp - quotient * newT;
            tmp = r;
            r = newR;
            newR = tmp - quotient * newR;
        }

        if (r > 1)
            return IMPOSSIBLE;
        if (t < 0)
            t += n;
        return t;
    }
}
