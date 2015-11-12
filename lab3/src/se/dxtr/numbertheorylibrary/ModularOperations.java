package se.dxtr.numbertheorylibrary;

/**
 * Created by dexter on 12/11/15.
 */
public class ModularOperations {

    public static long add(long x, long y, long n) {
        return (x + y) % n;
    }

    public static long subtract(long x, long y, long n) {
        long res = x - y;
        while (res < 0)
            res += n;
        return res;
    }
}
