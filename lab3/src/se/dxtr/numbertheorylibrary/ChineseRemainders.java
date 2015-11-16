package se.dxtr.numbertheorylibrary;

import java.math.BigInteger;
import static se.dxtr.numbertheorylibrary.ModularOperations.multiplicativeInverse;

/**
 * Utility class containing a method for solving a chinese remainder problem instance.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class ChineseRemainders {

    /**
     * Calculate and return the chinese remainder solution x mod K where K = mn,
     * for the system:
     * x = a (mod n)
     * x = b (mod m)
     * @return a {@link ChineseRemainderSolution} containing the solution x mod K
     */
    public static ChineseRemainderSolution solveCoprime(int a, int n, int b, int m) {
        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigB = BigInteger.valueOf(b);
        BigInteger bigM = BigInteger.valueOf(m);

        // k = m*n
        BigInteger k = bigN.multiply(bigM);

        // m^-1 mod n
        BigInteger mInverseModn = BigInteger.valueOf(multiplicativeInverse(m, n));

        // n^-1 mod m
        BigInteger nInverseModm = BigInteger.valueOf(multiplicativeInverse(n, m));

        // a * m * (m^-1 mod n)
        BigInteger aTimesMTimesMInverse = bigA.multiply(mInverseModn.multiply(bigM));

        // b * n * (n^-1 mod m)
        BigInteger bTimesNTimesNInverse = bigB.multiply(nInverseModm.multiply(bigN));

        // x = a * m * m^-1 + b * n * n^-1 (mod k)
        long x = aTimesMTimesMInverse.add(bTimesNTimesNInverse).mod(k).longValue();
        return new ChineseRemainderSolution(x, k.longValue());
    }
}
