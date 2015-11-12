package se.dxtr.numbertheorylibrary;

import java.math.BigInteger;

/**
 * Created by dexter on 12/11/15.
 */
public class ChineseRemainders {

    /**
     * Calculate the chinese remainder solution x mod K where K = mn.
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public static ChineseRemainderSolution solveCoprime(int a, int n, int b, int m) {
        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigB = BigInteger.valueOf(b);
        BigInteger bigM = BigInteger.valueOf(m);

        BigInteger k = bigN.multiply(bigM);
        BigInteger sum = BigInteger.ZERO;

        // x = a*m*m^-1 + b*n*n^-1 (mod m*n)
        sum = sum.add(bigA.multiply(BigInteger.valueOf(multiplicativeInverse2(m, n)).multiply(bigM)));
        sum = sum.add(bigB.multiply(BigInteger.valueOf(multiplicativeInverse2(n, m)).multiply(bigN)));
        return new ChineseRemainderSolution(sum.mod(k).longValue(), k.longValue());
    }

    /**
     * Use the extended euclidian algorithm to find the inverse of num modulus modder.
     */
    private static long multiplicativeInverse(long num, long modder) {
        long mod0 = modder;
        long tmp;
        long quotient;
        long x0 = 0;
        long x1 = 1;

        if (modder == 1)
            return 1;

        while (num > 1) {
            quotient = num / modder;
            tmp = modder;
            modder = num % modder;
            num = tmp;
            tmp = x0;
            x0 = x1 - quotient * x0;
            x1 = tmp;
        }

        if (x1 < 0)
            x1 += mod0;
        return x1;
    }

    private static long multiplicativeInverse2(long a, long n) {
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
        if (t < 0)
            t += n;
        return t;
    }
}
